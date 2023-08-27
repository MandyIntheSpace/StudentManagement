package com.example.studentmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SYSTEM_USER_LOGIN")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long userId;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "STATUS")
    private String status = "ACTIVE";

    @Column(name = "USERNAME", unique = true)
    private String userName;

    @Column(name = "ATTEMP_COUNT")
    private int attemptCount = 0;

}
