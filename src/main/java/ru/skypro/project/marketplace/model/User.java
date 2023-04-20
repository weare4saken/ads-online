package ru.skypro.project.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.lang.NonNull;

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

    @OneToMany(mappedBy = "author")
    private List<Ads> ads;

    @OneToMany(mappedBy = "author")
    private List<Comment> comments;

    @OneToOne
    private Avatar avatar;

}