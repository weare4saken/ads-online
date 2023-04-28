package ru.skypro.project.marketplace.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String mediaType;
    private Long fileSize;
    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] data;

}
