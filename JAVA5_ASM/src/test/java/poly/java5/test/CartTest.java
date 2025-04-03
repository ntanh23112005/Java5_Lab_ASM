package poly.java5.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;

public class CartTest {

    private CartServiceTestImpl cartService;

    @BeforeMethod
    void setUp() {
        cartService = new CartServiceTestImpl(); // Tạo instance test service
    }

    @Test
    void testAddCart_Success() {
        // Thêm sản phẩm hợp lệ
        String result = cartService.addToCart("Đồng hồ Rolex", 1);
        assertEquals("Thêm vào giỏ hàng thành công", result);
        assertEquals(1, cartService.getProductQuantity("Đồng hồ Rolex"));
    }

    @Test
    void testAddCart_Success_ExistingProduct() {
        // Thêm sản phẩm đã có trong giỏ hàng => Tăng số lượng
        cartService.addToCart("Đồng hồ Casio", 1);
        String result = cartService.addToCart("Đồng hồ Casio", 1);
        assertEquals("Thêm vào giỏ hàng thành công", result);
        assertEquals(2, cartService.getProductQuantity("Đồng hồ Casio"));
    }

    @Test
    void testAddCart_Fail_ExceedStock() {
        // Thêm sản phẩm quá số lượng tồn kho
        String result = cartService.addToCart("Đồng hồ Rolex", 1000);
        assertEquals("Số lượng vượt số tồn kho", result);
    }

    @Test
    void testUpdateCart_Fail_UpdateToZero() {
        // Cập nhật số lượng về 0 => Báo lỗi
        cartService.addToCart("Đồng hồ Rolex", 1);
        String result = cartService.updateCart("Đồng hồ Rolex", 0);
        assertEquals("Số lượng tối thiểu", result);
    }

    @Test
    void testDeleteCart_Success() {
        // Xóa sản phẩm khỏi giỏ hàng
        cartService.addToCart("Đồng hồ Casio", 1);
        String result = cartService.removeFromCart("Đồng hồ Casio");
        assertEquals("Xóa thành công", result);
        assertEquals(0, cartService.getProductQuantity("Đồng hồ Casio"));
    }
}
