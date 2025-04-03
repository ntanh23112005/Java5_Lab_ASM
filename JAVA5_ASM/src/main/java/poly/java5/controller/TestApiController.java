package poly.java5.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import poly.java5.dao.ProductDAO;
import poly.java5.dto.ProductDTO;
import poly.java5.entity.Category;
import poly.java5.entity.Product;
import poly.java5.mapper.ProductMapper;
import poly.java5.service.CategoryService;
import poly.java5.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class TestApiController {

	@Autowired
	ProductMapper mapper;
	
	@Autowired
	ProductService service;
	@Autowired
	ProductDAO dao;
	@Autowired
	CategoryService cateService;
	
	@GetMapping("/categories")
	public List<Category> getAllCategories(){
		return cateService.findAll();
	}
	
	@GetMapping
	public List<ProductDTO> getAll(){
		List<Product> products = service.findAll();
		return products.stream()
				.map(mapper::productToDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public Product getById(@PathVariable("id") Integer id) {
		return service.findByIdProduct(id);
	}
	
	@PostMapping
	public Product create(@RequestBody Product product) {
		Category cate = cateService.findById(product.getCategory().getId());
		product.setCategory(cate);
		return service.save(product);
	}
	
	@PutMapping("/{id}")
	public Product update(@PathVariable("id") Integer id, @RequestBody ProductDTO product) {
	    Product existingProduct = service.findByIdProduct(id);
	    if (existingProduct == null) {
	        throw new RuntimeException("Sản phẩm không tồn tại!");
	    }

	    // Kiểm tra xem category có null không
	    if (product.getCategoryId() == null) {
	        throw new RuntimeException("Danh mục của sản phẩm không hợp lệ!");
	    }

	    Category category = cateService.findById(product.getCategoryId());
	    if (category == null) {
	        throw new RuntimeException("Danh mục không tồn tại!");
	    }

	    existingProduct.setName(product.getName());
	    existingProduct.setUnitPrice(product.getUnitPrice());
	    existingProduct.setCategory(category);

	    return service.save(existingProduct);
	}


	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") Integer id){
		Product p = service.findByIdProduct(id);
		if(p == null){
			throw new RuntimeException("Sản phẩm đéo có");
		}
		dao.delete(p);
	}

}
