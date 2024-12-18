package controller.admin;

import dao.DiscountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/addDiscount")
public class AdminAddDiscount extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double amount = Double.parseDouble(req.getParameter("percentage-discount"));
        String startDate = req.getParameter("start-date");
        String endDate = req.getParameter("end-date");

        DiscountDAO.addDiscount(amount/100, startDate, endDate);

        req.getRequestDispatcher("./adminDiscountManagement").forward(req, resp);
    }
}
