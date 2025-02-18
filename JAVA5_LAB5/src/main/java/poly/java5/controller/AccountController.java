package poly.java5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.java5.service.CookieService;
import poly.java5.service.ParamService;
import poly.java5.service.SessionService;

@Controller
public class AccountController {
	@Autowired
	CookieService cookieService;

	@Autowired
	ParamService paramService;

	@Autowired
	SessionService sessionService;

	@GetMapping("/account/login")
	public String login1() {
		return "account/login";
	}

	@PostMapping("/account/login")
	public String login2(Model model) {
		String un = paramService.getString("username", "");
		String pw = paramService.getString("password", "");
		boolean rm = paramService.getBoolean("remember", false);

		if ("poly".equalsIgnoreCase(un) && "123".equals(pw)) {
			sessionService.set("username", un);

			if (rm) {
				cookieService.add("user", un, 10);
			} else {
				cookieService.remove("user");
			}
			return "/account/login";
		} else {
			model.addAttribute("message", "Invalid username or password");
			return "/account/login";
		}
	}
}
