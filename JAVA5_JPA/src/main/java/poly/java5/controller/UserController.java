package poly.java5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.java5.dao.UserDAO;
import poly.java5.entity.User;

@Controller
public class UserController {

	@Autowired
	UserDAO dao;
	
	
	@RequestMapping("/user/find-all")
	public String index(Model model) {
		List<User> list = dao.findAll();
		model.addAttribute("userList",list);
		return "/user/list";
	}
	@RequestMapping("/user/find-by-id/{id}")
	public String findById(Model model,
						@PathVariable("id") String username) {
		User user = dao.findById(username).get();
		model.addAttribute("user",user);
		return "/user/form";
	}
	@RequestMapping("/user/delete-by-id/{id}")
	public String deleteById(@PathVariable("id") String username) {
		dao.deleteById(username);
		return "redirect:/user/find-all";
	}
	@RequestMapping("/user/create")
	public String create(User user) {
		dao.save(user);
		return "redirect:/user/find-all";
	}
	@RequestMapping("/user/update")
	public String update(User user) {
		dao.save(user);
		return "redirect:/user/find-all";
	}

}
