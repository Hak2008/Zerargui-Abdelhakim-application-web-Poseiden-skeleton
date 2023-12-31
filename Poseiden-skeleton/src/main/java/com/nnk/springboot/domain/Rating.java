package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Moodys rating is mandatory")
    @Column(name = "moodysRating")
    private String moodysRating;

    @NotBlank(message = "S and P rating is mandatory")
    @Column(name = "sandPRating")
    private String sandPRating;

    @NotBlank(message = "Fitch rating is mandatory")
    @Column(name = "fitchRating")
    private String fitchRating;

    @NotBlank(message = "Order number is mandatory")
    @Column(name = "orderNumber")
    private Integer orderNumber;
}
