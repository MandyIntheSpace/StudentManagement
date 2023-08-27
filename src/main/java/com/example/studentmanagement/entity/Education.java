package com.example.studentmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Education implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long educationId;

    @Column(name = "LEVEL")
    private String level;

    @Column(name = "COURSE")
    private String course;

    @Column(name = "INSTITUTE_NAME")
    private String instituteName;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
    private Student student;

}
