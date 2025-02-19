package poly.java5.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import poly.java5.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{

	//call rút gọn, ( naTive = false: call Entity, =true: call Table in DB)
//	@Query(value = "SELECT o FROM Product o WHERE o.unitPrice BETWEEN ?1 AND ?2", nativeQuery = false)
//	@Query(value = "SELECT o FROM Product o WHERE o.unitPrice BETWEEN ?1 AND ?2", nativeQuery = true)
//	@Query(value = "SELECT o FROM Product o WHERE o.unitPrice BETWEEN ?1 AND ?2")
//	@Query("SELECT o FROM Product o WHERE o.unitPrice BETWEEN ?1 AND ?2")
	@Query("FROM Product o WHERE o.unitPrice BETWEEN :from AND :to")
	List<Product> findByUnitPrice(@Param("from") double min, @Param("to") double max);
	
	
	// cách gọi jpa rút gọn
	List<Product> findByUnitPriceBetween(double min, double max);
	
	//call theo trang
	@Query("SELECT o FROM Product o WHERE o.unitPrice BETWEEN ?1 AND ?2")
	Page<Product> findByUnitPrice(double min, double max, Pageable pageable);
	//call sắp xếp
	@Query("SELECT o FROM Product o WHERE o.unitPrice BETWEEN ?1 AND ?2")
	List<Product> findByUnitPrice(double min, double max, Sort sort);
	
	//select count sẽ trả về Long
	@Query("SELECT count(o) FROM Product o")
	Long getCount();
	
	//trả về 1 list name => List<String>
	@Query("SELECT o.name FROM Product o")
	List<String> getName();
	
	//trả về 1 Object gồm name và unitPrice
	@Query("SELECT o.name, o.unitPrice FROM Product o")
	List<Object[]> getProperties();
	
	//trả về 1 Interface có sẵn
//	@Query("SELECT o.name AS ten, o.unitPrice AS gia FROM Product o")
//	List<ResultItem> getProperties2();
}
