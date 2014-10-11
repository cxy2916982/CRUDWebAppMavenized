package com.cx.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cx.dao.StudentDao;
import com.cx.model.Student;
import com.cx.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao ;

	@Transactional
	public void addStudent(Student student) {
		studentDao.save(student);
	}

	@Transactional
	public void updateStudent(Student student) {
		studentDao.update(student);
	}

	@Transactional
	public void deleteStudentById(int studentId) {
		studentDao.deleteByIds(studentId);
	}

	@Transactional
	public Student findStudentById(int studentId) {
		return studentDao.findObjectById(studentId);
	}

	@Transactional
	public List<Student> getAllStudent() {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>() ;
		orderby.put("o.id", "asc") ;
		return studentDao.findObjectsByConditionWithNoPage(orderby);
	}
	

}
