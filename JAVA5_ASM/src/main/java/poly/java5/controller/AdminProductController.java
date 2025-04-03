package poly.java5.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.java5.constants.AppConstants;
import poly.java5.entity.Product;
import poly.java5.service.ProductService;
@Controller
public class AdminProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/admin/product")
	public String adminProduct(Model model) {
		List<Product> listP = productService.findByTrangThaiTrue();
		model.addAttribute("products", listP);
		List<Object[]> cateOptions = productService.findAllCategoryName();
		cateOptions.forEach(p -> {
			System.out.println(p);
		});
		model.addAttribute("cateOptions", cateOptions);
		return "admin/components_admin/table-product";
	}

	@PostMapping("/admin/product")
	public String updateProduct(RedirectAttributes redirectAttributes, @RequestParam("id") Integer id,
			@RequestParam("name") String name, @RequestParam("image") MultipartFile file,
			@RequestParam("unitPrice") Double unitPrice, @RequestParam("categoryId") String categoryId,
			@RequestParam("inStock") Integer inStock) {
		Product p = productService.findByIdProduct(id);
		try {
			p.setName(name);
			p.setUnitPrice(unitPrice);
			p.setCategory(productService.findByCategoryId(categoryId).orElse(null));
			p.setInStock(inStock);
			// Xử lý lưu ảnh
			if (!file.isEmpty()) {
				String fileName = file.getOriginalFilename();
				Path path = Paths.get(AppConstants.IMAGE_UPLOAD_DIR + fileName);
				try {
					Files.write(path, file.getBytes());
					p.setImage(fileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			productService.save(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/product";
	}
	
	@PostMapping("/admin/product/add")
	public String addProduct(@RequestParam("name") String name,
				            @RequestParam("image") MultipartFile file,
				            @RequestParam("unitPrice") Double unitPrice,
				            @RequestParam("categoryId") String categoryId,
				            @RequestParam("description") String description,
				            @RequestParam("productDate") Date productDate,
				            @RequestParam("inStock") Integer inStock) {
		try {
			Product p = new Product();
			p.setName(name);
			p.setUnitPrice(unitPrice);
			p.setCategory(productService.findByCategoryId(categoryId).orElse(null));
			p.setInStock(inStock);
			p.setDescription(description);
			p.setProductDate(productDate);
			// Xử lý lưu ảnh
			if (!file.isEmpty()) {
				String fileName = file.getOriginalFilename();
				Path path = Paths.get(AppConstants.IMAGE_UPLOAD_DIR + fileName);
				try {
					Files.write(path, file.getBytes());
					p.setImage(fileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			productService.save(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/product";
	}
	
	@GetMapping("/admin/delete-product/{id}")
	public String deleteProduct(RedirectAttributes redirectAttributes,
								@PathVariable("id") Integer id) {
		Product p = productService.findByIdProduct(id);
		try {
			p.setTrangThai(false);
			productService.save(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/product";
	}
}
