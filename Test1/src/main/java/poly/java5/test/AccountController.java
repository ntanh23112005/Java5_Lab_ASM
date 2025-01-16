package poly.java5.test;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class AccountController {

	@RequestMapping({"/account","/account/{username}"})
	public String method1(@PathVariable("username")Optional<String> id) {
		System.out.println(id.orElse("FPT"));
		return "demo/page.html";
	}
	
	@RequestMapping("/poly/user")
	public String param(
			@RequestParam("username")Optional<String> id,
			@CookieValue("user") String user) {
//		System.out.println(id.orElse("FPOLY"));
		System.out.println(user);
		return "demo/page.html";
	}
	
	
	@RequestMapping("/share/data")
	public String share(Model model) {
		model.addAttribute("msg","Hello");
//		model.addAttribute("xyz",getXYZ());
//		model.addAttribute("user1",getXYZ1());
		return "demo/page.html";
	}
	
	@ModelAttribute("xyz")
	public String getXYZ() {
		return "Thế Anh";
	}
	
//	@ModelAttribute("user1")
//	public User getXYZ1() {
//	    return User.builder()
//	            .username("ntanh")
//	            .password("123")
//	            .build();
//	}
}
