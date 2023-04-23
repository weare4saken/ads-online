package ru.skypro.project.marketplace.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.project.marketplace.dto.LoginReq;
import ru.skypro.project.marketplace.dto.RegisterReq;
import ru.skypro.project.marketplace.enums.Role;
import ru.skypro.project.marketplace.exception.UsernameNotFoundException;
import ru.skypro.project.marketplace.model.User;
import ru.skypro.project.marketplace.repository.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private ObjectMapper objectMapper;

    private final LoginReq loginReq = new LoginReq();
    private final User user = new User();
    private final RegisterReq registerReq = new RegisterReq();


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

        loginReq.setUsername("username@mail.ru");
        loginReq.setPassword("password");

        registerReq.setUsername("username2@mail.ru");
        registerReq.setFirstName("User2");
        registerReq.setLastName("Test2");
        registerReq.setPhone("+79609279285");
        registerReq.setPassword("password2");
        registerReq.setRole(Role.ADMIN);
    }

    @AfterEach
    void clearAll() {
        userRepository.delete(user);
    }

    @Test
    public void testLoginReturnsValidCredentialsWhenLoginSuccess() throws Exception {
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginReq)))
                .andExpect(status().isOk());
    }

    @Test
    public void testRegisterReturnsValidCredentialsWhenRegisterSuccess() throws Exception {
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerReq)))
                .andExpect(status().isOk());

        User savedUser = userRepository.findByUsernameIgnoreCase(registerReq.getUsername())
                .orElseThrow(UsernameNotFoundException::new);

        userRepository.delete(savedUser);
    }

}
