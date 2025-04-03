package poly.java5.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrderTest {

    private OrderServiceTestImpl orderService;
    private CartServiceTestImpl cartService;

    @BeforeMethod
    void setUp() {
        orderService = new OrderServiceTestImpl();
        cartService = new CartServiceTestImpl();
    }

    @Test
    void testOrder_Success() {
        // Thêm sản phẩm vào giỏ hàng trước khi đặt hàng
        cartService.addToCart("Đồng hồ Rolex", 1);
        String result = orderService.placeOrder("user@example.com", "0123456789", "Credit Card");
        assertEquals("Đặt thành công", result);
    }

    @Test
    void testOrder_Fail_InvalidEmail() {
        cartService.addToCart("Đồng hồ Casio", 1);
        String result = orderService.placeOrder("invalid-email", "0123456789", "Credit Card");
        assertEquals("Vui lòng điền đúng email", result);
    }

    @Test
    void testOrder_Fail_InvalidPhoneNumber() {
        cartService.addToCart("Đồng hồ Rolex", 1);
        String result = orderService.placeOrder("user@example.com", "123456", "Credit Card");
        assertEquals("Vui lòng điền đúng SĐT", result);
    }

    @Test
    void testOrder_Fail_EmptyCart() {
        // Giỏ hàng trống
        String result = orderService.placeOrder("user@example.com", "0123456789", "Credit Card");
        assertEquals("Giỏ hàng trống, hãy mua sắm thêm", result);
    }

    @Test
    void testOrder_Fail_NoPaymentMethod() {
        cartService.addToCart("Đồng hồ Casio", 1);
        String result = orderService.placeOrder("user@example.com", "0123456789", null);
        assertEquals("Hãy chọn phương thức thanh toán", result);
    }
}
