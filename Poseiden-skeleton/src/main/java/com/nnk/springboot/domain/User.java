package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @NotBlank(message = "Username is mandatory")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "FullName is mandatory")
    @Column(name = "fullname")
    private String fullname;

    @NotBlank(message = "Role is mandatory")
    @Column(name = "role")
    private String role;
}
