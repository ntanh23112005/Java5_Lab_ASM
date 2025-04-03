package poly.java5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
	
	@GetMapping("/403")
	public String err403(Model model) {
		model.addAttribute("error", "Bạn không có quyền quản trị vào trang admin");
		return "error/403";
	}
}
