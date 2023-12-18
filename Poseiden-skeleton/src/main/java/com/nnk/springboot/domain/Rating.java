package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Moody's Rating is mandatory")
    private String moodysRating;

    @NotBlank(message = "S&P Rating is mandatory")
    private String sandPRating;

    @NotBlank(message = "Fitch Rating is mandatory")
    private String fitchRating;

    @NotNull(message = "Order Number is mandatory")
    private Integer orderNumber;
}
