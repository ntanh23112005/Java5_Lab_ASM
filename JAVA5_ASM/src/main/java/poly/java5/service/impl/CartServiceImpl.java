package poly.java5.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;
import poly.java5.entity.Order;
import poly.java5.entity.OrderDetail;
import poly.java5.entity.Product;
import poly.java5.entity.User;
import poly.java5.service.CartService;
import poly.java5.service.OrderDetailsService;
import poly.java5.service.OrderService;
import poly.java5.service.ProductService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailsService orderDetailsService;

    @Autowired
    private HttpSession session;

    @Override
    public void addToCart(int productId, User user) {
        // Lấy thông tin user từ session
        user = (User) session.getAttribute("loggedUser");

        // Kiểm tra session loggedUser có tồn tại hay không
        if (user == null) {
            throw new RuntimeException("User không tồn tại. Vui lòng đăng nhập lại.");
        }

        // Kiểm tra sự tồn tại của đơn hàng chưa hoàn tất (OrderStatus = 1)
        Order order = orderService.findByUsernameAndOrderStatus(user.getUsername(), 1);

        // Nếu chưa có đơn hàng, tạo mới
        if (order == null) {
            order = Order.builder()
                    .orderStatus(1)
                    .user(user)
                    .shippingAddress("Chưa cập nhật")
                    .trangThai(true)
                    .build();
            orderService.saveOrder(order);
        }

        // Lấy thông tin sản phẩm theo ID
        Product product = productService.findByIdProduct(productId);
        if (product == null) {
            throw new RuntimeException("Sản phẩm không tồn tại.");
        }

        // Kiểm tra sản phẩm đã có trong giỏ hàng chưa
        OrderDetail orderDetail = orderDetailsService.findByOrderIdAndProductId(order.getId(), product.getId());

        if (orderDetail == null) {
            // Nếu chưa có, thêm mới vào giỏ hàng
            orderDetail = OrderDetail.builder()
                    .quantity(1)
                    .unitPrice(product.getUnitPrice())
                    .product(product)
                    .order(order)
                    .build();
        } else {
            // Nếu đã có, tăng số lượng lên 1
            orderDetail.setQuantity(orderDetail.getQuantity() + 1);
        }

        // Lưu thông tin chi tiết đơn hàng
        orderDetailsService.save(orderDetail);
        System.out.println("Thêm vào giỏ hàng thành công.");
    }

	@Override
	public void deleteFromCart(int productId) {
		orderDetailsService.deleteByProductId(productId);
	}


	@Override
	public void buyCart(String shippingAddress, User user) {
		 Order order = orderService.findByUsernameAndOrderStatus(user.getUsername(), 1);
		 order.setShippingAddress(shippingAddress);
		 order.setOrderStatus(3);
		 order.setUser(user);
		 orderService.saveOrder(order);
		 System.out.println("Thanh toán thành công");
	}
}