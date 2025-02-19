package poly.java5.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.java5.model.Product;

@Controller
public class HomeController {
	@RequestMapping("/trang-chu")
	public String index(Model model) {
		List<Product> products = List.of(new Product("Đồng Hồ Nam Orient Star Skeleton Mặt Hề RK-AV0114E", 15000000, "nambomba.jpg"),
				new Product("Đồng Hồ Nam Hublot Classic Fusion Automatic Titanium King Gold Bezel", 23000000, "donghonamhubot.jpg"),
				new Product("Đồng Hồ Nam Orient Star Skeleton Mặt Hề RK-AV0114E", 21000000, "nambomba.jpg"),
				new Product("Đồng Hồ Nam Hublot Classic Fusion Automatic Titanium King Gold Bezel", 32000000, "donghonamhubot.jpg"));
		System.out.println(products);
		model.addAttribute("products", products);
		return "home";
	}
	
	@RequestMapping("/gio-hang")
	public String cartView() {
		return "cart";
	}
	
	@RequestMapping("/chi-tiet-san-pham")
	public String detail() {
		return "detail";
	}
}
