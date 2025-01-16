package poly.java5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/param")
public class ParamController {

	@GetMapping("/form")
	public String form() {
		return "lab2/form";
	}
	
	@PostMapping("/save/{x}")
	public String save(Model model, @PathVariable String x, @RequestParam String y) {
		model.addAttribute("x",x);
		model.addAttribute("y",y);
		return "lab2/form";
	}
}
