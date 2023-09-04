package com.example.studentmanagement.Repository;

import com.example.studentmanagement.entity.Course;
import com.example.studentmanagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {

    @Query(value = "select * from course_details c where c.name = ?1", nativeQuery = true)
    public List<Course> findByCourseName(String courseName);

}
