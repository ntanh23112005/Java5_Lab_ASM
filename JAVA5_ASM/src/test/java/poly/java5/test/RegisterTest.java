package poly.java5.test;

import static org.testng.Assert.assertEquals;

//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTest {
    
	private AccountServiceTestImpl accountService;

    @BeforeMethod
    void setUp() {
        accountService = new AccountServiceTestImpl(); // Khởi tạo service
    }

    @Test
    void testRegister_Success() {
        String result = accountService.register("newuser@example.com", "validPass");
        assertEquals(result, "Đăng ký thành công", "Đăng ký nên thành công với email hợp lệ và mật khẩu đủ dài");
    }

    @Test
    void testRegister_Fail_EmailExists() {
        String result = accountService.register("existing@example.com", "validPass");
        assertEquals(result, "Email đã tồn tại", "Không nên cho phép đăng ký với email đã tồn tại");
    }

    @Test
    void testRegister_Fail_InvalidPassword() {
        String result = accountService.register("newuser@example.com", "123");
        assertEquals(result, "Mật khẩu phải hơn 6 kí tự", "Mật khẩu quá ngắn không được chấp nhận");
    }

    @Test
    void testRegister_Fail_InvalidEmailFormat() {
        String result = accountService.register("invalidemail", "validPass");
        assertEquals(result, "Email sai định dạng", "Email không hợp lệ không được phép đăng ký");
    }
}
