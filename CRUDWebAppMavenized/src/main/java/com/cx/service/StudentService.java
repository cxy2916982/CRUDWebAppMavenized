package com.cx.service;

import java.util.List;

import com.cx.model.Student;

public interface StudentService {
	
	public void addStudent(Student student) ;
	public void updateStudent(Student student) ;
	public void deleteStudentById(int studentId) ;
	public Student findStudentById(int studentId) ;
	public List<Student> getAllStudent() ;

}
