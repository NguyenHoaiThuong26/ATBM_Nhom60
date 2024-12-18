package controller;

import bean.User;
import dao.UserDAO;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(value = "/profile")
public class ProfileUserController extends HttpServlet {
    String email, phone, firstName, lastName, birthDate, gender;
    String emailError, phoneError;
    int countError = 0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("auth");
        int userId = u.getId();

        email = req.getParameter("email");
        phone = req.getParameter("phone");
        int phoneValue = 0;
        if (!phone.isEmpty()) {
            phoneValue = Integer.parseInt(phone); // Chỉ chuyển đổi sang int nếu phone không rỗng
        }
        firstName = req.getParameter("firstName");
        lastName = req.getParameter("lastName");
        birthDate = req.getParameter("birthDate");
        gender = new String(req.getParameter("gender").getBytes("ISO-8859-1"), "UTF-8");
        req.setAttribute("email", email);
        req.setAttribute("phone", phone);
        req.setAttribute("firstName", firstName);
        req.setAttribute("lastName", lastName);
        req.setAttribute("birthDate", birthDate);

        String username = u.getUsername();

        if (!phone.isEmpty()) {
            UserDAO.changeSpecificInfo(userId, email, phoneValue, firstName, lastName, birthDate, gender);

        }
        if (!email.isEmpty()) {
            UserDAO.changeSpecificInfo(userId, email, phoneValue, firstName, lastName, birthDate, gender);
        }

        User user2 = UserDAO.getUserByUsername(username);
        req.getSession().setAttribute("auth", user2);


        req.getRequestDispatcher("profile.jsp").forward(req, resp);
    }
}
