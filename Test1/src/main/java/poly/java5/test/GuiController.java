package poly.java5.test;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GuiController {
@RequestMapping("/gui/demo1")
public String sayHello(Model model) {
	model.addAttribute("id", "nta");
	model.addAttribute("name", "Nguyễn tHế aNh");
	model.addAttribute("gender", true);
	model.addAttribute("birthday", new Date());
	model.addAttribute("photo", "photo.jpg");
	model.addAttribute("salary", 50000000);
	return "/gui/page1";
}

@RequestMapping("/gui/demo2")
public String sayHello2(Model model) {
	Staff st = new Staff();
	st.setId("NV001");
	st.setName("NTA");
	st.setSalary(5000000.0);
	model.addAttribute("nvPoly", st);
	return "/gui/page2";
}
}
