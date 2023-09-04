package com.example.studentmanagement.servicesImpl;

import com.example.studentmanagement.Exception.ResourceNotFoundException;
import com.example.studentmanagement.Repository.CourseRepo;
import com.example.studentmanagement.Repository.DepartmentRepo;
import com.example.studentmanagement.Request.CourseRequest;
import com.example.studentmanagement.Request.DepartmentRequest;
import com.example.studentmanagement.Response.ResponseBody;
import com.example.studentmanagement.entity.Course;
import com.example.studentmanagement.entity.Department;
import com.example.studentmanagement.services.CourseService;
import com.example.studentmanagement.util.ResponseUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class CourseServiceimpl implements CourseService {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DepartmentRepo departmentRepo;


    @Override
    public CourseRequest addCourse(CourseRequest courseRequest) {
        Course course = new Course();
        course.setCourseName(courseRequest.getCourseName());
        course.setEnrolledYear(courseRequest.getEnrolledYear());
        course.setDuration(courseRequest.getDuration());
        Department department1 = departmentRepo.findByDepartmentName(courseRequest.getDepartmentName());
        Course course1 = null;

        if (department1 != null) {
            course.setDepartment(department1);
            department1.getCourseList().add(course);
            course1 = this.courseRepo.save(course);
        }
        return this.modelMapper.map(course, CourseRequest.class);
    }

//    @Override
//    public CourseRequest addCourse(CourseRequest courseRequest) {
//
//        Department department = this.departmentRepo.findById(departmentId)
//                .orElseThrow(() -> new ResourceNotFoundException("Department", "department id", "departmentId"));
//
//        Course course = new Course();
//        course.setCourseName(courseRequest.getCourseName());
//        course.setEnrolledYear(courseRequest.getEnrolledYear());
//        course.setDuration(courseRequest.getDuration());
//        course.setDepartment(department);
//
//        Course course1 = this.courseRepo.save(course);
//        return this.modelMapper.map(course1, CourseRequest.class);
//    }


}
