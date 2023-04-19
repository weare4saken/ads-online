package ru.skypro.project.marketplace.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AdsDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer author;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String image;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer pk;
    private int price;
    private String title;
    @JsonIgnore
    private String description;

}
