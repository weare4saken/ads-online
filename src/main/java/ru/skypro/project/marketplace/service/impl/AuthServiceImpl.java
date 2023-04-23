package ru.skypro.project.marketplace.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.project.marketplace.exception.IncorrectArgumentException;
import ru.skypro.project.marketplace.service.CustomUserDetailsService;
import ru.skypro.project.marketplace.dto.RegisterReq;
import ru.skypro.project.marketplace.service.AuthService;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final CustomUserDetailsService manager;
    private final PasswordEncoder encoder;


    @Override
    public boolean login(String userName, String password) {
        log.debug("Logging in user: {}", userName);
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(RegisterReq registerReq) {
        if(registerReq.getUsername() == null || registerReq.getUsername().isBlank()
            || registerReq.getFirstName() == null || registerReq.getFirstName().isBlank()
            || registerReq.getLastName() == null || registerReq.getLastName().isBlank()
            || registerReq.getPhone() == null || registerReq.getPhone().isBlank()
            || registerReq.getPassword() == null || registerReq.getPassword().isBlank()) throw new IncorrectArgumentException();

        log.info("Registering new user: {}", registerReq.getUsername());
        manager.createUser(registerReq);
        log.info("User {} registered successfully", registerReq.getUsername());
        return true;
    }

}
