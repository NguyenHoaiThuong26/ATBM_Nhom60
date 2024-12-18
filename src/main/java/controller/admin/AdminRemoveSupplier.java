package controller.admin;

import dao.SupplierDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/removeSupplier")
public class AdminRemoveSupplier extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int supplierId = Integer.parseInt(req.getParameter("supplierId"));

        String successed = "Thành công";
        String failed = "Không thành công";
        boolean success = SupplierDAO.removeSupplier(supplierId);
        if(success) {
            req.setAttribute("success", successed);
            req.getRequestDispatcher("./adminSupplierManagement").forward(req, resp);
        }else {
            req.setAttribute("fail", failed);
            req.getRequestDispatcher("./adminSupplierManagement").forward(req, resp);
        }
    }
}
