package controller.admin;

import bean.*;
import dao.*;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(value = "/changeInfoKey")
public class adminChangeInfoKey extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean isValid = Boolean.parseBoolean(req.getParameter("isValid"));
        int keyId = Integer.parseInt(req.getParameter("keyId"));

        UserService.updatePublicKeyStatus(keyId, isValid);

        PublicKey publicKey = UserService.getPublicKeyById(keyId);
        HttpSession session = req.getSession();
        session.setAttribute("publicKey", publicKey);


        req.getRequestDispatcher("./adminEditKey.jsp").forward(req, resp);
    }
}
