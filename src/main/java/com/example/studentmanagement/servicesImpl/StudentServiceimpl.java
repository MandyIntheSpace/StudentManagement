package com.example.studentmanagement.servicesImpl;

import com.example.studentmanagement.Repository.CourseRepo;
import com.example.studentmanagement.Repository.DepartmentRepo;
import com.example.studentmanagement.Repository.StudentRepo;
import com.example.studentmanagement.Request.RegistrationRequest;
import com.example.studentmanagement.Response.ResponseBody;
import com.example.studentmanagement.config.JwtAuthenticationFilter;
import com.example.studentmanagement.entity.Course;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.services.StudentService;
import com.example.studentmanagement.util.ResponseUtility;
import com.example.studentmanagement.util.UserDetailsutil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceimpl implements StudentService {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserDetailsutil userDetailsutil;

//    @Autowired
//    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    public ResponseBody saveStudent(RegistrationRequest registrationRequest) {

        String currentUserName = userDetailsutil.getCurrentUsername();

        if (currentUserName != null) {
            System.out.println("Current Username: "+currentUserName);
        }
        else {
            System.out.println("No user authenticated");
        }
        Student student = null;
        try{
//            student = this.modelMapper.map(registrationRequest, Student.class);
            student = new Student();
            student.setFirstName(registrationRequest.getFirstName());
            student.setMiddleName(registrationRequest.getMiddleName());
            student.setGender(registrationRequest.getGender());
            student.setDateOfBrith(registrationRequest.getDateOfBirth());
            student.setStatus(registrationRequest.getStatus());
            student.setCitizenshipNo(registrationRequest.getCitizenshipNo());
            student.setAddress(registrationRequest.getAddress());
            student.setMaritalStatus(registrationRequest.getStatus());
            student.setEmailAddress(registrationRequest.getEmailAddress());
            student.setAddedBy(currentUserName);
            student.setModifiedBy("Not Yet Modified");
            student.setStatus("ACTIVE");

            List<Course> courseList = this.courseRepo.findByCourseName(registrationRequest.getCourseName());

            if (courseList.isEmpty()) {
                return ResponseUtility.resourceNotFound(student, "The course name you are searching for didn't found", HttpStatus.NOT_FOUND);
            }
            for (Course course : courseList) {
                if (course.getEnrolledYear().equals(registrationRequest.getEnrolledYear())) {
                    student.setCourse(course);
                }
            }

            Student student1 = this.studentRepo.save(student);
            RegistrationRequest request = this.modelMapper.map(student1, RegistrationRequest.class);
            return ResponseUtility.resourceCreated(request, "student stored successfully", HttpStatus.OK);

        }catch(Exception e) {
            e.printStackTrace();
            return ResponseUtility.resouceNotCreated(student, "The student is not found", HttpStatus.NOT_FOUND);
        }
    }
}
