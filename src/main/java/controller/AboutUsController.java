package controller;

import bean.Product;
import service.ProductDetailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AboutUsController", value = "/aboutUs")
public class AboutUsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        try {
            List<Product> productList = ProductDetailService.getInstance().getProductList();
            req.setAttribute("products", productList);
            req.getRequestDispatcher("aboutUs.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            e.printStackTrace();  // Handle or log the exception appropriately
            resp.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
    }
}
