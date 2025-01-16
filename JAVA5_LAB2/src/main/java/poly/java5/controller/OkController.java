package poly.java5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ctrl")
public class OkController {
	
	@PostMapping("/ok")
	public String m1(Model model, @RequestParam(required = false) String x) {
		if(x != null) {
			model.addAttribute("method","Đã click OK3 - Post method with X");
		}else {
		model.addAttribute("method", "Đã click OK1 - Post method");
		}
		return "lab2/ok";
	}
	
	@GetMapping("/ok")
	public String m2(Model model) {
		model.addAttribute("method", "Đã click OK2 - Get method");
		return "lab2/ok";
	}
	
	
}
