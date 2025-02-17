package poly.java5.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.java5.dao.ProductDAO;
import poly.java5.entity.Product;
import poly.java5.service.ProductService;

@Controller
public class QueryController {
	
	@Autowired
	ProductDAO dao;
	
	@ResponseBody // chạy hàm chứ không chuyển view
	@RequestMapping("/query/demo1")
	public void demo1() {
		Pageable pageable = PageRequest.of(2, 5);
		Page<Product> list = dao.findByUnitPrice(5, 300, pageable);
		list.getContent().forEach(p -> {
			System.out.printf(">> %s: %.01f\r\n", p.getName(), p.getUnitPrice());
		});
	}
	
	
	@ResponseBody // chạy hàm chứ không chuyển view
	@RequestMapping("/query/demo2")
	public void demo2() {
		List<Product> list = dao.findByUnitPriceBetween(5, 300);
		list.forEach(p -> {
			System.out.printf(">> %s: %.01f\r\n", p.getName(), p.getUnitPrice());
		});
	}
	
	@ResponseBody // chạy hàm chứ không chuyển view
	@RequestMapping("/query/demo3")
	public void demo3() {
		//hàm trả về List name
//		List<String> list = dao.getName();
//		list.forEach(p -> {
//			System.out.printf(">> "+ p);
//		});
		
		//hàm trả số lượng Product
//		Long count = dao.getCount();
//		System.out.println(count);
		
		//hàm trả về mảng 
//		List<Object[]> list = dao.getProperties();
//		list.forEach(p ->{
//			System.out.printf(">> " + p[0] + p[1] +"\n");
//		});
		
		
		//hàm trả về Interface có sẵn
		List<ResultItem> list = dao.getProperties2();
		list.forEach(p ->{
			System.out.println(">>" + p.getTen() + p.getGia() +"\n");
		});
	}
}
