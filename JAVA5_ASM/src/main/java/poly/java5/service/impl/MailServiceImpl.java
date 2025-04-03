package poly.java5.service.impl;

import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;
import poly.java5.service.MailService;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void send(MailModel mailModel) {
        try {
            sendMail(mailModel); // Gửi email ngay lập tức
        } catch (Exception e) {
            throw new RuntimeException("Gửi email thất bại: " + e.getMessage(), e);
        }
    }

    private void sendMail(MailModel mail) {
        try {
            // Tạo đối tượng MimeMessage
            MimeMessage message = mailSender.createMimeMessage();
            
            // Tạo đối tượng MimeMessageHelper để hỗ trợ tạo nội dung email
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            
            // Thiết lập thông tin người gửi và người nhận
            helper.setFrom(mail.getFrom());
            helper.setReplyTo(mail.getFrom());
            helper.setTo(mail.getTo());

            // Nếu có CC, BCC thì thêm vào
            if (!isNullOrEmpty(mail.getCc())) {
                helper.setCc(mail.getCc());
            }
            if (!isNullOrEmpty(mail.getBcc())) {
                helper.setBcc(mail.getBcc());
            }

            // Thiết lập tiêu đề và nội dung email
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getBody(), true); // true cho phép gửi HTML

            // Đính kèm file nếu có
            String filenames = mail.getFilenames();
            if (!isNullOrEmpty(filenames)) {
                for (String filename : filenames.split("[,;]+")) {
                    File file = new File(filename.trim());
                    helper.addAttachment(file.getName(), file);
                }
            }

            // Gửi email
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Gửi email thất bại: " + e.getMessage(), e);
        }
    }

    // Kiểm tra chuỗi có null hoặc rỗng không
    private boolean isNullOrEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }
}
