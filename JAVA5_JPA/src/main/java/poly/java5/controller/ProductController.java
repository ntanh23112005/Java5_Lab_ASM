package poly.java5.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.java5.entity.Product;
import poly.java5.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping({"/product/page", "/product/page/{number}"})
	public String paginate(Model model,
						@PathVariable("number") Optional<Integer> number) {
		int pageNumber = number.orElse(0);
		Page<Product> page = productService.getPage(pageNumber);
		model.addAttribute("page",page);
		return "/paginate.html";
	}
	@RequestMapping("/product/sort")
	public String sort(Model model) {
		List<Product> list = productService.getSortByUnitPrice();
		model.addAttribute("list",list);
		return "/sort.html";
	}
	
}
