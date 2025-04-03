package poly.java5.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.java5.dao.CategoryDAO;
import poly.java5.dao.ProductDAO;
import poly.java5.entity.Category;
import poly.java5.entity.Product;
import poly.java5.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO dao;

	@Autowired
	CategoryDAO categoryDAO;
	
	@Override
	public List<Product> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Product> findByTrangThaiTrue() {
		return dao.findByTrangThaiTrue();
	}

	@Override
	public Product findByIdProduct(int id) {
		return dao.findByIdEquals(id);
	}

	@Override
	public List<Object[]> findAllCategoryName() {
		return dao.findAllCategoryNames();
	}

	@Override
	public Product save(Product product) {
		 return dao.save(product);
	}

	@Override
	public Optional<Category> findByCategoryId(String categoryId) {
		return categoryDAO.findById(categoryId);
	}

}
