package controller.admin;

import bean.*;
import dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/adminProductIndex")
public class AdminProductManagement extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        List<Product> productList = ProductDAO.getProductList();
        List<Discount> discountList = DiscountDAO.getDiscountList();
        List<Category> categoryList = CategoryDAO.getAllCategory();
        List<Brand> brandList = BrandDAO.getAllBrands();
        List<Supplier> supplierList = SupplierDAO.getListSupplier();

        session.setAttribute("productList", productList);
        session.setAttribute("discountList", discountList);
        session.setAttribute("categoryList", categoryList);
        session.setAttribute("brandList", brandList);
        session.setAttribute("supplierList", supplierList);
        req.getRequestDispatcher("./adminProduct.jsp").forward(req, resp);
    }
}
