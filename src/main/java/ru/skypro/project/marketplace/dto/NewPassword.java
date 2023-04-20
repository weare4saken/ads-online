package ru.skypro.project.marketplace.dto;

import lombok.Data;

@Data
public class NewPassword {

    private String currentPassword;
    private String newPassword;

}
