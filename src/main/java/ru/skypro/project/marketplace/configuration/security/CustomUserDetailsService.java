package ru.skypro.project.marketplace.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.project.marketplace.dto.RegisterReq;
import ru.skypro.project.marketplace.enums.Role;
import ru.skypro.project.marketplace.exception.IncorrectUsernameException;
import ru.skypro.project.marketplace.model.User;
import ru.skypro.project.marketplace.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsernameIgnoreCase(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new CustomUserDetails(user);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public void createUser(RegisterReq registerReq) {
        if (userRepository.findByUsernameIgnoreCase(registerReq.getUsername()).isPresent()) {
            throw new IncorrectUsernameException();
        }

        User user = new User();
        user.setUsername(registerReq.getUsername());
        user.setPassword(passwordEncoder.encode(registerReq.getPassword()));
        user.setRole(Role.USER);
        user.setFirstName(registerReq.getFirstName());
        user.setLastName(registerReq.getLastName());
        user.setPhone(registerReq.getPhone());
        user.setEnabled(true);
        userRepository.save(user);
    }

}
