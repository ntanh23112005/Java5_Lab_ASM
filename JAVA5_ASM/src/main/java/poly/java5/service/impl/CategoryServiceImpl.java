package poly.java5.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.java5.dao.CategoryDAO;
import poly.java5.entity.Category;
import poly.java5.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDAO cateDAO;
	
	@Override
	public List<Category> findAll() {
		return cateDAO.findAll();
	}

	@Override
	public Category findById(String id) {
		return cateDAO.findByIdEquals(id);
	}

}
