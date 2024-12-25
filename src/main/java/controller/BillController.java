package controller;

import bean.User;
import dao.BillDAO;
import dao.UserDAO;
import util.HashUtil;
import util.MD5Utils;
import util.RSACipher;
import util.RSAUtil;

import javax.crypto.Cipher;
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
    UserDAO userDAO =new UserDAO()  ;
    MD5Utils hashUtil = new MD5Utils();
    RSAUtil cipher = new RSAUtil();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get total from param total in url
        String total = req.getParameter("total");
        req.setAttribute("total", total);
        HttpSession session = req.getSession();

// Lấy ID của session
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId != null) {
            String publicKey = userDAO.getPublicKeyByUserId(userId); // Truy vấn DB bằng userId
            System.out.println("Public Key: " + publicKey);
        } else {
            System.out.println("No user ID found in session.");
        }

        req.getRequestDispatcher("bill.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var name = req.getParameter("name");
        var phone = req.getParameter("phone");
        var address = req.getParameter("address");
        var payment = req.getParameter("payment");
        var privateKey = req.getParameter("privateKeyInput"); // Lấy giá trị privateKeyInput
         String obj= name + phone +address+payment;
//        try {
      String  hash = hashUtil.encrypt(obj);

        String very= null;
        try {
            cipher.setPrivateKey(privateKey);
            very = cipher.encrypt(hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(very);
            System.out.println(privateKey);
            BillDAO.getInstance().createOrder(name, phone, address, payment, req);


    }
}