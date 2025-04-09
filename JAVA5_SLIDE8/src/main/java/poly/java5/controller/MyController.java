package poly.java5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

	@RequestMapping("/poly/url1")
	public String method1() {
		System.out.println("method1()");
		return "view";
	}
	@RequestMapping("/poly/url2")
	public String method2() {
		System.out.println("method2()");
		return "view";
	}
	@RequestMapping("/poly/url3")
	public String method3() {
		System.out.println("method3()");
		return "view";
	}
	
	public void run() {
		System.out.println("View");
	}
}
