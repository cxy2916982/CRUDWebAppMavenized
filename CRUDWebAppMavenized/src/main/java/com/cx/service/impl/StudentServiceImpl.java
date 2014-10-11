package com.cx.service.impl;

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
	public void add(Student student) {
		studentDao.save(student);
	}

	@Transactional
	public void edit(Student student) {
		studentDao.update(student);
	}

	@Transactional
	public void delete(int studentId) {
		studentDao.deleteByIds(studentId);
	}

	@Transactional
	public Student getStudent(int studentId) {
		return studentDao.findObjectById(studentId);
	}

	@Transactional
	public List getAllStudent() {
		return studentDao.findObjectsByConditionWithNoPage();
	}

}
