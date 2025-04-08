package poly.java5.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import poly.java5.constants.AppConstants;
import poly.java5.entity.User;
import poly.java5.service.AccountService;
import poly.java5.service.MailService;
import poly.java5.service.UserService;

@Controller
public class AccountController {

    @Autowired
    MailService mailService;

    @Autowired
    AccountService service;

    @Autowired
    UserService userService;

    @Autowired
    HttpSession session;

    @GetMapping("/account/login")
    public String login() {
        return "login";
    }

    @PostMapping("/account/login")
    public String checkLogin(RedirectAttributes redirectAttributes, @RequestParam("username") String us,
                             @RequestParam("password") String pw) {
        User u = service.findByUsernameAndPassword(us, pw);
        if (u != null) {
//			System.out.println("Đăng nhập thành công " +u.getFullname());
            redirectAttributes.addFlashAttribute("success", "Log in success ! Hi " + u.getUsername());
            session.setAttribute("loggedUser", u);
            return "redirect:/trang-chu";
        } else if (us.equals("") && pw.equals("")) {
            redirectAttributes.addFlashAttribute("error", "Enter your account to login !");
            return "redirect:/account/login";
        } else {
            System.out.println("Thất bại");
            redirectAttributes.addFlashAttribute("error", "Wrong username or pass word, try again !");
            return "redirect:/account/login";
        }
    }

    @GetMapping("/account/logout")
    public String handleLogOut(RedirectAttributes redirectAttributes) {
//		System.out.println("Đăng xuất thành công");
        session.removeAttribute("loggedUser");
        session.removeAttribute("otp");
        session.invalidate();
        redirectAttributes.addFlashAttribute("success", "You have logout successfully !");
        return "redirect:/account/login";
    }

    @GetMapping("/account/profile")
    public String profile(Model model) {
        User user = (User) session.getAttribute("loggedUser");
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/account/update-profile")
    public String updateProfile(RedirectAttributes redirectAttributes, @RequestParam("fullname") String fullname,
                                @RequestParam("mobile") String mobile, @RequestParam("photo") MultipartFile file, Model model) {

        User user = userService.findByUsername(((User) session.getAttribute("loggedUser")).getUsername());
        if (user != null) {
            user.setFullname(fullname);
            user.setMobile(mobile);

            // Xử lý lưu ảnh
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                Path path = Paths.get(AppConstants.IMAGE_UPLOAD_DIR + fileName);
                try {
                    Files.write(path, file.getBytes());
                    user.setPhoto(fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            userService.save(user);
            System.out.println("Cập nhật thành công");

            session.setAttribute("loggedUser", user);
            model.addAttribute("user", user);

            redirectAttributes.addFlashAttribute("success", AppConstants.SUCCESS_MESSAGE);
        } else {
            redirectAttributes.addFlashAttribute("error", AppConstants.ERROR_MESSAGE);
        }
        return "/profile";
    }

    @GetMapping("/account/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/account/register")
    public String doRegister(RedirectAttributes redirectAttributes
            , @RequestParam("fullName") String fullname
            , @RequestParam("mobile") String mobile
            , @RequestParam("email") String email
            , @RequestParam("password") String password
            , @RequestParam("confirmPassword") String confirmPassword
            , @RequestParam("photo") MultipartFile file
            , Model model) {
        User checkUser = userService.findByUsername(email);
        String activationToken = UUID.randomUUID().toString();

        String mobileRegex = ".*[a-zA-Z].*";
        String nameRegex = "^[^\\d]+$";

        if (fullname.equals("") || mobile.equals("") || email.equals("") || password.equals("") || confirmPassword.equals("")) {
            model.addAttribute("error", "Please enter all information !");
            return "register";
        }else if (!fullname.matches(nameRegex)) {
            model.addAttribute("error", "Full name not exits number !");
            return "register";
        }
        else if (mobile.matches(mobileRegex)) {
            model.addAttribute("error", "Number not exits alphabet !");
            return "register";
        } else if (checkUser != null) {
            model.addAttribute("error", "Email has been exits !");
            return "register";
        } else if (password.length() < 6) {
            model.addAttribute("error", "Password should have more than 6 characters !");
            return "register";
        } else if (!password.toString().equals(confirmPassword)) {
            model.addAttribute("error", "Confirm password is not match !");
            return "register";
        } else {
            User user = new User();
            user.setUsername(email);
            user.setFullname(fullname);
            user.setMobile(mobile);
            user.setPassword(password);
            user.setEnabled(false);
            // Xử lý lưu ảnh
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                Path path = Paths.get(AppConstants.IMAGE_UPLOAD_DIR + fileName);
                try {
                    Files.write(path, file.getBytes());
                    user.setPhoto(fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            user.setTrangThai(false);
            user.setActivationtoken(activationToken);
            userService.save(user);

            // Gửi email kích hoạt
            String activationLink = "http://localhost:8080/account/activate?token=" + activationToken;
            String subject = "Kích hoạt tài khoản của bạn";
            String body = "Chào " + fullname + ",\n\n"
                    + "Cảm ơn bạn đã đăng ký. Vui lòng nhấn vào liên kết dưới đây để kích hoạt tài khoản của bạn:\n"
                    + activationLink + "\n\n";
            mailService.send(user.getUsername(), subject, body);


            model.addAttribute("success", "Register success, check your email to activate your account !");
            return "login";
        }
    }


    @GetMapping("/account/activate")
    public String activateToken(@RequestParam("token") String token,
                                RedirectAttributes redirectAttributes,
                                Model model) {
        User user = userService.findByActivationToken(token);
        try {
            if (user != null) {
                user.setEnabled(true);
                user.setTrangThai(true);
                user.setActivationtoken(null);
                userService.save(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("success", "Xác thực thành công, hãy đăng nhập !");
        return "login";
    }

}
