package controller.admin;

import bean.Bill;
import bean.User;
import dao.BillDAO;
import dao.UserDAO;
import service.BillService;
import service.UserService;
import util.HashUtil;
import util.RSACipher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;


@WebServlet(value = "/adminCheckSignature")
public class AdminCheckSignature extends HttpServlet  {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int billId = Integer.parseInt(req.getParameter("billId"));
        List<Bill> billList = BillDAO.getInstance().getBillList();

        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("auth");
        if (u == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        int userId = u.getId();

        // Lấy hash và signature từ db
//        String storedHash = BillService.getHashCodeByBillId(billId);
        String storedSignature = BillService.getSignatureByBillId(billId);
        String publicKey = UserService.getPublicKeyByUserId(userId);

        // Lấy dữ liệu bill hiện tại
        String currenBillDataToHash = BillService.getBillAndBillDetailsToHash(billId);


        RSACipher rsaCipher = new RSACipher();

        try {
            String currentHash = HashUtil.hashText(currenBillDataToHash, HashUtil.SHA_1);
            String decryptedHash = rsaCipher.decrypt(storedSignature, publicKey);
            if (decryptedHash.equals(currentHash)) {
                BillService.changeStatusByBillId(billId, "IN_PROGRESS", "VERIFIED");
            } else {
                BillService.changeStatusByBillId(billId, "CANCEL", "MODIFIED");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("billList", billList);
        req.getRequestDispatcher("./adminBill.jsp").forward(req, resp);
    }
}
