package ru.skypro.project.marketplace.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.project.marketplace.dto.NewPassword;
import ru.skypro.project.marketplace.dto.UserDto;
import ru.skypro.project.marketplace.exception.BadCredentialsException;
import ru.skypro.project.marketplace.exception.IncorrectArgumentException;
import ru.skypro.project.marketplace.exception.UsernameNotFoundException;
import ru.skypro.project.marketplace.mapper.UserMapper;
import ru.skypro.project.marketplace.model.User;
import ru.skypro.project.marketplace.repository.UserRepository;
import ru.skypro.project.marketplace.service.UserService;

import java.io.IOException;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AvatarServiceImpl avatarService;


    @Override
    public void updatePassword(NewPassword newPassword, Authentication authentication) {
        User user = getUserByUsername(authentication.getName());
        if (!passwordEncoder.matches(newPassword.getCurrentPassword(), user.getPassword())) {
            throw new BadCredentialsException();
        }
        user.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
        userRepository.save(user);
        log.debug("Password updated for user: {}", authentication.getName());
    }

    @Override
    public UserDto getUser(Authentication authentication) {
        log.info("Returning details for user: {}", authentication.getName());
        return UserMapper.INSTANCE.toDto(getUserByUsername(authentication.getName()));
    }

    @Override
    public UserDto updateUser(UserDto userDto, Authentication authentication) {
        if(userDto.getFirstName() == null || userDto.getFirstName().isBlank()
            || userDto.getLastName() == null || userDto.getLastName().isBlank()
            || userDto.getPhone() == null || userDto.getPhone().isBlank()) throw new IncorrectArgumentException();

        User user = getUserByUsername(authentication.getName());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        userRepository.save(user);
        log.debug("User details updated for user: {}", authentication.getName());
        return UserMapper.INSTANCE.toDto(user);
    }

    @Override
    public void updateUserAvatar(MultipartFile avatar,
                                 Authentication authentication) throws IOException {
        User user = getUserByUsername(authentication.getName());

        if (user.getAvatar() != null) {
            avatarService.remove(user.getAvatar());
        }
        user.setAvatar(avatarService.uploadImage(avatar));
        userRepository.save(user);
        log.debug("Avatar updated for user: {}", authentication.getName());
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(UsernameNotFoundException::new);
    }

}

