package controller;

import bean.Item;
import bean.User;
import com.google.gson.Gson;
import dao.BillDAO;
import mail.MailService;
import service.UserService;
import util.RSACipher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getBillDetails")
public class BillDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, IOException, ServletException {
        int orderId = Integer.parseInt(req.getParameter("bill_detail_id"));
        List<Item> billDetails = BillDAO.getBillDetailsById(orderId);
        // Chuyển dữ liệu billDetails thành JSON
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(billDetails);

        // Cài đặt kiểu dữ liệu trả về là JSON
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Gửi JSON về client
        resp.getWriter().write(jsonResponse);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

