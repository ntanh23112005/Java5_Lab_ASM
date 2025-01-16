package poly.java5.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ReturnController {

	@RequestMapping("/url1")
	public String method1(Model model) {
		model.addAttribute("msg1","World");
		return "return/view";
	}
	
	//method2 có thể ghi thành method3
//	@RequestMapping("/return/view")
//	public String method2(Model model) {
//		return "return/view";
//	}
	
	@RequestMapping("/return/view")
	public void method3(Model model) {
	}
	
	@RequestMapping("/url2")
	public String method4(Model model) {
		model.addAttribute("msg2","HIHI");
		return "forward:/url1";
	}
	
	@RequestMapping("/url3")
	public String method5(RedirectAttributes model) {
		//chuyển tiếp mất tác dụng (Model, sử dụng RedirectAttributes)
		//	http://localhost:8080/url1?msg2=HIHI, sử dụng getParam ở url1 để lấy nó ra
		model.addAttribute("msg2","HIHI");
		return "redirect:/url1";
	}

}
