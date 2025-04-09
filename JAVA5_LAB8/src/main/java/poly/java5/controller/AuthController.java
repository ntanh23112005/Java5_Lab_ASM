package poly.java5.controller;

import jakarta.servlet.http.HttpSession;
import poly.java5.model.Account;
import poly.java5.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private HttpSession session;

    @GetMapping("/auth/login")
    public String loginForm() {
        return "login";
    }
    
    @GetMapping("/auth/home")
    public String home() {
        return "home";
    }

    @PostMapping("/auth/login")
    public String loginProcess(Model model, 
                               @RequestParam("username") String username,
                               @RequestParam("password") String password) {
        Account user = accountService.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("message", "Invalid username or password");
            return "login";
        }
        session.setAttribute("user", user);
        return "redirect:/auth/home";
    }
    
    @GetMapping("/account/edit-profile")
    public String pro() {
    	return "redirect:/auth/home";
    }
    @GetMapping("/account/change-password")
    public String pass() {
    	return "redirect:/auth/home";
    }
    @GetMapping("/order/view")
    public String vi() {
    	return "redirect:/auth/home";
    }
    @GetMapping("/admin/dashboard")
    public String ad() {
    	return "redirect:/auth/home";
    }
    
}