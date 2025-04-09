package poly.java5.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import poly.java5.service.MailService.MailModel;
import poly.java5.service.MailService;

@Service
public class MailServiceImpl implements MailService{
	List<MailModel> queue = new ArrayList<>();
	
	@Override
	public void send(MailModel mailModel) {
		queue.add(mailModel);
	}
	
	@Scheduled(fixedDelay = 2000)
	public void run() {
		while(!queue.isEmpty()) {
			MailModel mail = queue.remove(0);
			try {
				sendMail(mail);
				System.out.println("Thành công");
			} catch (Exception e) {
				System.out.println("Lỗi");
			}
		}
	}
	
	@Autowired
	JavaMailSender mailSender;

	private void sendMail(MailModel mail) {
		try {
			// 1. Tạo Mail
			MimeMessage message = mailSender.createMimeMessage();
			
			// 2. Tạo đối tượng hỗ trợ ghi nội dung Mail
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			
			// 2.1. Ghi thông tin người gửi
			helper.setFrom(mail.getFrom());
			helper.setReplyTo(mail.getFrom());
			
			// 2.2. Ghi thông tin người nhận
			helper.setTo(mail.getTo());
			
			if(!this.isNullOrEmpty(mail.getCc())) {
				helper.setCc(mail.getCc());
			}
			
			if(!this.isNullOrEmpty(mail.getBcc())) {
				helper.setBcc(mail.getBcc());
			}
			// 2.3. Ghi tiêu đề và nội dung
			helper.setSubject(mail.getSubject());
			helper.setText(mail.getBody(), true);
			
			// 2.4. Đính kèm file
			String filenames = mail.getFilenames();
			if(!this.isNullOrEmpty(filenames)) {
				for(String filename: filenames.split("[,;]+")) {
					File file = new File(filename.trim());
					helper.addAttachment(file.getName(), file);
				}
			}
			
			//3. Gửi Mail
			mailSender.send(message);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private boolean isNullOrEmpty(String text) {
		return (text == null || text.trim().isEmpty());
	}
	
}