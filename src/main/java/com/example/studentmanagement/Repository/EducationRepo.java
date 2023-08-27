package com.example.studentmanagement.Repository;

import com.example.studentmanagement.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface EducationRepo extends JpaRepository<Education, Long> {


}
