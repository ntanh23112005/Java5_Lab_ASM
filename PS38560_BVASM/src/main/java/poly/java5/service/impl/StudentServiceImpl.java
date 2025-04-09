package poly.java5.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.java5.dao.StudentDAO;
import poly.java5.entity.Student;
import poly.java5.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentDAO dao;
	
	@Override
	public List<Student> findAll() {
		return dao.findAll();
	}

	@Override
	public void save(Student student) {
		dao.save(student);
	}

	@Override
	public Student findByIdEquals(String id) {
		return dao.findByIdEquals(id);
	}

}
