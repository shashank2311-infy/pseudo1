package com.infy.Student.service;

import com.infy.Student.entity.Student;
import com.infy.Student.exception.StudentException;
import com.infy.Student.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;
    Logger logger = Logger.getLogger(StudentServiceImpl.class.getName());

    @Override
    public List<Student> getAllStudents() throws StudentException {
        logger.info("Inside getAllStudents of StudentServiceImpl");
        Iterable<Student> iterableStudent=studentRepository.findAll();
        if(iterableStudent==null)
        {
            throw new StudentException("No Student Found in record");
        }
        List<Student> studentList=new ArrayList<>();
        for(Student s:iterableStudent)
        {
            Student student=new Student();
            student.setStudentId(s.getStudentId());
            student.setStudentStream(s.getStudentStream());
            student.setStudentName((s.getStudentName()));
            studentList.add(student);
        }
        return studentList;
    }

    @Override
    public Student getStudentById(Long studentId) throws StudentException {
        logger.info("Inside getStudentById method of StudentServiceImpl");
        Optional<Student> optionalStudent=studentRepository.findById(studentId);
        Student student=optionalStudent.orElseThrow(() -> new StudentException("No Student found with Id: -"+studentId));
        return student;
    }

    @Override
    public Long addStudent(Student student) throws StudentException {
        logger.info("Inside addStudent of StudentServiceImpl");
        if(studentRepository.findById(student.getStudentId())!=null)
        {
            throw new StudentException("Student with id "+student.getStudentId()+" already Exists!!");
        }
        Student student1=new Student();
        student1.setStudentName(student.getStudentName());
        student1.setStudentStream(student.getStudentStream());
        student1.setStudentId(student.getStudentId());
        studentRepository.save(student1);
        return student1.getStudentId();
    }
}
