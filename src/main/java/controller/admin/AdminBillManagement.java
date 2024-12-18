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
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/adminBillIndex")
public class AdminBillManagement extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Bill> billList = BillDAO.getInstance().getBillList();

        req.setAttribute("billList", billList);

        req.getRequestDispatcher("./adminBill.jsp").forward(req, resp);
    }
}
