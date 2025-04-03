package poly.java5.test;

import java.util.HashMap;
import java.util.Map;

import poly.java5.test.UserTest;

public class AccountServiceTestImpl {

    private final Map<String, String> userStore = new HashMap<>();

    // Dữ liệu giả lập
    public AccountServiceTestImpl() {
        userStore.put("validUser", "validPass"); // Tài khoản hợp lệ
        userStore.put("existing@example.com", "validPass"); // Tài khoản đã tồn tại
    }

    public UserTest findByUsernameAndPasswordTest(String un, String pw) {
        if (userStore.containsKey(un) && userStore.get(un).equals(pw)) {
            return new UserTest(un, pw);
        }
        return null;
    }

    public String register(String email, String password) {
        if (!email.contains("@")) {
            return "Email sai định dạng";
        }
        if (userStore.containsKey(email)) {
            return "Email đã tồn tại";
        }
        if (password.length() < 6) {
            return "Mật khẩu phải hơn 6 kí tự";
        }
        userStore.put(email, password);
        return "Đăng ký thành công";
    }
}
