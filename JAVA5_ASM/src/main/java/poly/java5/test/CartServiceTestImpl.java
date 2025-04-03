package poly.java5.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class CartServiceTestImpl {

    private final Map<String, Integer> cart = new HashMap<>();
    private final Map<String, Integer> stock = new HashMap<>();

    // Khởi tạo sản phẩm và số lượng tồn kho
    public CartServiceTestImpl() {
        stock.put("Đồng hồ Rolex", 5);
        stock.put("Đồng hồ Casio", 10);
    }

    // Thêm sản phẩm vào giỏ hàng
    public String addToCart(String product, int quantity) {
        if (!stock.containsKey(product)) {
            return "Sản phẩm không tồn tại";
        }
        if (quantity > stock.get(product)) {
            return "Số lượng vượt số tồn kho";
        }
        cart.put(product, cart.getOrDefault(product, 0) + quantity);
        return "Thêm vào giỏ hàng thành công";
    }

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    public String updateCart(String product, int quantity) {
        if (quantity <= 0) {
            return "Số lượng tối thiểu";
        }
        if (quantity > stock.get(product)) {
            return "Số lượng vượt số tồn kho";
        }
        cart.put(product, quantity);
        return "Cập nhật giỏ hàng thành công";
    }

    // Xóa sản phẩm khỏi giỏ hàng
    public String removeFromCart(String product) {
        if (cart.containsKey(product)) {
            cart.remove(product);
            return "Xóa thành công";
        }
        return "Sản phẩm không có trong giỏ hàng";
    }

    // Kiểm tra sản phẩm trong giỏ hàng
    public int getProductQuantity(String product) {
        return cart.getOrDefault(product, 0);
    }
    
    
 // Lấy số lượng sản phẩm trong giỏ hàng
    public int getCartSize() {
        return cart.size();
    }

    // Xóa toàn bộ giỏ hàng
    public void clearCart() {
        cart.clear();
    }
}
