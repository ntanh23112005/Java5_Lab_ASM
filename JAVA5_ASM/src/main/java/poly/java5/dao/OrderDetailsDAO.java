package poly.java5.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import poly.java5.entity.OrderDetail;

public interface OrderDetailsDAO extends JpaRepository<OrderDetail, Long> {

	OrderDetail findByOrderIdAndProductId(Long orderId, int productId);
	
	@Modifying
    @Query("DELETE FROM OrderDetail od WHERE od.product.id = :productId")
    void deleteByProductId(@Param("productId") int productId);

}
