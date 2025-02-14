package poly.java5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.java5.dao.UserDAO;
import poly.java5.entity.User;
import poly.java5.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	
	@RequestMapping("/user/find-all")
	public String index(Model model) {
		List<User> list = userService.findAll();
		model.addAttribute("userList",list);
		return "/user/list";
	}
	@RequestMapping("/user/find-by-id/{id}")
	public String findById(Model model,
						@PathVariable("id") String username) {
		User user = userService.findByUsername(username);
		model.addAttribute("user",user);
		return "/user/form";
	}
	@RequestMapping("/user/delete-by-id/{id}")
	public String deleteById(@PathVariable("id") String username) {
		userService.deleteByUsername(username);
		return "redirect:/user/find-all";
	}
	@RequestMapping("/user/create")
	public String create(User user) {
		userService.create(user);
		return "redirect:/user/find-all";
	}
	@RequestMapping("/user/update")
	public String update(User user) {
		userService.update(user);
		return "redirect:/user/find-all";
	}

}
