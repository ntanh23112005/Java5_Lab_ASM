package poly.java5.service;

import java.util.List;

import poly.java5.entity.Student;

public interface StudentService {

	List<Student> findAll();
	
	void save(Student student);
	
	Student findByIdEquals(String id);
}
