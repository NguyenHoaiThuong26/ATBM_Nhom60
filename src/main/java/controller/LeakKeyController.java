package controller;

import bean.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "LeakKeyController", value = "/leak-key")
public class LeakKeyController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            User u = (User) session.getAttribute("auth");
            int userId = u.getId();

            String password = req.getParameter("password");

            // Xác thực mật khẩu
            if (!UserService.verifyPassword(userId, password)) {
                req.setAttribute("message", "Mật khẩu không chính xác. Vui lòng thử lại!");
                req.getRequestDispatcher("profile.jsp").forward(req, resp);
                return;
            }

            // Kiểm tra nếu chưa có khóa nào
            if (!UserService.hasPublicKey(userId)) {
                req.setAttribute("message", "Bạn chưa có khóa nào. Hãy tạo khóa mới!");
                req.getRequestDispatcher("profile.jsp").forward(req, resp);
                return;
            }

            // Vô hiệu hóa khóa
            UserService.updateExpiredKey(userId);

            req.setAttribute("message", "Khóa đã được vô hiệu hóa, bạn có thể tạo khóa mới!");
            req.getRequestDispatcher("profile.jsp").forward(req, resp);



        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi tạo cặp khóa.");
        }
    }
}
