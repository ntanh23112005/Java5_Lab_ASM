package poly.java5.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface MailService {

    void send(MailModel mailModel);

    default void send(String to, String subject, String body) {
        MailModel mail = MailModel.builder()
                .to(to)
                .subject(subject)
                .body(body)
                .build();
        this.send(mail);
    }

    default void sendWelcome(String to) {
        String subject = "Chào mừng bạn đến với FPT Polytechnic!";
        String body = "Chúng tôi rất vui khi bạn tham gia vào cộng đồng FPT Polytechnic!";
        this.send(to, subject, body);
    }

    default void sendPassword(String to, String password) {
        String subject = "Thông tin mật khẩu mới của bạn";
        String body = "Mật khẩu mới của bạn là: " + password;
        this.send(to, subject, body);
    }

    default void sendOrder(String to, String orderDetails) {
        String subject = "Chi tiết đơn hàng của bạn";
        String body = "Thông tin đơn hàng của bạn: " + orderDetails;
        this.send(to, subject, body);
    }

    @Builder
    @Data
    @AllArgsConstructor @NoArgsConstructor
    public static class MailModel {
        @Builder.Default
        String from = "FPT Polytechnic <poly@fpt.edu.vn>";
        String to;
        String cc;
        String bcc;
        String subject;
        String body;
        String filenames;
    }
}
