package poly.java5.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import poly.java5.entity.Authority;
import poly.java5.entity.User;
import poly.java5.service.AuthorityService;
import poly.java5.service.UserService;

@Controller
public class AdminUserController {

	@Autowired
	UserService userService;
	@Autowired
	AuthorityService authorityService;
	
	@GetMapping("/admin/user")
	public String adminUser(Model model) {
		List<User> listU = userService.findAll();
		model.addAttribute("users", listU);
		return "admin/components_admin/table-user";
	}

	@PostMapping("/admin/user/add")
	public String addUser(RedirectAttributes redirectAttributes, 
			@RequestParam("username") String username,
			@RequestParam("fullname") String fullname, 
			@RequestParam("photo") MultipartFile file,
			@RequestParam("mobile") String mobile, 
			@RequestParam("role") String role,
			@RequestParam("enabled") Boolean enabled) {
		try {
			User u = new User();
			
			u.setUsername(username);
			u.setPassword("123");
			u.setFullname(fullname);
			u.setEnabled(enabled);
			u.setMobile(mobile);
			u.setTrangThai(true);
			// Xử lý lưu ảnh
			if (!file.isEmpty()) {
				String fileName = file.getOriginalFilename();
				Path path = Paths.get(AppConstants.IMAGE_UPLOAD_DIR + fileName);
				try {
					Files.write(path, file.getBytes());
					u.setPhoto(fileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			userService.save(u);
			
			Authority a = new Authority();
			a.setRole(userService.findByIdEquals(role));
			a.setUser(userService.findByUsername(username));
			authorityService.save(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/user";
	}
	
	@PostMapping("/admin/user/update")
	public String updateUser(RedirectAttributes redirectAttributes, 
	        @RequestParam("username") String username,
	        @RequestParam("fullname") String fullname, 
	        @RequestParam("photo") MultipartFile file,
	        @RequestParam("mobile") String mobile, 
	        @RequestParam("role") String role,
	        @RequestParam("enabled") Boolean enabled,
	        @RequestParam("trangThai") Boolean trangthai) {
	    try {
	        User u = userService.findByUsername(username);
	        
	        // Cập nhật thông tin User
	        u.setFullname(fullname);
	        u.setEnabled(enabled);
	        u.setMobile(mobile);
	        u.setTrangThai(trangthai);
	        
	        // Xử lý lưu ảnh
	        if (!file.isEmpty()) {
	            String fileName = file.getOriginalFilename();
	            Path path = Paths.get(AppConstants.IMAGE_UPLOAD_DIR + fileName);
	            try {
	                Files.write(path, file.getBytes());
	                u.setPhoto(fileName);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        
	        userService.save(u);
	        
	        // Kiểm tra và cập nhật Authority
	        Authority a = authorityService.findByUserUsername(username);  // Sửa tên phương thức để tìm Authority cho User
	        if (a != null) {
	            // Nếu Authority đã tồn tại, chỉ cần cập nhật role
	            a.setRole(userService.findByIdEquals(role));  // Cập nhật Role
	            authorityService.save(a);
	        } else {
	            // Nếu không có Authority, tạo mới Authority
	            a = new Authority();
	            a.setRole(userService.findByIdEquals(role));  // Lấy Role từ roleService
	            a.setUser(u);  // Gán User cho Authority
	            authorityService.save(a);
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return "redirect:/admin/user";
	}
	
	@GetMapping("/admin/delete-user/{username}")
	public String deleteUser(RedirectAttributes redirectAttributes,
								@PathVariable("username") String username) {
		User u = userService.findByUsername(username);
		u.setTrangThai(false);
		userService.save(u);
		return "redirect:/admin/user";
	}

}
