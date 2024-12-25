package controller;

import bean.User;
import dao.BillDAO;
import mail.MailService;
import service.BillService;
import util.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name ="BillController", value = "/bill")
public class BillController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get total from param total in url
        String total = req.getParameter("total");
        req.setAttribute("total", total);
        req.getRequestDispatcher("bill.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("auth");

        var name = req.getParameter("name");
        var phone = req.getParameter("phone");
        var address = req.getParameter("address");
        var payment = req.getParameter("payment");
        BillDAO.getInstance().createOrder(name, phone, address, payment, req);

        int billId = BillService.getLastestBill();
        String dataToHash = BillService.getBillAndBillDetailsToHash(billId);

        try {
            String hashedBill = HashUtil.hashText(dataToHash, HashUtil.SHA_256);

            String hashedBillAndInformation = "Mã hash:\n" + hashedBill + "\n\n Thông tin hóa đơn:\n" + dataToHash;


            BillService.saveHashCode(hashedBill, billId);

            // Tạo mail
            String emailSub = "Mã hash thông tin đơn hàng của bạn";
            String emailMessage = "<p>Gửi " + u.getUsername() + ",</p>"
                    + "<p>Đây là mã hash và thông tin đơn hàng của bạn. Vui lòng xem file được đính kèm</p>"
                    + "<p>Cảm ơn vì đã sử dụng dịch vụ của chúng tôi</p>";
            String fileName = "hashAndInformation.txt";

            MailService.sendEmailWithAttachment(u.getEmail(), emailSub, emailMessage, fileName, hashedBillAndInformation);



        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        req.getSession().removeAttribute("cart");

        // Trả về dữ liệu JSON
        resp.setContentType("application/json");
        resp.getWriter().write("{\"billId\": " + billId + "}");
    }
}