package com.crm.exception;

import com.crm.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class HandleException {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<String> handleEmployeeNotFoundException(ResourceNotFound e){

        ErrorDetails errorDetails = new ErrorDetails(new Date());
        return new ResponseEntity<>("Employee not found", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
