package com.example.studentmanagement.config;

import com.example.studentmanagement.Repository.UserRepo;
import com.example.studentmanagement.Request.AuthRequest;
import com.example.studentmanagement.Response.AuthResponse;
import com.example.studentmanagement.Response.ResponseBody;
import com.example.studentmanagement.entity.User;
import com.example.studentmanagement.util.ResponseUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseBody createJwtToken(AuthRequest request) {

        try{

            String userName = request.getUserName();
            String password = request.getPassword();
            User user = userRepo.findByUserName(userName);

            if (user == null) {
                return ResponseUtility.resourceNotFound(userName, "User not found", HttpStatus.OK);
            }

            if (user.getAttemptCount() > 3) {

                user.setStatus("INACTIVE");
                userRepo.save(user);
                return ResponseUtility.resourceNotFound(userName, "Sorry you are disabled", HttpStatus.OK);

            }
            ResponseBody responseBody = authenticate(userName, password);
            if (responseBody.getMessage().equalsIgnoreCase("password did not matched")){
                return responseBody;
            } else if (responseBody.getMessage().equalsIgnoreCase("Not Found")) {
                return responseBody;
            } else {

                UserDetails userDetails = loadUserByUsername(userName);
                User authenticatedUser = userRepo.findByUserName(userName);
                authenticatedUser.setAttemptCount(0);
                userRepo.save(authenticatedUser);
                System.out.println(authenticatedUser.getAttemptCount());
                String newGeneratedToken = jwtUtil.generateToken(userDetails);
                return ResponseUtility.resourceFound(new AuthResponse(newGeneratedToken), "Authenticated", HttpStatus.OK);

            }

        } catch(Exception e) {
//            return ResponseUtility.resourceNotFound(null, "Something went wrong", HttpStatus.CONFLICT);
            e.printStackTrace();
            return ResponseUtility.resourceNotFound(null, "Something went worng there", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private ResponseBody authenticate(String userName, String password) {

        try{

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
            return ResponseUtility.resourceFound(userName, "Username found", HttpStatus.FOUND);

        } catch(Exception e) {

            User user = userRepo.findByUserName(userName);
            if (user.getAttemptCount() <= 3) {

                int count = user.getAttemptCount();
                user.setAttemptCount(count + 1);
                userRepo.save(user);
                return ResponseUtility.resourceFound((3 - user.getAttemptCount()), "password did not matched", HttpStatus.OK);
            }
            else {

                return ResponseUtility.resourceFound(null, "something went wrong", HttpStatus.NOT_FOUND);

            }

        }

    }

    private Set getAuthorities() {

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authorities;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUserName(username);
        if (user != null) {

            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(), user.getPassword(), getAuthorities());
        }
        else {
            return (UserDetails) new UsernameNotFoundException("username is not valid");
        }

    }
}
