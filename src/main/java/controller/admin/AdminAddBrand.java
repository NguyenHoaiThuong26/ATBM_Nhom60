package controller.admin;

import bean.Brand;
import bean.Category;
import bean.Product;
import bean.Supplier;
import dao.BrandDAO;
import dao.CategoryDAO;
import dao.ProductDAO;
import dao.SupplierDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/addBrand")
public class AdminAddBrand extends HttpServlet {
    String error = "Không thể thêm thương hiệu";
    String newBrand;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        newBrand = req.getParameter("new-brand");
        boolean addSuccess = BrandDAO.addNewBrand(newBrand);

        if(addSuccess) {
            req.getRequestDispatcher("./adminBrandManagement").forward(req, resp);
        }else {
            req.setAttribute("error", error);
            req.getRequestDispatcher("./adminBrandManagement").forward(req, resp);
        }

    }
}
