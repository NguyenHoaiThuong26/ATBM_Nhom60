package controller.admin;

import bean.Discount;
import dao.DiscountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminDiscountManagement")
public class AdminDiscountManagement extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session =req.getSession();
        List<Discount> discountList = DiscountDAO.getDiscountList();
        session.setAttribute("discountList", discountList);
        req.getRequestDispatcher("./adminDiscount.jsp").forward(req, resp);
    }
}
