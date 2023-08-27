package com.example.studentmanagement.Exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String exceptionMessage){
        super(exceptionMessage);
    }
}
