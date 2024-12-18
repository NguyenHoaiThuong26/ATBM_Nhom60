package controller.admin;

import bean.User;
import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(value = "/adminViewUser")
public class AdminViewUser extends HttpServlet  {
    int userId, role;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userId = Integer.parseInt(req.getParameter("userId"));

        User user = UserDAO.adminViewUser(userId);
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        session.setAttribute("userId", userId);
        req.getRequestDispatcher("./adminEditUser.jsp").forward(req, resp);
    }
}
