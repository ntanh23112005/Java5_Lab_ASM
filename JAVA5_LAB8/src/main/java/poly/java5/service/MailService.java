package poly.java5.service;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

public interface MailService {
	void sendNow(MailModel mailModel);  
    void push(MailModel mailModel);
    
    default void sendNow(String to, String subject, String body) {
        MailModel mail = MailModel.builder().to(to).subject(subject).body(body).build();
        this.send(mail);
    }

    default void push(String to, String subject, String body) {
        MailModel mail = MailModel.builder().to(to).subject(subject).body(body).build();
        this.push(mail);
    }
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
		String from = "hohoangphu2005@gmail.com";
		String to, cc, bcc, subject, body, filenames;
	}
}