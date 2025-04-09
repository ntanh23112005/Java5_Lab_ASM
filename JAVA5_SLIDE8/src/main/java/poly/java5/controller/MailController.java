package poly.java5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import poly.java5.service.MailService;

@Controller
public class MailController {
	@Autowired
	MailService mailService;
	
	@GetMapping("/mail/send")
	public void sendForm() {
	}
	@PostMapping("/mail/send")
	public void sendProcess() {
		mailService.send("anhntps38560@gmail.com", "Test Mail", "Hi NghiemN, I love you!");
	}
}
