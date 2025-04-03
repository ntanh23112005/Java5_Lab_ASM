package poly.java5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import poly.java5.dao.OrderDetailsDAO;
import poly.java5.entity.OrderDetail;
import poly.java5.service.OrderDetailsService;
@Service
@Transactional
public class OrderDetailsServiceImpl implements OrderDetailsService {

	@Autowired
	OrderDetailsDAO dao;
	
	@Override
	public OrderDetail findByOrderIdAndProductId(Long orderId, int productId) {
		return dao.findByOrderIdAndProductId(orderId, productId);
	}

	@Override
	public void save(OrderDetail orderDetail) {
		dao.save(orderDetail);
	}

	@Override
	public void deleteByProductId(int productId) {
		dao.deleteByProductId(productId);
	}

}
