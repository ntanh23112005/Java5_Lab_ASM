package poly.java5.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import poly.java5.dao.ProductDAO;
import poly.java5.entity.Product;
import poly.java5.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService{

	
	// Pageable chứa các tham số để lọc, sắp xếp
	// PageRequest: lấy các tham số bỏ vào Pageable
	// sử dụng Page<T>.findAll(pageable) dùng để lấy dữ liệu 
	// 	dựa vào các tham số trong pageable
	
	@Autowired
	ProductDAO dao;
	
	@Override
	public Page<Product> getPage(int pageNumber) {
		int pageSize = 8;
		
		Sort sort = Sort.by(Direction.DESC,"unitPrice");
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		
		Page<Product> page = dao.findAll(pageable);

		return page;
	}

	@Override
	public List<Product> getSortByUnitPrice() {
		Sort sort = Sort.by(Direction.DESC,"unitPrice");
		List<Product> list = dao.findAll(sort);
		return null;
	}

}
