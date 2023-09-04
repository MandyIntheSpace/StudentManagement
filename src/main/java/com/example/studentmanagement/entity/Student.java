package com.example.studentmanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "STUDENT")
@Entity
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long studentId;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "DATE_OF_BIRTH")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String dateOfBrith;

    @Column(name = "STATUS")
    private String status = "INACTIVE";

    @Column(name = "CITIZENSHIP_NO")
    private String citizenshipNo;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "MARITAL_STATUS")
    private String maritalStatus;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    @Column(name = "MOBILE_NUMBER", unique = true)
    private String mobileNumber;

    @Column(name = "ADDED_BY")
    private String addedBy;

    @CreationTimestamp
    @Column(name = "ADDED_DATE")
    private Date addedDate;

    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    @UpdateTimestamp
    @Column(name = "MODIFIED_DATE")
    private Date modifiedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COURSE_ID")
    private Course course;

}
