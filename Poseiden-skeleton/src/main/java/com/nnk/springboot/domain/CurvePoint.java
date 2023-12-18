package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "CurveId is mandatory")
    private Integer curveId;

    private Timestamp asOfDate;

    @NotNull(message = "Term is mandatory")
    private Double term;

    @NotNull(message = "Value is mandatory")
    private Double value;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp creationDate;

}
