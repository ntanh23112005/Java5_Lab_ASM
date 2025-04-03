package poly.java5.service;

import java.util.List;

import poly.java5.entity.Category;

public interface CategoryService {
	
	List<Category> findAll();
	
	Category findById(String id);
}
