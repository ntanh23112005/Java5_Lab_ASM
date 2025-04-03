package poly.java5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.java5.dao.OrderDAO;
import poly.java5.entity.Order;
import poly.java5.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDAO dao;
	
	@Override
	public Order findByUsernameAndOrderStatus(String username, int orderStatus) {
		return dao.findByUserUsernameAndOrderStatus(username, orderStatus);
	}

	@Override
	public void saveOrder(Order order) {
		dao.save(order);
	}

}
