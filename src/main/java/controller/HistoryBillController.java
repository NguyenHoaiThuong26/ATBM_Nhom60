package controller;

import bean.Item;
import dao.BillDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "HistoryBillController", value = "/history-bill")
public class HistoryBillController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var myBill = BillDAO.getBillsByUser((bean.User) req.getSession().getAttribute("auth"));
        req.setAttribute("listOrder", myBill);
        req.getRequestDispatcher("history-bill.jsp").forward(req, resp);
    }
}