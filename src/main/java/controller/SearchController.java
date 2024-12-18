package controller;

import bean.Brand;
import bean.Category;
import bean.Product;
import dao.BrandDAO;
import dao.SearchDAO;
import org.jetbrains.annotations.NotNull;
import service.BrandService;
import service.CategoryService;
import service.ProductDetailService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchController", value = "/search")
public class SearchController extends HttpServlet {
    private SearchDAO searchDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.searchDAO = new SearchDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, @NotNull HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        try {
            String txtSearch = req.getParameter("txtSearch");
            int indexPage = Integer.parseInt(req.getParameter("indexPage"));

            // Thực hiện phân trang
            int countProduct = searchDAO.getProduct(txtSearch);
            int pageSize = 9;   // 1 pageSize chưa 9 SP.
            int endPage = countProduct / pageSize;
            endPage = (countProduct % pageSize != 0 ? endPage++ : endPage);

            List<Product> productList = null;
            if (txtSearch != null && !txtSearch.isEmpty()) {
                // Thực hiện tìm kiếm và lấy danh sách sản phẩm
                productList = searchDAO.getProductBySearching(indexPage, pageSize, txtSearch);

                // Đặt danh sách sản phẩm vào request
                req.setAttribute("products", productList);
            }
            List<Category> categoryList = CategoryService.getInstance().getAllCategories();
            List<Brand> brandList = BrandService.getInstance().getAllBrands();

            req.setAttribute("categories", categoryList);
            req.setAttribute("brands", brandList);
            req.setAttribute("endPage", endPage);
            req.setAttribute("products", productList);
            req.setAttribute("txtSearch", txtSearch);
            req.getRequestDispatcher("product.jsp").forward(req, resp);
        } catch (Exception e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
        }
    }
}
