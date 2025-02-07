package poly.java5.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import poly.java5.service.CookieService;

@Controller
public class BeanController {

	@Autowired
	HttpServletRequest request;

	@Autowired
	List<String> names;

	@Autowired
	CookieService cookieService;
	
	@RequestMapping("/hello1")
	public String sayHello1() {
		System.out.println(request.getRequestURL());
		System.out.println(names.get(0));

		Cookie cookie = cookieService.get("poly");
		return "/demo/hello";
	}
}
