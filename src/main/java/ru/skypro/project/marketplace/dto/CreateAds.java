package ru.skypro.project.marketplace.dto;

import lombok.Data;

@Data
public class CreateAds {

    private String title;
    private String description;
    private Integer price;

}
