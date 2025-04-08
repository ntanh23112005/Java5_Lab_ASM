package poly.java5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import poly.java5.entity.Order;
import poly.java5.entity.Product;
import poly.java5.entity.User;
import poly.java5.service.OrderService;
import poly.java5.service.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping("/test")
	@ResponseBody
	public void test() {
		List<Product> list = productService.findByTrangThaiTrue();
		list.forEach(p ->{
			System.out.println(p.getName());
		});
	}
	
	@RequestMapping("/trang-chu")
	public String index(Model model) {
		List<Product> products = productService.findByTrangThaiTrue();
		
		User user = (User)session.getAttribute("loggedUser");
		if(user != null) {
			// Kiểm tra sự tồn tại của đơn hàng chưa hoàn tất (OrderStatus = 1)
	        Order order = orderService.findByUsernameAndOrderStatus(user.getUsername(), 1);

	        // Nếu chưa có đơn hàng, tạo mới
	        if (order == null) {
	            order = Order.builder()
	                    .orderStatus(1)
	                    .user(user)
	                    .shippingAddress("Chưa cập nhật")
	                    .trangThai(true)
	                    .build();
	            orderService.saveOrder(order);
	        }
	        model.addAttribute("products", products);
			return "home";
		}else {
			model.addAttribute("products", products);
			return "home";
		}
        
	}
	
	@RequestMapping("/gio-hang")
	public String cartView(Model model, RedirectAttributes redirectAttributes) {
		
		User user = (User)session.getAttribute("loggedUser");

		if (user == null){
			redirectAttributes.addFlashAttribute("error", "You must login first !");
			return "redirect:/account/login";
		}
		 // Kiểm tra sự tồn tại của đơn hàng chưa hoàn tất (OrderStatus = 1)
        Order order = orderService.findByUsernameAndOrderStatus(user.getUsername(), 1);

        // Nếu chưa có đơn hàng, tạo mới
        if (order == null) {
            order = Order.builder()
                    .orderStatus(1)
                    .user(user)
                    .shippingAddress("Chưa cập nhật")
                    .trangThai(true)
                    .build();
            orderService.saveOrder(order);
        }
		
		model.addAttribute("order", order);
		return "cart";
	}
	
	@RequestMapping("/chi-tiet-san-pham/{id}")
	public String detail(Model model,
					@PathVariable("id") int id) {
		Product p = productService.findByIdProduct(id);
		System.out.println(p);
		System.out.println("UnitPrice: " + p.getUnitPrice());
		model.addAttribute("p",p);
		return "detail";
	}
	
}
