package com.example.studentmanagement.Repository;
import com.example.studentmanagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {

    Department findByDepartmentName(String departmentName);

}
