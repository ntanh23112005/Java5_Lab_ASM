package poly.java5.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import poly.java5.entity.User;
import poly.java5.service.MailService;
import poly.java5.service.MailService.MailModel;
import poly.java5.service.UserService;

@Controller
public class MailController {

	@Autowired
	private MailService mailService;

	@Autowired
	UserService userService;

	@Autowired
	private HttpSession session;

	@GetMapping("/account/otp-change-pass/{username}")
	public String sendOtpChangePass(RedirectAttributes redirectAttributes, @PathVariable("username") String username) {
		// Tạo mã OTP
		String otp = String.format("%06d", new Random().nextInt(999999));

		// Tạo mail và gửi ngay lập tức
		try {
			MailModel mail = new MailModel();
			mail.setTo(username);
			mail.setSubject("Mã xác thực thay đổi mật khẩu");
			mail.setBody("Mã OTP của bạn là: " + otp);

			// Gửi email ngay lập tức
			mailService.send(mail);
			System.out.println("Đã gửi OTP: " + otp);
		} catch (Exception e) {
			System.out.println("Lỗi khi gửi OTP: " + e.getMessage());
			e.printStackTrace();
		}

		session.setAttribute("otp", otp);

		// Thêm thông báo OTP đã gửi và yêu cầu mở modal
		redirectAttributes.addFlashAttribute("otpSent", true);
		redirectAttributes.addFlashAttribute("otpCode", otp);
		redirectAttributes.addFlashAttribute("success", "Đã gửi mã xác thực, hãy kiểm tra email !");
		// Chuyển hướng về trang profile
		return "redirect:/account/profile";
	}

	@PostMapping("/account/change-pass")
    public String doChangePass(RedirectAttributes redirectAttributes,
    							@RequestParam("username") String username,
    							@RequestParam("newPassword") String newPass) {
    		User user = userService.findByUsername(username);
    		if(user != null) {
    			user.setPassword(newPass);
    			userService.save(user);
    			System.out.println("Lưu xuống DB thành công");
    			session.removeAttribute("otp");
    	}
    		return "redirect:/account/profile";
    }
}
