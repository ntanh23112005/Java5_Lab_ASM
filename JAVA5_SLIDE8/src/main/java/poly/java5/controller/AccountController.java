package poly.java5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import poly.java5.entity.Const;
import poly.java5.entity.User;

@Controller
public class AccountController {

	@Autowired
	HttpSession session;
	
	@GetMapping("/account/login")
	public String loginForm() {
		return "login";
	}
	
	@PostMapping("/account/login")
	public String checkLogin() {
		User u = User.builder()
				.username("nta")
				.password("123")
				.role(true)
				.build();
		session.setAttribute("user", u);
		System.out.println("Session ID: " + session.getId());
		System.out.println("User in session: " + session.getAttribute("user"));
		if(session.getAttribute(Const.SECURED_URI) != null) {
			String uri = (String)session.getAttribute(Const.SECURED_URI);
			return "redirect:" +uri;
		}
		return "login";
	}
	
	@RequestMapping("/account/change-password")
	public String method2() {
		return "change-password";
	}

	@RequestMapping("/account/edit-profile")
	public String method3() {
		return "edit-profile";
	}
}
