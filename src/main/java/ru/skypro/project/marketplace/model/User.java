package ru.skypro.project.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "users")
//@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    private String username;
//    @NotNull
    private String firstName;
//    @NotNull
    private String lastName;
//    @NotNull
    private String phone;
//    @NotNull
//    @Enumerated(EnumType.STRING)
//    private Role role;
    @NotNull
    private String password;

    private Boolean enabled;

    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private List<Ads> ads;

    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private List<Comment> comments;

    @OneToOne
    private Avatar avatar; //мб image?

}