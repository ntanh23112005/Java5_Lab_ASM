package poly.java5;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CalculatorTest {

	Calculator calc = new Calculator();
	
	@Test
	public void testAssertNotEquals() {
		int result = calc.add(2, 3);
		assertNotEquals(result == 6, "Kết quả không được bằng 6");
	}
	
	@Test
	public void testAssertTrue() {
		int result = calc.multiply(3, 4);
		assertTrue(result == 12, "Kết quả phép nhân phải đúng");
	}
	
	@Test
	public void testAssertFalse() {
		int result = calc.subtract(10, 5);
		assertFalse(result == 0, "Kết quả phép trừ không được bằng 0");
	}
	
	@Test
	public void testAssertNull() {
		String nullValue = null;
		assertNull(nullValue, "Giá trị phải là null");
	}
}
