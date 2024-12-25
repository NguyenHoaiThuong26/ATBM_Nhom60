package controller;

import bean.User;
import mail.MailService;
import service.SignUpService;
import service.UserService;
import util.Encode;
import util.VerificationCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "controller.LoginController", value = "/log")
public class LoginController extends HttpServlet {
    static String username, pass, email;
    public String error = "Bạn nhập sai tài khoản hoặc mật khẩu vui lòng nhập lại";
    int verifyCode;
    public static final String SUBJECT = "MÃ XÁC MINH";
    public String unVerify = "Tài khoản chưa được xác thực";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        email = req.getParameter("email");
        username = req.getParameter("username");
        pass = req.getParameter("password");
//        pass = Encode.toSHA1(pass);

        verifyCode = VerificationCode.generateVerificationCode();
//        Session
        User user = UserService.getInstance().checkLogin(username, pass);
        HttpSession session = req.getSession();
        session.removeAttribute("verificationCode");
        session.removeAttribute("verificationTime");
        if (email != null) {
            if(SignUpService.getInstance().checkEmailExist(email)){
                session.setAttribute("verificationCode", verifyCode);
                session.setAttribute("verificationTime", System.currentTimeMillis());
                session.setAttribute("email", email);
                MailService.send(email, SUBJECT, "Đây là mã xác minh của bạn : " + verifyCode);
                req.getRequestDispatcher("verify.jsp").forward(req, resp);
            }else {
                req.setAttribute("email", email);
                req.setAttribute("isNotVerify", "Tài khoản chưa được xác thực");
                req.setAttribute("emailNotExist", "Email chưa được đăng ký");
                req.getRequestDispatcher("./logIn.jsp").forward(req, resp);
            }

        }


        if (user != null) {
            if(user.getStatus() == 0) {
                req.setAttribute("isNotVerify", "Tài khoản chưa được xác thực");
                req.getRequestDispatcher("./logIn.jsp").forward(req, resp);
                return;
            }
            if (user.getStatus() != 0) {
                // Lưu thông tin user vào session
                session.setAttribute("auth", user);

                // Lưu id_user vào session
                int id_user = user.getId();
                session.setAttribute("userId", id_user);
                resp.sendRedirect("./index.jsp");

                return;
            }
            if(user.getStatus() == -1) {
                req.setAttribute("error", "Tài khoản của quý khách đã ăn ban, tạo tài khoản mới đê, then kiu");
                req.getRequestDispatcher("./logIn.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("username", username);
            req.setAttribute("error", error);
            req.getRequestDispatcher("./logIn.jsp").forward(req, resp);
        }
    }
}