package controller.admin;

import bean.Supplier;
import dao.SupplierDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/addSupplier")
public class AdminAddSupplier extends HttpServlet {
    String nameSupplier, email;
    int phone;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;
        nameSupplier = req.getParameter("new-supplier-name");
        email  =req.getParameter("new-supplier-email");
        phone = Integer.parseInt(req.getParameter("new-supplier-phone"));


        Supplier supplier = new Supplier(id, nameSupplier, email, phone);
        SupplierDAO.addNewSupplier(supplier);
        resp.sendRedirect("./adminSupplierManagement");
    }
}
