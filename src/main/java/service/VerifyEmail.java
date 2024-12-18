package service;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class VerifyEmail {

    public static void sendVerificationCode(String toEmail, String verificationCode) {
        final String from = "danghuuquy10042003@gmail.com"; // Địa chỉ email gửi
        String host = "smtp.gmail.com"; // SMTP server của email gửi

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("@gmail.com", "");

            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Verification Code");

            message.setText("Your verification code is: " + verificationCode);

            Transport.send(message);
            System.out.println("Verification code sent successfully.");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String toEmail = "21130338@st.hcmuaf.edu.vn"; // Địa chỉ email của người nhận
        String verificationCode = "123456"; // Mã xác thực (có thể tạo ngẫu nhiên)

        sendVerificationCode(toEmail, verificationCode);
    }
}

