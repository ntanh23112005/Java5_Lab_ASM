package poly.java5.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
@RequestMapping("/poly/hello")
public String sayHello(Model model) {
	model.addAttribute("title","Đây là tiêu đề");
	model.addAttribute("subject", "Spring Boot");
	return "/demo/hello.html";
}
}
