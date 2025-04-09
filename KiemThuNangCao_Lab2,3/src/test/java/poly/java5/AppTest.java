package poly.java5;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AppTest {

	@Test
    public void testIsEventNumber2(){
        App demo1 = new App();
        boolean result =  demo1.isEventNumber(2);
        assertTrue(result, "Number đầu vào là 2");
    }

    @Test
    public void testIsEventNumber4(){
        App demo1 = new App();
        boolean result =  demo1.isEventNumber(4);
        assertTrue(result, "Number đầu vào là 4");
    }
}
