package com.infy.Student.service;

import com.infy.Student.entity.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getAllStudents() throws Exception;
    public Student getStudentById(Long studentId) throws Exception;
    public Long addStudent(Student student) throws Exception;
}
