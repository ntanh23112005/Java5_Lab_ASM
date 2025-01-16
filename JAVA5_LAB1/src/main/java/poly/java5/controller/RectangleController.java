package poly.java5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/math")
public class RectangleController {

	@Autowired
	HttpServletRequest req;
	
	@GetMapping("/form")
	public String form() {
		return "lab1/cvdt";
	}
	
	@PostMapping("/action")
	public String doMathRectangle(Model model) {
		double width = Double.valueOf(req.getParameter("width"));
		double height = Double.valueOf(req.getParameter("height"));
		
		double chuvi = (width + height) * 2;
		double dientich = width * height; 

		model.addAttribute("chuvi", chuvi);
		model.addAttribute("dientich", dientich);
		
		return "lab1/cvdt";
		
	}
}
