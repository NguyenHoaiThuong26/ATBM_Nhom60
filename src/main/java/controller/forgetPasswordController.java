package controller;

import mail.MailService;
import service.SignUpService;
import util.VerificationCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/forgetPassword")
public class forgetPasswordController extends HttpServlet {

    String emailForget;
    int verifyCode;
    public static final String SUBJECT = "MÃ XÁC MINH";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        emailForget = req.getParameter("email__forget");
        verifyCode = VerificationCode.generateVerificationCode();
        HttpSession session = req.getSession();
        session.removeAttribute("verificationCode");
        session.removeAttribute("verificationTime");

        if(SignUpService.getInstance().checkEmailExist(emailForget)){
            session.setAttribute("verificationCode", verifyCode);
            session.setAttribute("verificationTime", System.currentTimeMillis());
            session.setAttribute("emailForgetPassword", emailForget);
            req.getRequestDispatcher("./verify.jsp").forward(req, resp);
            MailService.send(emailForget, SUBJECT, "Đây là mã xác minh của bạn : " + verifyCode);
        }else {
            req.setAttribute("email", emailForget);
            req.setAttribute("emailNotExist", "Email chưa được đăng ký");
            req.getRequestDispatcher("./forgetPassword.jsp").forward(req, resp);
        }
    }
}
