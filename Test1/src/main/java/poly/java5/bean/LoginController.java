package poly.java5.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.java5.service.CookieService;

@Controller
public class LoginController {

	@Autowired
	CookieService cookieService;
	
	@RequestMapping("/cookie/login/form")
	public String formLogin() {
		return "/cookie/login";
	}
	
	@RequestMapping("/cookie/login/check")
	public String checkLogin( Model model,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam(name="remember", defaultValue = "false") boolean remember) {
		
		if(!username.equalsIgnoreCase("poly")) {
			model.addAttribute("msg","Sai tk rồi cường là thằng ngu");
		}else if(!password.equals("123")) {
			model.addAttribute("msg","Sai pass rồi cường là thằng ngu");
		}else {
			model.addAttribute("msg", "Ok đúng rồi");
			if(remember) { //tạo cookie
				cookieService.create("us", username, 30*24*60*60);
				cookieService.create("pw", password, 30*24*60*60);
			}else { //xóa cookie
				cookieService.delete("us");
				cookieService.delete("pw");
			}
		}
		return "/cookie/login";
	}
}
