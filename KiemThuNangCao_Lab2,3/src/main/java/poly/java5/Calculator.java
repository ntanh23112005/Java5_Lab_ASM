package poly.java5;

public class Calculator {

	// add method
	public int add(int a, int b) {
		return a + b;
	}
	
	//subtract method
	public int subtract(int a, int b) {
		return a - b;
	}
	
	//multiply method
	public int multiply(int a, int b) {
		return a * b;
	}
	
	//devide method
	public int devide(int a, int b) {
		if(b == 0) {
			throw new ArithmeticException("Không chia hết cho 0");
		}
		return a / b;
	}
}
