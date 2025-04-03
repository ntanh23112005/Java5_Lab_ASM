package poly.java5.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.java5.entity.Order;

public interface OrderDAO extends JpaRepository<Order, Long> {

	Order findByUserUsernameAndOrderStatus(String username, int orderStatus);
	
}
