package ru.skypro.project.marketplace.dto;

import lombok.Data;
import ru.skypro.project.marketplace.enums.Role;

@Data
public class RegisterReq {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
}
