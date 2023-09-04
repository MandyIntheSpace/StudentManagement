package com.example.studentmanagement.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @Column(name = "UNIVERSITY_NAME")
    private String universityName;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
    private Student student;

}
