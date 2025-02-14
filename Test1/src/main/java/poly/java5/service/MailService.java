package poly.java5.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface MailService {

	void send(MailMessage message);

	default void send(String to, String subject, String body) {
		MailMessage message = MailMessage.builder().to(to).subject(subject).body(body).build();
		send(message);
	}

	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	@Data
	public static class MailMessage {
		@Default
		String from = "FPT Polytechnic <poly@edu.vn>";
		String to, cc, bcc, subject, body, files;
	}
}
