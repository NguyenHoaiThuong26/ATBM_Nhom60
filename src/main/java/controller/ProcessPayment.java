package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(name = "ProcessPayment", value = "/processPayment")
public class ProcessPayment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Kiểm tra trạng thái đăng nhập
        String userName = (String) session.getAttribute("auth");


        if (userName != null && !userName.isEmpty()) {
            // Nếu đã đăng nhập, chuyển hướng đến bill.jsp
            response.sendRedirect("bill.jsp");
        } else {
            // Nếu chưa đăng nhập, chuyển hướng đến signIn.jsp
            response.sendRedirect("logIn.jsp");
        }
    }
}