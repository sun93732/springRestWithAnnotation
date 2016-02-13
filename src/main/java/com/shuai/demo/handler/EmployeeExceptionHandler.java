package com.shuai.demo.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.shuai.demo.model.exception.MyError;
import com.shuai.demo.model.exception.ResourceNotFoundException;

@ControllerAdvice
public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler{
     
    
    @ExceptionHandler({ResourceNotFoundException.class})   
    protected ResponseEntity<Object> handleInvalidRequest(RuntimeException e, WebRequest request) {
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        MyError error  = new MyError();
        error.setMsg(e.getMessage());
        /*error must be one object....If you pass it a String, it won't work*/
        return handleExceptionInternal(e, error, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
       
        
    }
}

