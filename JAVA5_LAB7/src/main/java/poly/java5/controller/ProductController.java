package poly.java5.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.java5.dao.ProductDAO;
import poly.java5.entity.Product;
import poly.java5.service.SessionService;

@Controller
public class ProductController {

	@Autowired
	ProductDAO dao;

	@Autowired
	SessionService session;

	@RequestMapping("/product/search")
	public String search(Model model, @RequestParam("min") Optional<Double> min,
			@RequestParam("max") Optional<Double> max) {
		double minPrice = min.orElse(Double.MIN_VALUE);
		double maxPrice = max.orElse(Double.MAX_VALUE);

		List<Product> items = dao.findByUnitPriceBetween(minPrice, maxPrice);
//		System.out.println(!items.isEmpty());
		model.addAttribute("items", items);
		return "product/search";
	}

	@RequestMapping("/product/search-and-page")
	public String searchAndPage(Model model, @RequestParam("keywords") Optional<String> kw,
			@RequestParam("p") Optional<Integer> p) {
		String kwords = kw.orElse(session.get("keywords", ""));
		session.set("keywords", kwords);
		
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<Product> page = dao.findAllByNameLike("%" + kwords + "%", pageable);
		
		model.addAttribute("page", page);
		return "product/search-and-page";
	}

}
