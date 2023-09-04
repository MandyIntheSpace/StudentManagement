package com.example.studentmanagement.servicesImpl;

import com.example.studentmanagement.Repository.DepartmentRepo;
import com.example.studentmanagement.Request.DepartmentRequest;
import com.example.studentmanagement.entity.Department;
import com.example.studentmanagement.services.DepartmentService;
import jdk.jfr.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceimpl implements DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DepartmentRequest saveDepartment(DepartmentRequest departmentRequest) {

        Department department = this.modelMapper.map(departmentRequest, Department.class);
        department.setDepartmentName(departmentRequest.getDepartmentName());
        department.setDepartmentHod(departmentRequest.getDepartmentHod());
        department.setDepartmentContactNo(departmentRequest.getDepartmentContactNo());
        department.setCourseList(departmentRequest.getCourseList());
        Department department1 = this.departmentRepo.save(department);
        return this.modelMapper.map(department1, DepartmentRequest.class);

    }
}
