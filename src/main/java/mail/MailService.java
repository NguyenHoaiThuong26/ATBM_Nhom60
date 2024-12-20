package mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.MailcapCommandMap;
import javax.activation.CommandMap;

public class MailService {
    static Properties props = new Properties();

    static {
        props.put("mail.smtp.auth", MailProperties.auth);
        props.put("mail.smtp.starttls.enable", MailProperties.ssl);
        props.put("mail.smtp.host", MailProperties.host);
        props.put("mail.smtp.port", MailProperties.port);
    }

    static {
        MailcapCommandMap mailcap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
        mailcap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
        mailcap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
        mailcap.addMailcap("application/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
        mailcap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
        mailcap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
        CommandMap.setDefaultCommandMap(mailcap);
    }

    public static boolean send(String to, String subject, String mes) {
        javax.mail.Session session_send = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(MailProperties.username, MailProperties.password);
            }
        });

        try {
            javax.mail.internet.MimeMessage mimeMessage = new javax.mail.internet.MimeMessage(session_send);
            mimeMessage.setFrom(new javax.mail.internet.InternetAddress(MailProperties.username, "WEB BAN HANG"));
            mimeMessage.addRecipient(javax.mail.Message.RecipientType.TO, new javax.mail.internet.InternetAddress(to));
            mimeMessage.setSubject(subject, "UTF-8");

            // Tạo phần nội dung email
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(mes, "text/html; charset=utf-8");

            // Tạo multipart chứa nội dung email
            javax.mail.Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Thiết lập nội dung email
            mimeMessage.setContent(multipart);

            // Gửi email
            Transport.send(mimeMessage);
            System.out.println("Email đã được gửi thành công!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Gửi email thất bại!");
            return false;
        }
    }

    public static boolean sendEmailWithAttachment(String to, String subject, String message, String fileName, String fileContent) {
        javax.mail.Session session_send = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(MailProperties.username, MailProperties.password);
            }
        });

        // Tạo file tạm với đường dẫn tuyệt đối
        String absolutePath = System.getProperty("java.io.tmpdir") + File.separator + fileName;
        File tempFile = new File(absolutePath);

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(fileContent);
            writer.flush();
        } catch (IOException e) {
            System.out.println("Lỗi khi tạo file: " + e.getMessage());
            return false;
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
            Transport.send(mimeMessage);
            System.out.println("Email đã được gửi thành công với file đính kèm!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Gửi email thất bại!");
            return false;
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
