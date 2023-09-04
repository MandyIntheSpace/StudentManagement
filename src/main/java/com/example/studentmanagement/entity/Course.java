package com.example.studentmanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "COURSE_DETAILS")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long courseId;

    @Column(name = "NAME")
    private String courseName;

    @Column(name = "ENROLLED_YEAR")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String enrolledYear;

    @Column(name = "DURATION")
    private String duration;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "id")
    private Department department;

}
