package controller.admin;

import bean.Bill;
import bean.PublicKey;
import bean.User;
import dao.BillDAO;
import dao.UserDAO;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/adminKeyIndex")
public class AdminKeyManager extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PublicKey> keyList = UserService.getPublicKeyList();

        req.setAttribute("keyList", keyList);

        req.getRequestDispatcher("./adminKey.jsp").forward(req, resp);
    }
}
