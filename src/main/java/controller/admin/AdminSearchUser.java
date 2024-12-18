package controller.admin;

import bean.User;
import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/adminSearchUser")
public class AdminSearchUser extends HttpServlet {

    String searchValue;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        searchValue = req.getParameter("search-user");
        List<User> userList = UserDAO.adminSearchUser(searchValue);
        req.setAttribute("userList", userList);
        req.setAttribute("searchValue", searchValue);
        req.getRequestDispatcher("./adminUser.jsp").forward(req, resp);
    }
}
