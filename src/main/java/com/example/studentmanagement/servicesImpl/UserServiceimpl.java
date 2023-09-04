package com.example.studentmanagement.servicesImpl;


import com.example.studentmanagement.Repository.UserRepo;
import com.example.studentmanagement.Request.UserRequest;
import com.example.studentmanagement.entity.User;
import com.example.studentmanagement.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceimpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserRequest addUser(UserRequest userRequest) {
        User user = new User();
        user.setUserName(userRequest.getUserName());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User user1 = this.userRepo.save(user);
        return this.modelMapper.map(user1, UserRequest.class);


    }

    @Override
    public void initUser() {
        User user = new User();
        user.setUserName("mandip");
        user.setPassword(passwordEncoder.encode("mandip"));
        userRepo.save(user);
    }
}
