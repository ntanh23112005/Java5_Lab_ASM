package poly.java5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.java5.entity.Car;
import poly.java5.service.CarService;

@Controller
public class CarController {

	@Autowired
	CarService service;

	@GetMapping("/home")
	public String getMethodName(Model model) {
		List<Car> cars = service.findAll();
		model.addAttribute("cars", cars);
		return "test";
	}

	@PostMapping("/search")
	public String search(@RequestParam("search") String name, Model model) {
		if (name == null || name.equals("") || name.isEmpty()) {
			List<Car> cars = service.findAll();
			model.addAttribute("cars", cars);
			return "test";
		} else {
			List<Car> cars = service.findByNameLike("%" + name + "%");
			cars.forEach(c ->{
				System.out.println(c);
			});
			model.addAttribute("cars", cars);
			return "test";
		}
	}
	
	@PostMapping("/car")
	public String carAddUpdate(@RequestParam("name") String name,
								@RequestParam("price") Integer price,
								Model model) {
		Car car = service.findByName(name);
		if(car != null) {
			car.setPrice(price);
			service.saveCar(car);
			System.out.println("Cập nhật thành công");
		}else {
			car = new Car();
			car.setName(name);
			car.setPrice(price);
			service.saveCar(car);
			System.out.println("Lưu thành công");
		}
		return "redirect:/home";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteCar(@PathVariable("id") Integer id) {
		service.deleteById(id);
		return "redirect:/home";
	}
	
}
