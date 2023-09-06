package com.example.studentmanagement.servicesImpl;

import com.example.studentmanagement.Exception.ResourceNotFoundException;
import com.example.studentmanagement.Repository.CourseRepo;
import com.example.studentmanagement.Repository.DepartmentRepo;
import com.example.studentmanagement.Repository.EducationRepo;
import com.example.studentmanagement.Repository.StudentRepo;
import com.example.studentmanagement.Request.RegistrationRequest;
import com.example.studentmanagement.Response.ResponseBody;
import com.example.studentmanagement.config.JwtAuthenticationFilter;
import com.example.studentmanagement.dto.StudentDetails;
import com.example.studentmanagement.entity.Course;
import com.example.studentmanagement.entity.Education;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.services.StudentService;
import com.example.studentmanagement.util.ResponseUtility;
import com.example.studentmanagement.util.UserDetailsutil;
import org.hibernate.NonUniqueResultException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private EducationRepo educationRepo;

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
            student.setMaritalStatus(registrationRequest.getMaritialStatus());
            student.setEmailAddress(registrationRequest.getEmailAddress());
            student.setMobileNumber(registrationRequest.getMobileNumber());
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
//            return ResponseUtility.resourceCreated(request, "student stored successfully", HttpStatus.OK);
            Set<Education> educationSet = new HashSet<>();
            educationSet.addAll(registrationRequest.getEducationSet());
            for (Education education : educationSet) {
                education.setStudent(student);
            }
            this.educationRepo.saveAll(educationSet);
            return ResponseUtility.resourceCreated(request, "student stored successfully", HttpStatus.OK);
        }catch(Exception e) {
            e.printStackTrace();
            return ResponseUtility.resouceNotCreated(student, "The student is not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseBody getAllStudent() {
        try{
            List<Student> studentList = this.studentRepo.findAll();
            List<RegistrationRequest> registrationRequestList = new ArrayList<>();
            for (Student student: studentList) {
                RegistrationRequest registrationRequest1 = this.modelMapper.map(student, RegistrationRequest.class);
                registrationRequestList.add(registrationRequest1);
            }
            return ResponseUtility.resourceFound(registrationRequestList, "The student details were founded successfully", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtility.resourceNotFound(null, "something have went wrong", HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseBody getStudentByName(String name) {
        try{
            List<Student> studentList = this.studentRepo.findStudentByName(name);
            List<RegistrationRequest> registrationRequestList = new ArrayList<>();
            for (Student student : studentList) {
                RegistrationRequest request = this.modelMapper.map(student, RegistrationRequest.class);
                registrationRequestList.add(request);
            }
            return ResponseUtility.resourceFound(registrationRequestList, "The student details were found", HttpStatus.OK);
        } catch(Exception e) {
            return ResponseUtility.resourceNotFound(null, "Something went wrong", HttpStatus.CONFLICT);
        }
//        return ResponseUtility.resourceNotFound(null, "Something went wrong", HttpStatus.NOT_FOUND);
    }

    @Override
    public RegistrationRequest updateStudent(RegistrationRequest registrationRequest, Long studentId) {
//        Student student = this.studentRepo.findByAddress(address);
        Student student = this.studentRepo.findById(studentId).orElseThrow(() ->
                new ResourceNotFoundException("Student", "student id", "studentId"));

        String userName = userDetailsutil.getCurrentUsername();
        if (userName != null) {
            System.out.println("The current logged in user's username is: "+userName);
        }
        else {
            System.out.println("The user is not currently loggedin");
        }
        try{
//            System.out.println(registrationRequest.getFirstName() + "this is null");
            student.setFirstName(registrationRequest.getFirstName());
            System.out.println("Receive firstName: "+ registrationRequest.getFirstName());
            student.setMiddleName(registrationRequest.getMiddleName());
            student.setGender(registrationRequest.getGender());
            student.setDateOfBrith(registrationRequest.getDateOfBirth());
            student.setStatus(registrationRequest.getStatus());
            student.setCitizenshipNo(registrationRequest.getCitizenshipNo());
            student.setAddress(registrationRequest.getAddress());
            student.setMaritalStatus(registrationRequest.getMaritialStatus());
            student.setEmailAddress(registrationRequest.getEmailAddress());
            student.setAddedBy(userName);
            student.setModifiedBy(userName);
            student.setModifiedDate(new Date());
            List<Course> courseList = this.courseRepo.findByCourseName(registrationRequest.getCourseName());
            System.out.println(courseList);
            if (courseList == null) {
                return null;
            }
            for(Course course : courseList) {
                if(course.getEnrolledYear().equals(registrationRequest.getEnrolledYear())) {
                    student.setCourse(course);
                }
            }
            try{
                Student student1 = this.studentRepo.save(student);
                return this.modelMapper.map(student1, RegistrationRequest.class);
            } catch(NonUniqueResultException e) {
                e.printStackTrace();
                System.out.println("The problem have occured due to being repeated address name");
            }

            List<Education> educationList = this.educationRepo.findStudentById(student.getStudentId());
            System.out.println(student.getStudentId());
            for (Education education: educationList) {
                this.educationRepo.delete(education);
            }
            Set<Education> educationList1 = registrationRequest.getEducationSet();
            for (Education education: educationList1) {
                education.setStudent(student);
                this.educationRepo.save(education);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseBody deleteStudent( Long studentId) {

        String userName = this.userDetailsutil.getCurrentUsername();

        Student student = this.studentRepo.findById(studentId).orElseThrow(() ->
                new ResourceNotFoundException("Student", "student id", "studentId"));
        try{
            student.setStatus("DELETED");
            student.setModifiedDate(new Date());
            student.setModifiedBy(userName);
            Student student1 = this.studentRepo.save(student);
            RegistrationRequest registrationRequest = this.modelMapper.map(student1, RegistrationRequest.class);
            return ResponseUtility.resourceCreated(registrationRequest, "The user has been successfully updated", HttpStatus.OK);
        }catch(Exception e) {
            e.printStackTrace();
            return ResponseUtility.resouceNotCreated(null, "The user is not updated", HttpStatus.CONFLICT);
        }
    }

    @Override
    public List<RegistrationRequest> searchStudent(String keywords) {
      try{
          List<Student> studentList = this.studentRepo.findByGender(keywords);
          List<RegistrationRequest> studentDetails = studentList.stream().map((student) ->
                  this.modelMapper.map(student, RegistrationRequest.class)).collect(Collectors.toList());
          return studentDetails;
      } catch(Exception e) {
          e.printStackTrace();
          return null;
      }
    }
}


