package poly.java5.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService { 
    private final List<MailModel> queue = new ArrayList<>();

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void send(MailModel mailModel) {
        System.out.println("📩 Đã thêm email vào hàng đợi: " + mailModel);
        queue.add(mailModel);
    }

    @Scheduled(fixedDelay = 2000)
    public void run() {
        while (!queue.isEmpty()) {
            MailModel mail = queue.remove(0);
            try {
                sendMail(mail);
                System.out.println("✅ Email gửi thành công: " + mail.getTo());
            } catch (Exception e) {
                System.err.println("❌ Lỗi khi gửi mail đến " + mail.getTo() + ": " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void sendMail(MailModel mail) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

            helper.setFrom(mail.getFrom());
            helper.setReplyTo(mail.getFrom());
            helper.setTo(mail.getTo());

            if (!isNullOrEmpty(mail.getCc())) helper.setCc(mail.getCc());
            if (!isNullOrEmpty(mail.getBcc())) helper.setBcc(mail.getBcc());

            helper.setSubject(mail.getSubject());
            helper.setText(mail.getBody(), true);

            if (!isNullOrEmpty(mail.getFilenames())) {
                for (String filename : mail.getFilenames().split("[,;]+")) {
                    File file = new File(filename.trim());
                    if (file.exists()) {
                        helper.addAttachment(file.getName(), file);
                    } else {
                        System.err.println("⚠️ File không tồn tại: " + filename);
                    }
                }
            }

            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi gửi mail: " + e.getMessage(), e);
        }
    }

    private boolean isNullOrEmpty(String text) {
        return (text == null || text.trim().isEmpty());
    }

	@Override
	public void sendNow(MailModel mailModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void push(MailModel mailModel) {
		// TODO Auto-generated method stub
		
	}
}
