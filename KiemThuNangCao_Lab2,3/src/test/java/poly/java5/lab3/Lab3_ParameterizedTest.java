package poly.java5.lab3;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Lab3_ParameterizedTest {

	@ParameterizedTest
	@ValueSource(ints = {1,2,3,4,5})
	void testIsPositive(int number) {
		assertTrue(number > 0);
	}
	
	@Test
	void testWithAssertAll() {
		assertAll(
			"Group of assertions",
			() -> assertTrue("JUnit5".contains("JUnit")),
			() -> assertEquals(2, 1 + 1),
			() -> assertFalse("Hello".isEmpty())
		);
	}
}
