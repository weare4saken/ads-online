/*package ru.skypro.project.marketplace.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import ru.skypro.project.marketplace.model.Ads;
import ru.skypro.project.marketplace.model.User;

@RequiredArgsConstructor
public class SecurityUtils {

    private final UserDetailsManager manager;

    public void checkPermissionToAds(Ads ads, User user) {
        if (manager.userExists(user.getUsername())) {
            UserDetailsManager = new UserDetailsManager(user);
        }


    }
}*/
