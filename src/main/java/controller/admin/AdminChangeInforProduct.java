package controller.admin;

import bean.Brand;
import bean.Category;
import bean.Product;
import bean.Supplier;
import dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/changeInforProduct")
public class AdminChangeInforProduct extends HttpServlet {

    String nameProduct, startDate, endDate, category,
            brand, supplier, desc;

    int idProduct, quantity, discountId;
    double price;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        HttpSession session = req.getSession();
//        idProduct = (int) session.getAttribute("productId");
        idProduct = Integer.parseInt(req.getParameter("productId"));

        nameProduct = req.getParameter("product-name");

        discountId = Integer.parseInt(req.getParameter("product-discount"));
        startDate = req.getParameter("product-discount-start");
        endDate = req.getParameter("product-discount-end");

        category = req.getParameter("product-category");
        brand = req.getParameter("product-brand");
        supplier = req.getParameter("product-supplier");
        desc = req.getParameter("product-desc");

        quantity = Integer.parseInt(req.getParameter("product-quantity"));

        price = Double.parseDouble(req.getParameter("product-price"));

        int brandId = 0;
        int categoryId = 0;
        int supplierId = 0;

        List<Supplier> supplierList = SupplierDAO.getListSupplier();
        for (Supplier sup : supplierList) {
            if (sup.getName().equals(supplier)) {
                supplierId = sup.getId();
            }
        }

        List<Category> categoryList = CategoryDAO.getAllCategory();
        for (Category cate : categoryList) {
            if (cate.getName().equals(category)) {
                categoryId = cate.getId();
            }
        }
        List<Brand> brandList = BrandDAO.getAllBrands();
        for (Brand br : brandList) {
            if (br.getName().equals(brand)) {
                brandId = br.getId();
            }
        }

        DiscountDAO.changeDiscountDate(discountId, startDate, endDate);

        Product product = new Product(idProduct, nameProduct, discountId, categoryId, brandId, supplierId, quantity, price, desc);
        ProductDAO.changeInforProduct(product);
        System.out.println(category);
        req.getRequestDispatcher("./adminEditProduct.jsp").forward(req, resp);
    }
}
