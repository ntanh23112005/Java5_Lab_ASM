package poly.java5.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	HttpServletRequest req;
	
	@GetMapping("/form")
	public String form() {
		return "lab1/login";
	}
	
	@PostMapping("/check")
	public String checkLogin(Model model) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		if("poly".equals(username) && "123".equals(password)) {
			model.addAttribute("message", "Login successfull !");
		}else {
			model.addAttribute("message","Username or password is incorrect !");
		}
		
		return "lab1/login";
	}
}
