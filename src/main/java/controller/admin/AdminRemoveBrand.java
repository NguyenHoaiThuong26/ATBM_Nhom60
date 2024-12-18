package controller.admin;

import dao.BrandDAO;
import dao.CategoryDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/removeBrand")
public class AdminRemoveBrand extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int brandId = Integer.parseInt(req.getParameter("brandId"));

        String successed = "Thành công";
        String failed = "Không thành công";
        boolean success = BrandDAO.removeBrand(brandId);
        if(success) {
            req.setAttribute("success", successed);
            req.getRequestDispatcher("./adminBrandManagement").forward(req, resp);
        }else {
            req.setAttribute("fail", failed);
            req.getRequestDispatcher("./adminBrandManagement").forward(req, resp);
        }
    }
}
