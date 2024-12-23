package controller;

import bean.User;
import mail.MailService;
import service.UserService;
import util.RSACipher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;


@WebServlet(name = "KeyController", value = "/generate-key")
public class KeyController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            User u = (User) session.getAttribute("auth");
            int userId = u.getId();

            // Kiểm tra xem người dùng đã có khóa chưa
            if (!UserService.canCreateNewKey(userId)) {
                req.setAttribute("message", "Bạn đã có khóa riêng. Không được tạo khóa mới!");
                req.getRequestDispatcher("profile.jsp").forward(req, resp);
                return;
            }

            // Tạo cặp khóa RSA
            KeyPair keyPair = RSACipher.generateKeyPair(2048);
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // Lưu public key xuống database
            String publicKeyBase64 = RSACipher.exportKey(publicKey);
            UserService.savePuKey(userId, publicKeyBase64);

            // Xuất private key dạng base64
            String privateKeyBase64 = RSACipher.exportKey(privateKey);

            // Tạo mail
            String emailSub = "Private Key của bạn";
            String emailMessage = "<p>Gửi " + u.getUsername() + ",</p>"
                    + "<p>Đây là private key của bạn. Vui lòng xem file được đính kèm</p>"
                    + "<p>Cảm ơn vì đã sử dụng dịch vụ của chúng tôi</p>";
            String fileName = "private_key.txt";

            if (MailService.sendEmailWithAttachment(u.getEmail(), emailSub, emailMessage, fileName, privateKeyBase64)) {
                // Gửi thông điệp thành công đến JSP
                req.setAttribute("message", "Khóa đã được tạo thành công, bạn hãy kiểm tra email!");

            } else {
                req.setAttribute("message", "Có lỗi xảy ra khi gửi email. Vui lòng thử lại sau!");
            }

            req.getRequestDispatcher("profile.jsp").forward(req, resp);



        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi tạo cặp khóa.");
        }
    }
}
