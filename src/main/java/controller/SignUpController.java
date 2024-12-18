package controller;
import dao.UserDAO;
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


@WebServlet(value = "/signup")
public class SignUpController extends HttpServlet {
    String username, email, password, confirmPassword;
    String emailError, usernameError, confirmWrong;
    int countError = 0;
    int verifyCode;
    public static final String SUBJECT = "MÃ XÁC MINH";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        username = req.getParameter("username__signup");
        email = req.getParameter("email__signup");
        password = req.getParameter("password__signup");
        confirmPassword = req.getParameter("confirm__password");
        HttpSession session = req.getSession();
        session.removeAttribute("verificationCode");
        session.removeAttribute("verificationTime");
        verifyCode = VerificationCode.generateVerificationCode();

        if (SignUpService.getInstance().checkEmailExist(email)) {
            countError++;
            emailError = "Email đã tồn tại";
        }
        if(SignUpService.getInstance().checkUsernameExist(username)) {
            countError++;
            usernameError = "Username đã tồn tại";
        }
        if(!SignUpService.getInstance().checkConfirmPassword(password, confirmPassword)) {
            countError++;
            confirmWrong = "Xác thực mật khẩu thất bại";
        }else {
            password = util.Encode.toSHA1(password);
            confirmPassword = util.Encode.toSHA1(confirmPassword);
        }

        if(countError == 0) {
            UserDAO.registerUser(username, email, password);
            session.setAttribute("verificationCode", verifyCode);
            session.setAttribute("verificationTime", System.currentTimeMillis());
            session.setAttribute("email", email);
            req.removeAttribute("emailError");
            req.removeAttribute("usernameError");
            req.removeAttribute("confirmWrong");
            req.getRequestDispatcher("verify.jsp").forward(req, resp);
            MailService.send(email, SUBJECT, "Đây là mã xác minh của bạn : " + verifyCode);
        }else {
            req.setAttribute("username__signup", username);
            req.setAttribute("email__signup", email);
            req.setAttribute("emailError", emailError);
            req.setAttribute("usernameError", usernameError);
            req.setAttribute("confirmWrong", confirmWrong);
            req.getRequestDispatcher("./signUp.jsp").forward(req, resp);
        }

    }

}
