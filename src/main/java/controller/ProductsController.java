package controller;

import bean.Brand;
import bean.Category;
import bean.Product;
import dao.ProductDAO;
import service.BrandService;
import service.CategoryService;
import service.ProductDetailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductsController", value = "/products")
public class ProductsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doPost(req, resp);
        String category = req.getParameter("category");
        String minPricePara = req.getParameter("minPrice");
        String maxPricePara = req.getParameter("maxPrice");
        String brand = req.getParameter("brands");
        String azorza = req.getParameter("AZorZA");
        String discount = req.getParameter("discounts");

        try {
            List<Product> productList = null;
            if (category != null && !category.isEmpty()) {
                productList = ProductDetailService.getInstance().getProductByCategory(category);
            } else if (minPricePara != null && maxPricePara != null && !minPricePara.isEmpty() && !maxPricePara.isEmpty()) {
                double minPrice = Double.parseDouble(minPricePara);
                double maxPrice = Double.parseDouble(maxPricePara);
                productList = ProductDetailService.getInstance().getProductByPriceRange(minPrice, maxPrice);
            } else if (brand != null && !brand.isEmpty()) {
                productList = ProductDetailService.getInstance().getProductByBrand(brand);
            } else if (azorza != null && !azorza.isEmpty()) {
                String sort = azorza.equals("ASC") ? "ASC" : "DESC";
                productList = ProductDetailService.getInstance().getProductAZ(sort);
                productList = ProductDetailService.getInstance().getProductAzPrice(sort);
            } else if (discount != null && !discount.isEmpty()) {
                productList = ProductDetailService.getInstance().getProductByDiscount();
            } else {
                productList = ProductDetailService.getInstance().getProductList();
            }
            List<Category> categoryList = CategoryService.getInstance().getAllCategories();
            List<Brand> brandList = BrandService.getInstance().getAllBrands();

            req.setAttribute("categories", categoryList);
            req.setAttribute("brands", brandList);
            req.setAttribute("products", productList);
            req.getRequestDispatcher("product.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            e.printStackTrace();  // Handle or log the exception appropriately
            resp.sendRedirect("error.jsp");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Category> categoryList = CategoryService.getInstance().getAllCategories();
//
//        req.setAttribute("categories", categoryList);
//        req.getRequestDispatcher("product.jsp").forward(req, resp);
    }
}
