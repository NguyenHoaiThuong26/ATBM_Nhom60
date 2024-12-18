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

@WebServlet(value = "/addCategory")
public class AdminAddCategory extends HttpServlet {
    String error = "Không thể thêm danh mục";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String newCategory = req.getParameter("new-category");
        boolean addSuccess = CategoryDAO.addNewCategory(newCategory);

        if(addSuccess) {
            req.getRequestDispatcher("./adminCategoryManagement").forward(req, resp);
        }else {
            req.setAttribute("error", error);
            req.getRequestDispatcher("./adminCategoryManagement").forward(req, resp);
        }
    }

}
