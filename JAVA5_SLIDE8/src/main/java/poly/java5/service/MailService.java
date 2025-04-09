package poly.java5.service;

import lombok.Builder;
import lombok.Builder.Default;
import poly.java5.service.MailService;
import poly.java5.service.MailService.MailModel;
import lombok.Data;

public interface MailService {
	void send(MailModel mailModel);
	default void send(String to, String subject, String body) {
		MailModel mail = MailModel.builder().to(to).subject(subject).body(body).build();
		this.send(mail);
	}
	default void sendWelcome() {}
	default void sendPassword() {}
	default void sendOrder() {}
	
	@Builder
	@Data
	public static class MailModel{
		@Default
		String from = "FPT Polytechnic <poly@fpt.edu.vn>";
		String to, cc, bcc, subject, body, filenames;
	}
}