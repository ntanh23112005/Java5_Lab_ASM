package poly.java5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import poly.java5.entity.Product;
import poly.java5.entity.User;
import poly.java5.service.CartService;
import poly.java5.service.ProductService;

@Controller
public class OrderController {

	@Autowired
	CartService cartService;
	@Autowired
	ProductService productService;
	@Autowired
	HttpSession session;
	
	@GetMapping("add-cart/{id}")
	public String addCart(RedirectAttributes redirectAttributes,
						@PathVariable("id") Integer productId
						) {
		Product product = productService.findByIdProduct(productId);
		User user = (User)session.getAttribute("loggedUser");
		cartService.addToCart(productId, user);
		redirectAttributes.addFlashAttribute("success", "Bạn đã thêm vào giỏ hàng sản phẩm " + product.getName());
		return "redirect:/trang-chu";
	}
	
	@GetMapping("/delete-cart/{id}")
	public String deleteFromCart(RedirectAttributes redirectAttributes
								, @PathVariable("id") Integer productId) {
		cartService.deleteFromCart(productId);
		redirectAttributes.addFlashAttribute("success", "Xóa thành công");
		return "redirect:/gio-hang";
	}
	
	@PostMapping("/buy")
	public String buy(RedirectAttributes redirectAttributes
						, @RequestParam("shippingAddress") String shippingAddress) {
		User user = (User)session.getAttribute("loggedUser");
		cartService.buyCart(shippingAddress, user);
		redirectAttributes.addFlashAttribute("success", "Cảm ơn bạn đã tin dùng sản phẩm");
		return "redirect:/trang-chu";
	}
}
