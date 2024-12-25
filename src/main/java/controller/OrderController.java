package controller;

import bean.Order;
import com.google.gson.Gson;
import dao.OrderDAO;
import dao.UserDAO;
import org.jetbrains.annotations.NotNull;
import service.OrderSevice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.IOException;
import java.util.Base64;
import java.util.stream.Collectors;

@WebServlet("/order")
public class OrderController extends HttpServlet {
UserDAO userDAO =new UserDAO()  ;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy thông tin đơn hàng từ query string
        String orderHash = req.getParameter("orderHash");
        if (orderHash == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Missing orderHash parameter.\"}");
            return;
        }
        HttpSession session = req.getSession();

// Lấy ID của session
       Integer sessionId = Integer.valueOf(session.getId());
       String publicKey = userDAO.getPublicKeyByUserId(sessionId);
        System.out.println(publicKey);

// Chuyển thành int bằng hashCode()

        // Truy vấn thông tin đơn hàng từ database
        Order order = OrderDAO.getOrder(orderHash);
        if (order == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("{\"error\": \"Order not found.\"}");
            return;
        }

        // Phản hồi thông tin đơn hàng dưới dạng JSON
        resp.setContentType("application/json");
        resp.getWriter().write(String.format(
                "{\"orderHash\": \"%s\", \"signature\": \"%s\", \"publicKey\": \"%s\"}",
                order.getOrderHash(), order.getSignature(), order.getPublicKey()
        ));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy dữ liệu từ yêu cầu POST
        String orderHash = req.getParameter("orderHash");
        String signature = req.getParameter("signature");
        String publicKey = req.getParameter("publicKey");

        if (orderHash == null || signature == null || publicKey == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Missing required parameters: orderHash, signature, publicKey.\"}");
            return;
        }

        // Lưu thông tin xuống database
        try {
            OrderDAO.saveOrder(orderHash, signature, publicKey);

            // Phản hồi thành công
            resp.setContentType("application/json");
            resp.getWriter().write("{\"status\": \"success\", \"message\": \"Order saved successfully.\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"An error occurred while saving the order.\"}");
            e.printStackTrace();
        }
    }
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        // Lấy thông tin đơn hàng từ request
//        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//        Gson gson = new Gson();
//        OrderDAO orderRequest = gson.fromJson(body, OrderDAO.class);
//
//        // Kiểm tra dữ liệu
//        if (orderRequest.getPublicKey() == null || orderRequest.getPublicKey().isEmpty()) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            resp.getWriter().write("{\"status\":\"error\", \"message\":\"Public key is missing.\"}");
//            return;
//        }
//
//        // Lưu đơn hàng vào database
//        boolean isSaved = OrderSevice.hashOrder(orderRequest,);
//        if (isSaved) {
//            resp.setContentType("application/json");
//            resp.getWriter().write("{\"status\":\"success\", \"message\":\"Order saved successfully.\"}");
//        } else {
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            resp.getWriter().write("{\"status\":\"error\", \"message\":\"Failed to save order.\"}");
//        }
//    }
}