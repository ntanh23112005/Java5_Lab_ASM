package poly.java5.service;

import poly.java5.entity.OrderDetail;

public interface OrderDetailsService {

	/**
	 * @param Hàm tìm chi tiết đặt hàng
	 * @return 1 chi tiết hóa đơn thông qua OrderId, ProductId
	 * */
	OrderDetail findByOrderIdAndProductId(Long orderId, int productId);
	
	/**
	 * @param Hàm lưu sản phẩm vào orderDetail
	 * @return 1 row trong orderDetails
	 * */
	void save(OrderDetail orderDetail);
	
	void deleteByProductId(int productId);
}
