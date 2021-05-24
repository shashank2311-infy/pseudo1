package com.infy.Student.exception;

import com.infy.Student.service.StudentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.logging.Logger;

@ControllerAdvice
public class CentralizedExceptionHandler {
    Logger logger = Logger.getLogger(StudentServiceImpl.class.getName());

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleExceptions(Exception exception)
    {
        logger.info("Inside Generic Exception Handler of Centralized Exception Handler");
        SException se=new SException(exception.getMessage(), new Date(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(se, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = StudentException.class)
    public ResponseEntity<Object> handleStudentExceptions(StudentException e)
    {
        logger.info("Inside StudentException Handler of Centralized Exception Handler");
        SException se=new SException(e.getMessage() , new Date(),HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(se, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> methodArgumentHandler(MethodArgumentNotValidException e)
    {
        logger.info("Inside MethodArgumentNotValid Exception Handler of Centralized Exception Handler");
        SException se=new SException("Internal error occurred. Plz ", new Date(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(se,HttpStatus.BAD_REQUEST);
    }

}
