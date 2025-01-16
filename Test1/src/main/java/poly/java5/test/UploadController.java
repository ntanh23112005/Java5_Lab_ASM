package poly.java5.test;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/upload")
public class UploadController {
	@Autowired
	ServletContext app;
	
	@GetMapping("/form")
	public String form() {
		return "demo/upload";
	}
	
@PostMapping("/save")
public String save(Model model, 
		@RequestPart("photo") MultipartFile file) {
	//tên file, thể loại, kích thước size
	String name = file.getOriginalFilename();
	String type = file.getContentType();
	long size = file.getSize();
	
	File folder = new File(app.getRealPath("/files")); //thư mục chứa
	File newFile = new File(folder, file.getOriginalFilename());
	try {
		file.transferTo(newFile);
	} catch (IllegalStateException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return "demo/upload";
}
}
