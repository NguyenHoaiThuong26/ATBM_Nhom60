package mail;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import javax.activation.DataHandler;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class MailService {
    static Properties props = new Properties();

    static {
        props.put("mail.smtp.auth", MailProperties.auth);
        props.put("mail.smtp.starttls.enable", MailProperties.ssl);
        props.put("mail.smtp.host", MailProperties.host);
        props.put("mail.smtp.port", MailProperties.port);
    }

    public static boolean send(String to, String subject, String mes) {
        try {
            Session session = Session.getInstance(props,
                    new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(MailProperties.username, MailProperties.password);
                        }
                    });
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(MailProperties.username, "WEB BAN HANG"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(mes);
            Transport.send(message);
            return true;
        } catch (MessagingException | UnsupportedEncodingException e) {
            return false;
        }
    }


//    public static void sendEmailWithAttachment(String to, String subject, String message, File attachment) {
//        javax.mail.Session session_send = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {
//            @Override
//            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
//                return new javax.mail.PasswordAuthentication(MailProperties.username, MailProperties.password);
//            }
//        });
//
//        try {
//            javax.mail.internet.MimeMessage mimeMessage = new javax.mail.internet.MimeMessage(session_send);
//            mimeMessage.setFrom(new javax.mail.internet.InternetAddress(MailProperties.username, "WEB BAN HANG"));
//            mimeMessage.addRecipient(javax.mail.Message.RecipientType.TO, new javax.mail.internet.InternetAddress(to));
//            mimeMessage.setSubject(subject, "UTF-8");
//
//            // Tạo phần nội dung email
//            MimeBodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setContent(message, "text/html; charset=utf-8");
//
//            // Tạo phần đính kèm (nếu có)
//            javax.mail.Multipart multipart = new MimeMultipart();
//            multipart.addBodyPart(messageBodyPart);
//
//            if (attachment != null && attachment.exists()) {
//                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
//                attachmentBodyPart.attachFile(attachment);
//                multipart.addBodyPart(attachmentBodyPart);
//            }
//
//            // Thiết lập nội dung email
//            mimeMessage.setContent(multipart);
//
//            // Gửi email
//            javax.mail.Transport.send(mimeMessage);
//            System.out.println("Email đã được gửi thành công!");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Gửi email thất bại!");
//        }
//    }


    public static void sendEmailWithAttachment(String to, String subject, String message, String fileName, String fileContent) {
        javax.mail.Session session_send = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(MailProperties.username, MailProperties.password);
            }
        });

        // Tạo file mới với nội dung được truyền vào
        File tempFile = new File(fileName);
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(fileContent); // Ghi nội dung vào file
            writer.flush();
        } catch (IOException e) {
            System.out.println("Lỗi khi tạo file: " + e.getMessage());
            return;
        }

        try {
            javax.mail.internet.MimeMessage mimeMessage = new javax.mail.internet.MimeMessage(session_send);
            mimeMessage.setFrom(new javax.mail.internet.InternetAddress(MailProperties.username, "WEB BAN HANG"));
            mimeMessage.addRecipient(javax.mail.Message.RecipientType.TO, new javax.mail.internet.InternetAddress(to));
            mimeMessage.setSubject(subject, "UTF-8");

            // Tạo phần nội dung email
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(message, "text/html; charset=utf-8");

            // Tạo phần đính kèm
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(tempFile);

            // Thêm phần nội dung và đính kèm vào multipart
            javax.mail.Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentBodyPart);

            // Thiết lập nội dung email
            mimeMessage.setContent(multipart);

            // Gửi email
            javax.mail.Transport.send(mimeMessage);
            System.out.println("Email đã được gửi thành công với file đính kèm!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Gửi email thất bại!");
        } finally {
            // Xóa file tạm sau khi gửi email
            if (tempFile.exists()) {
                tempFile.delete();
            }
        }
    }





    public static void main(String[] args) {
        sendEmailWithAttachment("21130559@st.hcmuaf.edu.vn", "Private key nè",
                "Vui lòng kiểm tra file đính kèm để nhận private key của bạn.", "private_key.txt", "đây là private key");
    }






}
