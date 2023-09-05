package com.example.studentmanagement.Repository;


import com.example.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    @Query(value = "select * from student c where c.first_name = ?1", nativeQuery = true)
    List<Student> findStudentByName(String name);

    @Query(value = "select * from student c where c.address = ?1", nativeQuery = true)
    Student findByAddress(String address);

}
