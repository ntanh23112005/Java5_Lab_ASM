package poly.java5.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.java5.model.Product;

@Controller
public class HomeController {
@RequestMapping("/home")
public String index(Model model) {
	List<Product> products = List.of(
            new Product("Laptop Dell", 15000000, "latest1.jpg"),
            new Product("iPhone 15", 23000000, "latest1.jpg"),
            new Product("Samsung S23", 21000000, "latest1.jpg"),
            new Product("MacBook Pro", 32000000, "latest1.jpg")
        );
	System.out.println(products);
        model.addAttribute("products", products);
	return "home";
}
}
