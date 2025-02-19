package poly.java5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.java5.dao.ProductDAO;
import poly.java5.model.Report;

@Controller
public class ReportController {
	
	@Autowired
	ProductDAO dao;
	
	@RequestMapping("/report/inventory-by-category")
	public String inventory(Model model) {
		List<Report> items = dao.getInventoryByCategory();
		model.addAttribute("items", items);
		return "report/inventory-by-category";
	}

}
