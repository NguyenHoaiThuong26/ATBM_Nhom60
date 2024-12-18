package controller;

import dao.UserDAO;
import service.SignUpService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(value = "/resetPassword")
public class ResetPasswordController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ResetPasswordController.class.getName());

    String emailForgetPassword,
            password,
            confirmPassword,
            confirmWrong;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        emailForgetPassword = (String) session.getAttribute("emailForgetPassword");
        password = req.getParameter("password");
        confirmPassword = req.getParameter("confirm-password");

        if (!SignUpService.getInstance().checkConfirmPassword(password, confirmPassword)) {
            confirmWrong = "Xác thực mật khẩu thất bại";
            req.setAttribute("confirmWrong", confirmWrong);
            req.getRequestDispatcher("./resetPassword.jsp").forward(req, resp);
        }
        UserDAO.changePassword(emailForgetPassword, password);
        req.setAttribute("changedPassword", "Đã thay đổi mật khẩu thành công");
        req.getRequestDispatcher("./resetPassword.jsp").forward(req, resp);

    }
}
