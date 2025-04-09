package groupTest;

import org.testng.annotations.Test;

public class groupExample {

	@Test(groups = "Regression")
	public void testCaseOne() {
		System.out.println("CaseOne in Regression group");
	}
	
	@Test(groups = "Regression")
	public void testCaseTwo() {
		System.out.println("CaseTwo in Regression group");
	}
	
	@Test(groups = "Smoke Test")
	public void testCaseThree() {
		System.out.println("CaseThree in Regression group");
	}
	
	@Test(groups = "Regression")
	public void testCaseFour() {
		System.out.println("CaseFour in Regression group");
	}
}
