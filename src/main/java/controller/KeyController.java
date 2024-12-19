package controller;

import bean.User;
import service.UserService;
import util.DigitalSignature;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
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

            // Tạo cặp khóa RSA
            KeyPair keyPair = DigitalSignature.generateKeyPair(2048);
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // Lưu public key xuống database
            String publicKeyBase64 = DigitalSignature.exportKey(publicKey);
            UserService.savePuKey(userId, publicKeyBase64);

            // Xuất private key ra file .txt
            String privateKeyBase64 = DigitalSignature.exportKey(privateKey);

            // Gửi file private key về cho người dùng
            resp.setContentType("text/plain");
            resp.setHeader("Content-Disposition", "attachment; filename=\"private_key.txt\"");
            PrintWriter out = resp.getWriter();
            out.print(privateKeyBase64);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi tạo cặp khóa.");
        }
    }
}
