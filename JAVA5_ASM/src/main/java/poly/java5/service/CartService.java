package poly.java5.service;

import poly.java5.entity.User;

public interface CartService {
	
	/**
	 * @param Hàm add Product vào giỏ hàng
	 * @return none ( vào DB orderDetail, order)
	 * */
	void addToCart(int productId, User user);
	
	/**
	 * @param Hàm xóa 1 Product khỏi giỏ hàng
	 * @return xóa sản phẩm ( trong DB orderDetail)
	 * */
	void deleteFromCart(int productId);
	
	/**
	 * @param Tiến hành thanh toán
	 * @return Update orderStatus, Save orderDetail
	 * */
	void buyCart(String shippingAddress, User user);
}
