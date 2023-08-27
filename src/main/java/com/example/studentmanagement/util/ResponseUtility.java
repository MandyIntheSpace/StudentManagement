package com.example.studentmanagement.util;

import com.example.studentmanagement.Response.ResponseBody;
import org.springframework.http.HttpStatus;

public class ResponseUtility {

    public static ResponseBody resourceCreated(Object object, String message, HttpStatus status) {

        ResponseBody responseBody = new ResponseBody();
        responseBody.setBody(object);
        responseBody.setStatus(status);
        responseBody.setMessage(message);
        return responseBody;

    }

    public static ResponseBody resouceNotCreated(Object object, String message, HttpStatus status) {

        ResponseBody responseBody = new ResponseBody();
        responseBody.setBody(object);
        responseBody.setStatus(status);
        responseBody.setBody(message);
        return responseBody;

    }

    public static ResponseBody resourceNotFound(Object object, String message, HttpStatus status) {

        ResponseBody responseBody = new ResponseBody();
        responseBody.setBody(object);
        responseBody.setMessage(message);
        responseBody.setStatus(status);
        return responseBody;

    }

    public static ResponseBody resourceFound(Object object, String message, HttpStatus status) {

        ResponseBody responseBody = new ResponseBody();
        responseBody.setBody(object);
        responseBody.setMessage(message);
        responseBody.setStatus(status);
        return responseBody;
    }

}
