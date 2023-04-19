package ru.skypro.project.marketplace.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
//@Getter
//@Setter
public class UserDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String username; //может быть email
    private String firstName;
    private String lastName;
    //    @Pattern(regexp = "${email.regexp}")
    private String phone;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String avatar;

}


