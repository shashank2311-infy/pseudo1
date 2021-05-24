package com.infy.Student;

import com.infy.Student.entity.Student;
import com.infy.Student.exception.StudentException;
import com.infy.Student.repository.StudentRepository;
import com.infy.Student.service.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class StudentServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Mock
	public StudentRepository studentRepository;

	@InjectMocks
	public StudentServiceImpl studentService;

	@Test
	public void getAllStudents() throws Exception
	{
		List<Student> studentList=new ArrayList<>();
		Student student=new Student();
		Student student1=new Student();
		student.setStudentId(101L);
		student.setStudentName("Shashank");
		student.setStudentStream("Science");
		studentList.add(student);

		student1.setStudentId(102L);
		student1.setStudentName("Aakash");
		student1.setStudentStream("Commerce");
		studentList.add(student1);

		Mockito.when(studentRepository.findAll()).thenReturn(studentList);
		Assertions.assertEquals(studentService.getAllStudents(),studentList);
	}

	@Test
	public void getStudentByIdTest() throws Exception
	{
		Long studentId=101L;
		Student student=new Student();
		student.setStudentId(101L);
		student.setStudentName("Shashank");
		student.setStudentStream("Science");

		Mockito.when(studentRepository.findById(studentId)).thenReturn(java.util.Optional.of(student));
		Assertions.assertEquals(studentService.getStudentById(studentId), student);
	}

	@Test
	public void addStudentTest() throws Exception
	{
		Student student=new Student();
		student.setStudentId(101L);
		student.setStudentName("Shashank");
		student.setStudentStream("Science");

		Mockito.when(studentRepository.save(student)).thenReturn(student);
		Assertions.assertEquals(studentService.addStudent(student),student.getStudentId());
	}

}
