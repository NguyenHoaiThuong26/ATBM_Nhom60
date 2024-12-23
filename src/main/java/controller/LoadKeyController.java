package controller;

import bean.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


@WebServlet(name = "LoadKeyController", value = "/load-key")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50   // 50MB
)
public class LoadKeyController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            User u = (User) session.getAttribute("auth");
            int userId = u.getId();

            // Kiểm tra xem người dùng đã có khóa chưa
            if (!UserService.canCreateNewKey(userId)) {
                req.setAttribute("message", "Bạn đã có khóa. Không được tải khóa mới!");
                req.getRequestDispatcher("profile.jsp").forward(req, resp);
                return;
            }

            // Lấy file từ request
            Part filePart = req.getPart("public_key_file");
            if (filePart == null || filePart.getSize() == 0) {
                req.setAttribute("message", "Vui lòng chọn file chứa public key!");
                req.getRequestDispatcher("profile.jsp").forward(req, resp);
                return;
            }

            String publicKey;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(filePart.getInputStream(), StandardCharsets.UTF_8))) {
                publicKey = br.readLine();
            }

            if (publicKey == null || publicKey.trim().isEmpty()) {
                req.setAttribute("message", "File không chứa public key hợp lệ.");
                req.getRequestDispatcher("profile.jsp").forward(req, resp);
                return;
            }

            UserService.savePuKey(userId, publicKey);

            // Thông báo thành công
            req.setAttribute("message", "Public key đã được tải và lưu thành công!");
            req.getRequestDispatcher("profile.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi tạo load key.");
        }
    }
}
