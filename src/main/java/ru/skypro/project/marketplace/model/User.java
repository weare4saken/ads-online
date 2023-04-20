package ru.skypro.project.marketplace.model;

import lombok.*;
import org.springframework.lang.NonNull;
import ru.skypro.project.marketplace.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
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
    @NotNull
    private String password;
    private Boolean enabled;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "author")
    private List<Ads> ads;

    @OneToMany(mappedBy = "author")
    private List<Comment> comments;

    @OneToOne
    private Avatar avatar;

}