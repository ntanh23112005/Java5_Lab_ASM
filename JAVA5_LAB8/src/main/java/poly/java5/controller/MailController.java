package poly.java5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import poly.java5.service.MailService;
import poly.java5.service.MailService.MailModel;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private MailService mailService;
    
    @GetMapping("/send")
    public String getMethodName() {
        return "mail/send";
    }

    @PostMapping("/send")
    public String sendMail(@ModelAttribute MailModel mail, 
                           @RequestParam("attachments") List<MultipartFile> files,
                           @RequestParam("action") String action) {
        // Xử lý file đính kèm
        if (!files.isEmpty()) {
            String filenames = files.stream()
                                    .map(MultipartFile::getOriginalFilename)
                                    .collect(Collectors.joining(", "));
            mail.setFilenames(filenames);
        }

        if ("queue".equals(action)) {
            mailService.push(mail);
            System.out.println("📨 Email đã được xếp vào hàng đợi!");
        } else {
            mailService.send(mail);
            System.out.println("✅ Email đã được gửi ngay!");
        }
        
        return "redirect:/mail/send";
    }
}
