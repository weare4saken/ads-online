package ru.skypro.project.marketplace.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.project.marketplace.dto.RegisterReq;
import ru.skypro.project.marketplace.repository.UserRepository;
import ru.skypro.project.marketplace.service.AuthService;

import static ru.skypro.project.marketplace.enums.Role.USER;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final UserServiceImpl userService;
    private final UserRepository userRepository;


    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(RegisterReq registerReq) {
        if (manager.userExists(registerReq.getUsername())) {
            return false;
        }

        manager.createUser(
                User.builder()
                        .passwordEncoder(this.encoder::encode)
                        .password(registerReq.getPassword())
                        .username(registerReq.getUsername())
                        .roles(USER.toString())
                        .build());

        ru.skypro.project.marketplace.model.
                User user = userService.getUserByUsername(registerReq.getUsername());

        user.setFirstName(registerReq.getFirstName());
        user.setLastName(registerReq.getLastName());
        user.setPhone(registerReq.getPhone());
        userRepository.save(user);
        return true;
    }

}
