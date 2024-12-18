package controller;

import bean.*;
import dao.ColorDAO;
import dao.ReviewDAO;
import service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ProductDetailController", value = "/productdetails")
public class ProductDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");

        try {
            int selectedProductId = Integer.parseInt(req.getParameter("selectedProductId"));

            // Lấy thông tin sản phẩm từ ID được chọn
            Product selectedProduct = ProductDetailService.getInstance().getProductById(selectedProductId);

            // Kiểm tra selectedProductId có tồn tại không
            if (selectedProduct != null) {
                // Lấy thông tin thương hiệu của sản phẩm (nếu cần)
                Brand selectedBrand = BrandService.getInstance().getNameBrandById(selectedProduct.getBrandId());
                double discount = DiscountService.getInstance().getDiscount(selectedProduct.getDiscountId());

                // Lấy thông tin ảnh và màu sắc của sản phẩm
                List<Image_Product> productImages = ImageService.getInstance().getImageByProductId(selectedProductId);
                List<Product_Color> productColors = ColorService.getInstance().getListColorCodeByIdProduct(selectedProductId);

                List<Review> productReviews = ReviewService.getInstance().getReviewByProductId(selectedProductId);
                List<User> users = UserService.getInstance().getAllUser();

                // Đặt thông tin vào request
                req.setAttribute("discount", discount);
                req.setAttribute("selectedProduct", selectedProduct);
                req.setAttribute("brands", selectedBrand);
                req.setAttribute("productImages", productImages);
                req.setAttribute("productColors", productColors);

                req.setAttribute("productReviews", productReviews);
                req.setAttribute("users", users);


                // Chuyển hướng sang trang productDetail.jsp
                req.getRequestDispatcher("productDetail.jsp").forward(req, resp);
            } else {
//                resp.sendRedirect("errorPage.jsp");
                // Nếu không tìm thấy sản phẩm, có thể xử lý tùy ý
            }
        } catch (Exception e) {
            e.printStackTrace();
//            resp.sendRedirect("errorPage.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        resp.setContentType("application/json;charset=UTF-8");
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("auth");
        if(u==null) {
            resp.getWriter();
//            req.getRequestDispatcher("login.jsp").forward(req, resp);
//            resp.sendRedirect("logIn.jsp");
            return;
        }

        String content = req.getParameter("content");
        int productId = Integer.parseInt(req.getParameter("productId"));
        int userId = u.getId();
        List<Review> productReviews = ReviewService.getInstance().getReviewByProductId(productId);

//      Thêm review
        ReviewDAO.insertReview(userId, productId, content);


//      Lấy ra review mới nhất
        Review newReview = new Review();
        newReview = ReviewService.getInstance().getNewReview(userId, productId);
        req.setAttribute("newReview", newReview);

        PrintWriter out = resp.getWriter();


        out.println("<div class=\"d-flex flex-row comment-row\">");
        out.println("<div class=\"p-2\"><span class=\"round\"><img src=\"https://i.imgur.com/uIgDDDd.jpg\" alt=\"user\" width=\"50\"></span></div>");
        out.println("<div class=\"comment-text w-100\">");
        if (newReview.getUserId() == userId) {
            out.println("<p class=\"review__username\">" + u.getUsername() + "</p>");
        }
        out.println("<div class=\"comment-footer\">");
        out.println("<span class=\"dot mb-1\"></span>");
        out.println("<span class=\"review__date\">" + newReview.getDateReview()+ "</span>");
        out.println("</div>");
        out.println("<p class=\"review__content m-b-5 m-t-10\">" + newReview.getContent() + "</p>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
    }

}