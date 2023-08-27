package com.example.studentmanagement.servicesImpl;


import com.example.studentmanagement.Repository.UserRepo;
import com.example.studentmanagement.Request.UserRequest;
import com.example.studentmanagement.entity.User;
import com.example.studentmanagement.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserRequest addUser(UserRequest userRequest) {
        User user = new User();
        user.setUserName(userRequest.getUserName());
        user.setPassword(userRequest.getPassword());
        User user1 = this.userRepo.save(user);
        return this.modelMapper.map(user1, UserRequest.class);

    }

    @Override
    public void initUser() {
        System.out.println("Nothing is here right now");
    }
}
