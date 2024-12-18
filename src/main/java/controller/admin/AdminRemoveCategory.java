package controller.admin;

import dao.CategoryDAO;
import dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/removeCategory")
public class AdminRemoveCategory extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        String successed = "Thành công";
        String failed = "Không thành công";
        boolean success = CategoryDAO.removeCategory(categoryId);
            if(success) {
                req.setAttribute("success", successed);
                req.getRequestDispatcher("./adminCategoryManagement").forward(req, resp);
            }else {
                req.setAttribute("fail", failed);
                req.getRequestDispatcher("./adminCategoryManagement").forward(req, resp);
            }
    }
}
