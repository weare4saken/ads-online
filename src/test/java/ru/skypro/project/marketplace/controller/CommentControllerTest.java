package ru.skypro.project.marketplace.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.project.marketplace.dto.AdsCommentDto;
import ru.skypro.project.marketplace.enums.Role;
import ru.skypro.project.marketplace.model.Ads;
import ru.skypro.project.marketplace.model.Comment;
import ru.skypro.project.marketplace.model.User;
import ru.skypro.project.marketplace.repository.AdsRepository;
import ru.skypro.project.marketplace.repository.CommentRepository;
import ru.skypro.project.marketplace.repository.UserRepository;
import ru.skypro.project.marketplace.service.CustomUserDetailsService;
import java.time.Instant;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdsRepository adsRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    private Authentication auth;
    private User user = new User();
    private Ads ads = new Ads();
    private Comment comment = new Comment();
    private AdsCommentDto commentDto = new AdsCommentDto();

    @BeforeEach
    void setUp() {
        user.setUsername("username@mail.ru");
        user.setFirstName("User");
        user.setLastName("Test");
        user.setPhone("+79609279284");
        user.setPassword(encoder.encode("password"));
        user.setRole(Role.USER);
        user.setEnabled(true);
        userRepository.save(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        auth = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities());

        ads.setTitle("Ads");
        ads.setDescription("description");
        ads.setPrice(1000);
        ads.setAuthor(user);
        adsRepository.save(ads);

        comment.setText("Text");
        comment.setAds(ads);
        comment.setCreatedAt(Instant.now());
        comment.setAuthor(user);
        commentRepository.save(comment);
    }

    @AfterEach
    void cleatUp() {
        commentRepository.delete(comment);
        adsRepository.delete(ads);
        userRepository.delete(user);
    }

    @Test
    public void testGetCommentsReturnsCorrectCommentsList() throws Exception {
        mockMvc.perform(get("/ads/{id}/comments", ads.getId())
                        .with(authentication(auth)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.count").isNumber())
                .andExpect(jsonPath("$.results").isArray())
                .andExpect(jsonPath("$.results[0].text").value(comment.getText()));
    }

    @Test
    public void testAddAdsCommentReturnsAddedComment() throws Exception {
        commentDto.setText("TEXT");

        mockMvc.perform(post("/ads/{id}/comments", ads.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentDto))
                        .with(authentication(auth)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pk").isNumber())
                .andExpect(jsonPath("$.text").value(commentDto.getText()))
                .andExpect(jsonPath("$.authorFirstName").value(user.getFirstName()));
    }

    @Test
    public void testDeleteAdsCommentReturnsOkWhenCommentRemoved() throws Exception {
        mockMvc.perform(delete("/ads/{adId}/comments/{commentId}", ads.getId(), comment.getId())
                        .with(authentication(auth)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateCommentsReturnsUpdatedComment() throws Exception {
        String newText = "New Text";
        comment.setText(newText);
        commentRepository.save(comment);

        mockMvc.perform(patch("/ads/{adId}/comments/{commentId}", ads.getId(), comment.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comment))
                        .with((authentication(auth))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value(comment.getText()));
    }

}
