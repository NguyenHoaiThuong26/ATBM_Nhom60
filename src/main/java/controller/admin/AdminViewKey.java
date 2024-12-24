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
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(value = "/adminViewKey")
public class AdminViewKey extends HttpServlet  {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int keyId = Integer.parseInt(req.getParameter("keyId"));

        PublicKey publicKey = UserService.getPublicKeyById(keyId);
        HttpSession session = req.getSession();
        session.setAttribute("publicKey", publicKey);
        session.setAttribute("keyId", keyId);
        req.getRequestDispatcher("./adminEditKey.jsp").forward(req, resp);
    }
}
