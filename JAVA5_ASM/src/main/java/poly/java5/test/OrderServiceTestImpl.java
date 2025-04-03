package poly.java5.test;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceTestImpl {

    private final CartServiceTestImpl cartService;
    private final Pattern emailPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    private final Pattern phonePattern = Pattern.compile("^0[0-9]{9}$");

    public OrderServiceTestImpl() {
        this.cartService = new CartServiceTestImpl();
    }

    // Đặt hàng
    public String placeOrder(String email, String phone, String paymentMethod) {
        if (cartService.getCartSize() == 0) {
            return "Giỏ hàng trống, hãy mua sắm thêm";
        }
        if (!emailPattern.matcher(email).matches()) {
            return "Vui lòng điền đúng email";
        }
        if (!phonePattern.matcher(phone).matches()) {
            return "Vui lòng điền đúng SĐT";
        }
        if (paymentMethod == null || paymentMethod.isEmpty()) {
            return "Hãy chọn phương thức thanh toán";
        }

        cartService.clearCart();
        return "Đặt thành công";
    }
}
