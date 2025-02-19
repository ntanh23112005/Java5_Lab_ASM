package poly.java5.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.java5.entity.Product;
import poly.java5.model.Report;

public interface ProductDAO extends JpaRepository<Product, Integer>{

//	@Query("FROM Product o WHERE o.unitPrice BETWEEN ?1 AND ?2")
//	List<Product> findByPrice(double minPrice, double maxPrice);
	List<Product> findByUnitPriceBetween(double minPrice, double maxPrice);
	
//	@Query("FROM Product o WHERE o.name LIKE ?1")
//	Page<Product> findByKeywords(String keywords, Pageable pageable);
	Page<Product> findAllByNameLike(String keywords, Pageable pageable);
	
	@Query("SELECT o.category.name AS group, sum(o.unitPrice) AS sum, count(o) AS count "
			+ " FROM Product o "
			+ " GROUP BY o.category"
			+ " ORDER BY sum(o.unitPrice) DESC")
	List<Report> getInventoryByCategory();

}
