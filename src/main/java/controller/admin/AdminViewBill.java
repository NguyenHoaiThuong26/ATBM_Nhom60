package controller.admin;

import bean.Bill;
import bean.User;
import dao.BillDAO;
import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(value = "/adminViewBill")
public class AdminViewBill extends HttpServlet  {
    int billId;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        billId = Integer.parseInt(req.getParameter("billId"));

        Bill bill = BillDAO.adminViewBill(billId);
        HttpSession session = req.getSession();
        session.setAttribute("bill", bill);
        session.setAttribute("billId", billId);
        req.getRequestDispatcher("./adminEditBill.jsp").forward(req, resp);
    }
}
