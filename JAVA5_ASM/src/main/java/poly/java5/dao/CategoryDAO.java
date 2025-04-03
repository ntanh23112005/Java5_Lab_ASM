package poly.java5.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.java5.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, String> {
	List<Category> findAll();
	
	Category findByIdEquals(String id);
}
