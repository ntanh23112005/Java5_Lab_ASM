package poly.java5.service;

import java.util.List;

import org.springframework.data.domain.Page;

import poly.java5.entity.Product;

public interface ProductService {

	Page<Product> getPage(int pageNumber);
	
	List<Product> getSortByUnitPrice();
}
