package poly.java5.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest {

    private ProductServiceTestImpl productService;

    @BeforeMethod
    void setUp() {
        productService = new ProductServiceTestImpl(); // Tạo instance test service
    }

    @Test
    void testSearch_Success() {
        // Tìm kiếm với từ khóa hợp lệ
        List<String> result = productService.searchProduct("Rolex");
        assertEquals(1, result.size(), "Nên tìm thấy 1 sản phẩm phù hợp");
        assertEquals("Đồng hồ Rolex", result.get(0), "Sản phẩm phải đúng");
    }

    @Test
    void testSearch_Fail() {
        // Tìm kiếm với từ khóa không có trong danh sách
        List<String> result = productService.searchProduct("Apple Watch");
        assertEquals(0, result.size(), "Không nên tìm thấy sản phẩm nào");
    }
}
