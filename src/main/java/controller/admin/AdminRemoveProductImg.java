package controller.admin;

import dao.ImageDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/adminRemoveImg")
public class AdminRemoveProductImg extends HttpServlet {
    int imgId, productId;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        productId = Integer.parseInt(req.getParameter("productId"));

        imgId = Integer.parseInt(req.getParameter("imgId"));
        ImageDAO.removeImg(imgId);

        resp.sendRedirect("./adminViewProduct?productId=" + productId);
    }
}
