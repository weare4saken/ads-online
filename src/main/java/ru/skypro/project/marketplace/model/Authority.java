package ru.skypro.project.marketplace.model;

import lombok.Data;
import lombok.NoArgsConstructor;;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String authority;

}
