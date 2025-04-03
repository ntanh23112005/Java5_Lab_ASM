package poly.java5.service;

import java.util.List;
import java.util.Optional;

import poly.java5.entity.Category;
import poly.java5.entity.Product;

public interface ProductService {

	/**
	 * @param Hàm lấy tất cả cột từ Product
	 * @return List<Product>
	 * */
	List<Product> findAll();
	
	Product save(Product product);
	
	/**
	 * @param Hàm lấy các sản phẩm có trangthai = true
	 * @return List<Product>
	 * */
	List<Product> findByTrangThaiTrue();
	
	/**
	 * @param Hàm lấy 1 sản phẩm theo id
	 * @return Product
	 */
	Product findByIdProduct(int id);
	
	List<Object[]> findAllCategoryName();
	
	public Optional<Category> findByCategoryId(String categoryId);
}
