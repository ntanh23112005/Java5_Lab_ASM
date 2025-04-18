package poly.java5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import poly.java5.model.Product;

@Controller
@RequestMapping("/product")
public class ProductController {

	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("product", new Product());
		return "lab2/product";
	}
	
	@PostMapping("/save")
    public String save(
            @ModelAttribute("product") Product p, 
            Model model) {
//        Product product = new Product(name, price);
//        
//        model.addAttribute("product", product);
        return "lab2/product";
    }
}
