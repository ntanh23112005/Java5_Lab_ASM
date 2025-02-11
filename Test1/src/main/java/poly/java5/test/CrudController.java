package poly.java5.test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CrudController {
	Map<String, Product> map = new HashMap<>();
	String[] errors = {"", "", "", "Xóa thành công"};
	
	@RequestMapping({"/crud/index", "/crud/index/{error}"})
	public String index(Model model, 
			@PathVariable("error") Optional<Integer> opt,
			@ModelAttribute("form") Product product) {
		product.setId("PRO01");
		model.addAttribute("msg", errors[opt.orElse(0)]);
		return "/crud.html";
	}
	@RequestMapping("/crud/create")
	public String create(Model model, 
			@ModelAttribute("form") Product product) {
		if(!map.containsKey(product.getId())) {
			map.put(product.getId(), product);
			model.addAttribute("msg", "Tạo mới thành công");
			return "redirect:/crud/index";
		}
		model.addAttribute("msg", "Đã tồn tại");
		return "/crud.html";
	}
	@RequestMapping("/crud/update")
	public String update(Model model,
			@ModelAttribute("form") Product product) {
		if(map.containsKey(product.getId())) {
			map.put(product.getId(), product);
			model.addAttribute("msg", "Cập nhật thành công");
		} else {
			model.addAttribute("msg", "Không tìm thấy");
		}
		return "/crud.html";
	}
	@RequestMapping("/crud/delete/{id}")
	public String delete(Model model, 
			@PathVariable("id") String id) {
		if(map.containsKey(id)) {
			map.remove(id);
			model.addAttribute("msg", "Xóa thành công");
			return "redirect:/crud/index/3";
		}
		model.addAttribute("msg", "Không tìm thấy");
		return "/crud.html";
	}
	@RequestMapping("/crud/edit/{id}")
	public String readById(Model model, 
			@PathVariable("id") String id) {
		model.addAttribute("form", map.get(id));
		return "/crud.html";
	}

	@ModelAttribute("list")
	public Collection<Product> getItems(){
		return map.values();
	}
}