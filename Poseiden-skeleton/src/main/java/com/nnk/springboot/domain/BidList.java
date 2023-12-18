package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
@Data
@Entity
@Table(name = "bidlist")
public class BidList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BidListId")
    private Integer bidListId;

    @NotBlank(message = "Account is mandatory")
    @Column(name = "account")
    private String account;

    @NotBlank(message = "Type is mandatory")
    @Column(name = "type")
    private String type;

    @NotNull(message = "Bid quantity is mandatory")
    @Column(name = "bidQuantity")
    private Double bidQuantity;

    @NotNull(message = "Ask quantity is mandatory")
    @Column(name = "askQuantity")
    private Double askQuantity;

    @NotNull(message = "Bid is mandatory")
    @Column(name = "bid")
    private Double bid;

    @NotNull(message = "Ask is mandatory")
    @Column(name = "ask")
    private Double ask;

    @NotBlank(message = "Benchmark is mandatory")
    @Column(name = "benchmark")
    private String benchmark;

    @Column(name = "bidListDate")
    private Timestamp bidListDate;

    private String commentary;

    @NotBlank(message = "Security is mandatory")
    private String security;

    @NotBlank(message = "Status is mandatory")
    private String status;

    @NotBlank(message = "Trader is mandatory")
    private String trader;

    @NotBlank(message = "Book is mandatory")
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
    private String side;
}
