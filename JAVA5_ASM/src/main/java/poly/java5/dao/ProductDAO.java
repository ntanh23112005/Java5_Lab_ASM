package poly.java5.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.java5.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {

	List<Product> findByTrangThaiTrue();
	
	Product findByIdEquals(int productId);
	
	@Query("SELECT p.category.id, p.category.name FROM Product p")
    List<Object[]> findAllCategoryNames();
}
