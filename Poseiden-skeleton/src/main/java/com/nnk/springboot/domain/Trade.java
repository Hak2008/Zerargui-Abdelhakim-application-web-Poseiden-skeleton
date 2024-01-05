package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "trade")
public class Trade {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "TradeId")
    private Integer tradeId;

    @NotBlank(message = "Account is mandatory")
    @Column(name = "account")
    private String account;

    @NotBlank(message = "Type is mandatory")
    @Column(name = "type")
    private String type;

    @NotBlank(message = "Buy quantity is mandatory")
    @Column(name = "buyQuantity")
    private Double buyQuantity;

    private Double sellQuantity;

    private Double buyPrice;

    private Double sellPrice;

    private String benchmark;

    private Timestamp tradeDate;

    private String security;

    private String status;

    private String trader;

    private String book;

    private String creationName;

    private Timestamp creationDate;

    private String revisionName;

    private Timestamp revisionDate;

    private String dealName;

    private String dealType;

    private String sourceListId;

    private String side;
}
