package controller.admin;

import dao.BrandDAO;
import dao.DiscountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/removeDiscount")
public class AdminRemoveDiscount extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int discountId = Integer.parseInt(req.getParameter("discountId"));

        String successed = "Thành công";
        String failed = "Không thành công";
        boolean success = DiscountDAO.removeDiscount(discountId);
        if(success) {
            req.setAttribute("success", successed);
            req.getRequestDispatcher("./adminDiscountManagement").forward(req, resp);
        }else {
            req.setAttribute("fail", failed);
            req.getRequestDispatcher("./adminDiscountManagement").forward(req, resp);
        }
    }
}
