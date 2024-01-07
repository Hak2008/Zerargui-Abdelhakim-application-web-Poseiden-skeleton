package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "rulename")
public class RuleName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Description is mandatory")
    @Column(name = "description")
    private String description;

    @NotBlank(message = "JSON is mandatory")
    @Column(name = "json")
    private String json;

    @NotBlank(message = "Template is mandatory")
    @Column(name = "template")
    private String template;

    @NotBlank(message = "SQL Str is mandatory")
    @Column(name = "sqlStr")
    private String sqlStr;

    @NotBlank(message = "SQL Part is mandatory")
    @Column(name = "sqlPart")
    private String sqlPart;
}
