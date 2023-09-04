package com.example.studentmanagement.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsutil {

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal() + "created");
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            String userDetails = ((UserDetails) authentication.getPrincipal()).getUsername();
            return userDetails.toString();
        }
        return null;
    }

}
