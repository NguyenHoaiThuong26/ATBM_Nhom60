package controller.admin;

import bean.*;
import dao.BrandDAO;
import dao.CategoryDAO;
import dao.DiscountDAO;
import dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/addProduct")
public class AdminAddProduct extends HttpServlet {
    String productName, description;
    int discountId, brandId, categoryId, quantity, supplierId;
    double price;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        productName = req.getParameter("productName");
        discountId = Integer.parseInt(req.getParameter("discount"));
        categoryId = Integer.parseInt(req.getParameter("category"));
        brandId = Integer.parseInt(req.getParameter("brand"));
        supplierId = Integer.parseInt(req.getParameter("supplier"));
        quantity = Integer.parseInt(req.getParameter("quantity"));
        price = Double.parseDouble(req.getParameter("price"));
        description = req.getParameter("description");
        Product product = new Product(0, productName, discountId, categoryId,
                brandId, supplierId, quantity, price, description);
        ProductDAO.addProduct(product);

        resp.sendRedirect("./adminProductIndex");
    }
}
