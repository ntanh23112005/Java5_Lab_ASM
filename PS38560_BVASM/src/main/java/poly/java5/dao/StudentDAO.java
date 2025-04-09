package poly.java5.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.java5.entity.Student;

public interface StudentDAO extends JpaRepository<Student, String>{

	Student findByIdEquals(String id);
}
