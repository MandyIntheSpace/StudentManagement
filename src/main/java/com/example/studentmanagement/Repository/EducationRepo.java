package com.example.studentmanagement.Repository;

import com.example.studentmanagement.entity.Education;
import com.example.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EducationRepo extends JpaRepository<Education, Long> {

    @Query(value = "select * from student c where c.student_id = ?1", nativeQuery = true)
    List<Education> findStudentById(Long studentId);

}
