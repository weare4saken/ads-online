package ru.skypro.project.marketplace.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AdsCommentDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer author;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String authorImage;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String authorFirstName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer pk;
    private String text;

}
