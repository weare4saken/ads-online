package ru.skypro.project.marketplace.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
//@Getter
//@Setter
public class LoginReq {
    private String password;
    private String username;

}
