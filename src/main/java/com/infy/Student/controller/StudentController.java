package com.infy.Student.controller;


import com.infy.Student.entity.Student;
import com.infy.Student.exception.StudentException;
import com.infy.Student.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentServiceImpl studentService;
    Logger log = Logger.getLogger(StudentController.class.getName());

    @PostMapping("/student")
    public ResponseEntity<Long> addStudent(@RequestBody Student student) throws Exception
    {
        try
        {
            log.info("Inside addStudent method of StudentController");
            studentService.addStudent(student);
            ResponseEntity<Long> response=new ResponseEntity<Long>(student.getStudentId(),HttpStatus.CREATED);
            return response;
        }
        catch(StudentException e)
        {
            log.info("Inside catchBlock of StudentException of addStudent in Student Controller");
            throw new StudentException(e.getMessage());
        }
        catch(Exception e)
        {
            log.info("Inside catchBlock of GenericException of addStudent in Student Controller");
            throw new Exception("There Is Some Internal Issue!!");
        }
    }

    @GetMapping(value="/")
    public ResponseEntity<List<Student>> getAllStudents() throws Exception{
        try
        {
            log.info("Inside getAllStudents method of StudentController");
            List<Student> studentList=studentService.getAllStudents();
            ResponseEntity<List<Student>> response=new ResponseEntity<List<Student>>(studentList, HttpStatus.OK);
            return response;
        }
        catch(StudentException e)
        {
            log.info("Inside catchBlock of StudentException of getAllStudents in Student Controller");
            throw new StudentException(e.getMessage());
        }
        catch(Exception e)
        {
            log.info("Inside catchBlock of GenericException of getAllStudents in Student Controller");
            throw new Exception("There Is Some Internal Issue!!");
        }
    }

    @GetMapping(value="/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable("studentId") Long studentId) throws Exception
    {
            try
            {
                log.info("Inside getStudentById method of StudentController");
                Student student=studentService.getStudentById(studentId);
                ResponseEntity<Student> response=new ResponseEntity<Student>(student, HttpStatus.OK);
                return response;
            }
            catch(StudentException e)
            {
                log.info("Inside catchBlock of StudentException of getStudentById in Student Controller");
                throw new StudentException(e.getMessage());
            }
            catch(Exception e)
            {
                log.info("Inside catchBlock of GenericException of getStudentById in Student Controller");
                throw new Exception("There Is Some Internal Issue!!");
            }

    }

}
