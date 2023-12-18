package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "trade")
public class Trade {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "TradeId")
    private Integer tradeId;

    @NotBlank(message = "Account is mandatory")
    @Column(name = "account")
    private String account;

    @NotBlank(message = "Type is mandatory")
    @Column(name = "type")
    private String type;

    @NotNull(message = "Buy quantity is mandatory")
    @Column(name = "buyQuantity")
    private Double buyQuantity;

    @NotNull(message = "Sell quantity is mandatory")
    @Column(name = "sellQuantity")
    private Double sellQuantity;

    @NotNull(message = "Buy price is mandatory")
    @Column(name = "buyPrice")
    private Double buyPrice;

    @NotNull(message = "Sell price is mandatory")
    @Column(name = "sellPrice")
    private Double sellPrice;

    @NotBlank(message = "Benchmark is mandatory")
    @Column(name = "benchmark")
    private String benchmark;

    @Column(name = "tradeDate")
    private Timestamp tradeDate;

    @NotBlank(message = "Security is mandatory")
    @Column(name = "security")
    private String security;

    @NotBlank(message = "Status is mandatory")
    @Column(name = "status")
    private String status;

    @NotBlank(message = "Trader is mandatory")
    @Column(name = "trader")
    private String trader;

    @NotBlank(message = "Book is mandatory")
    @Column(name = "book")
    private String book;

    @NotBlank(message = "Creation name is mandatory")
    @Column(name = "creationName")
    private String creationName;

    @Column(name = "creationDate")
    private Timestamp creationDate;

    @NotBlank(message = "Revision name is mandatory")
    @Column(name = "revisionName")
    private String revisionName;

    @Column(name = "revisionDate")
    private Timestamp revisionDate;

    @NotBlank(message = "Deal name is mandatory")
    @Column(name = "dealName")
    private String dealName;

    @NotBlank(message = "Deal type is mandatory")
    @Column(name = "dealType")
    private String dealType;

    @NotBlank(message = "Source list ID is mandatory")
    @Column(name = "sourceListId")
    private String sourceListId;

    @NotBlank(message = "Side is mandatory")
    @Column(name = "side")
    private String side;
}
