package com.example.studentmanagement.Repository;
import com.example.studentmanagement.entity.Department;
import com.example.studentmanagement.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {

    Department findByDepartmentName(String departmentName);

    @Query(value = "select from education c where c.student_id = ?1", nativeQuery = true)
    Education findByStudentId(Long Id);

}
