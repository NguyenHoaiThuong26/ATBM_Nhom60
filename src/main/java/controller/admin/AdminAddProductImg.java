package controller.admin;

import dao.ImageDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/addProductImage")
public class AdminAddProductImg extends HttpServlet {
    int productId;
    String linkImg;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        productId = Integer.parseInt(req.getParameter("productId"));
        linkImg = req.getParameter("imageUrl");
        String[] listLink = linkImg.split(" ");

//        HttpSession session = req.getSession();
//        session.setAttribute("productId", productId);
        for (String link : listLink
        ) {
            ImageDAO.addImg(link, productId);
        }
        resp.sendRedirect("./adminViewProduct?productId=" + productId);
    }
}
