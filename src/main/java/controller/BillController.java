package controller;

import bean.User;
import dao.BillDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name ="BillController", value = "/bill")
public class BillController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get total from param total in url
        String total = req.getParameter("total");
        req.setAttribute("total", total);
        req.getRequestDispatcher("bill.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var name = req.getParameter("name");
        var phone = req.getParameter("phone");
        var address = req.getParameter("address");
        var payment = req.getParameter("payment");
        BillDAO.getInstance().createOrder(name, phone, address, payment, req);
        // remove cart
        req.getSession().removeAttribute("cart");
    }
}