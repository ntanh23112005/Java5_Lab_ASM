package poly.java5.service;

import poly.java5.entity.Order;

public interface OrderService {

	void saveOrder(Order order);
	
	Order findByUsernameAndOrderStatus(String username, int orderStatus);
}
