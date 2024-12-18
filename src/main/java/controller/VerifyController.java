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

@WebServlet(value = "/verify")
public class VerifyController extends HttpServlet {
    int verifyCode, userCodeEntered;
    long codeTime, currentTime, expiredTime;
    public String email, emailForgetPassword;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        email = (String) session.getAttribute("email");
        emailForgetPassword = (String) session.getAttribute("emailForgetPassword");

        userCodeEntered = Integer.parseInt(req.getParameter("userEnteredCode"));

        verifyCode = (int) session.getAttribute("verificationCode");
        codeTime = (long) session.getAttribute("verificationTime");

        currentTime = System.currentTimeMillis();
        expiredTime = currentTime - codeTime;
        if (SignUpService.getInstance().checkEmailExist(email) && email != null) {
            if (expiredTime < 10 * 60 * 1000) {
                if (userCodeEntered == verifyCode) {
                    UserDAO.verifyUser(email);
                    req.setAttribute("verificationSuccess", "Xác minh thành công!");
                    req.getRequestDispatcher("./verify.jsp").forward(req, resp);
                } else {
                    req.setAttribute("errorMessage", "Mã xác minh không đúng");
                    req.getRequestDispatcher("./verify.jsp").forward(req, resp);
                }
            }
        } else if (SignUpService.getInstance().checkEmailExist(emailForgetPassword) && emailForgetPassword != null) {
            if (expiredTime < 10 * 60 * 1000) {
                if (userCodeEntered == verifyCode) {
                    req.setAttribute("emailForgetPassword", emailForgetPassword);
                    req.getRequestDispatcher("./resetPassword.jsp").forward(req, resp);
                } else {
                    req.setAttribute("errorMessage", "Mã xác minh không đúng");
                    req.getRequestDispatcher("./verify.jsp").forward(req, resp);
                }
            }
        } else {
            req.setAttribute("email", email);
            req.setAttribute("email__forget", emailForgetPassword);
            req.setAttribute("emailNotExist", "Email chưa được đăng ký");
            req.getRequestDispatcher("./verify.jsp").forward(req, resp);
        }
    }
}