package poly.java5.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.java5.model.Product;

@Controller
@RequestMapping("/product2")
public class Product2Controller {

	@GetMapping("/form")
	public String form(Model model) {
		Product pr = new Product();
		pr.setName("Iphone 1");
		pr.setPrice(30000.0);
		model.addAttribute("product", pr);
		return "lab2/product";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("product") Product p) {
		return "lab2/product";
	}
	
	@ModelAttribute("items")
	public List<Product> getList(){
		return Arrays.asList(new Product("A", 1.0), new Product("B", 12.0));
	}
}
