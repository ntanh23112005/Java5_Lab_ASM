package poly.java5.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductServiceTestImpl {

    private final List<String> productList = new ArrayList<>();

    // Giả lập danh sách sản phẩm
    public ProductServiceTestImpl() {
        productList.add("Đồng hồ Rolex");
        productList.add("Đồng hồ Casio");
        productList.add("Đồng hồ Seiko");
        productList.add("Đồng hồ Tissot");
    }

    // Hàm tìm kiếm sản phẩm
    public List<String> searchProduct(String keyword) {
        List<String> result = new ArrayList<>();
        for (String product : productList) {
            if (product.toLowerCase().contains(keyword.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }
}
