package ru.skypro.project.marketplace.dto;

import lombok.Data;

@Data
public class FullAds {

    private Integer pk;
    private String authorLastName;
    private String authorFirstName;
    private String description;
    private String email;
    private String image;
    private String phone;
    private int price;
    private String title;

}
