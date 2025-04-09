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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import poly.java5.constants.AppConstants;
import poly.java5.entity.Student;
import poly.java5.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	StudentService service;

	@GetMapping("/student")
	public String student(Model model) {
		List<Student> students = service.findAll();
		model.addAttribute("students", students);

		return "student";
	}

	@PostMapping("/student")
	public String updateStudent(@RequestParam("id") String id, 
								@RequestParam("fullname") String fullname,
								@RequestParam("gender") Boolean gender, 
								@RequestParam("photo") MultipartFile file,
								@RequestParam("mark") Double mark) {
		try {
			Student student = service.findByIdEquals(id);
			if (student != null) {
				student.setFullname(fullname);
				student.setGender(gender);
				student.setMark(mark);
				if (!file.isEmpty()) {
					String fileName = file.getOriginalFilename();
					Path path = Paths.get(AppConstants.IMAGE_UPLOAD_DIR + fileName);
					try {
						Files.write(path, file.getBytes());
						student.setPhoto(fileName);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				service.save(student);
			} else {
				student = new Student();
				student.setId(id);
				student.setFullname(fullname);
				student.setGender(gender);
				student.setMark(mark);
				if (!file.isEmpty()) {
					String fileName = file.getOriginalFilename();
					Path path = Paths.get(AppConstants.IMAGE_UPLOAD_DIR + fileName);
					try {
						Files.write(path, file.getBytes());
						student.setPhoto(fileName);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				service.save(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/student";
	}
}
