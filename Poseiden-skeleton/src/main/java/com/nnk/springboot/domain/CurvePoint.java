package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Curve Id is mandatory")
    @Column(name = "curveId")
    private Integer curveId;

    private Timestamp asOfDate;

    @NotBlank(message = "Term is mandatory")
    @Column(name = "term")
    private Double term;

    @NotBlank(message = "Value is mandatory")
    @Column(name = "value")
    private Double value;

    private Timestamp creationDate;

}
