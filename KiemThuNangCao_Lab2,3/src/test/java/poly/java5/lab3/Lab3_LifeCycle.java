package poly.java5.lab3;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Lab3_LifeCycle {

	// Beforce All là phương thức chỉ được chạy 1 lần trước khi bắt đầu tất cả các bài kiểm tra
	@BeforeAll
	static void initAll() {
		System.out.println("@BeforeAll - Run before all methods once");
	}
	
	// Beforce Each là phương thức được gọi trước mỗi bài kiểm tra
	@BeforeEach
	void init() {
		System.out.println("	@BeforeEach - Run beforce each test methods");
	}
	
	// @Test là phương thức kiểm tra nơi bạn thực thi các bài kiểm tra cụ thể
	// Với trường hợp này là trường hợp test có tên hiển thị là " First Tes"
	@DisplayName("First test")
	@Test
	void testMethod1() {
		System.out.println("		Test method 1");
	}
	
	// Đây là 1 phương thức test bị vô hiệu hóa ( @Disabled)
	// Không được chạy trong suốt quá trình test
	@Test
	@Disabled
	void testMethod2() {
		System.out.println("		Test method 2");
	}
	
	// Đây là 1 phương thức test được chạy bình thường
	@Test
	void testMethod3() {
		System.out.println("		Test method 3");
	}
	
	// @AfterEach là phương thức được gọi sau mỗi hàm test
	// Đây là nơi có thể dùng để dọn tài nguyên hoặc đối tượng trong bài test
	@AfterEach
	void tearDown() {
		System.out.println("	@AfterEach - Run after each test methods");
	}
	
	// @AfterAll là phương thức chỉ đc gọi 1 lần sau khi các bài test hoàn thành
	// Sử dụng phương thức này để giải phóng tài nguyên toán cục như jobManager
	@AfterAll
	static void tearDownAll() {
		System.out.println("@AfterAll - Run after all test methods once");
	}
}
