package controller;

import service.BillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name ="ThankYouController", value = "/thank-you")
public class ThankYouController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String billId = req.getParameter("billId");
        req.setAttribute("billId", billId);

        req.getRequestDispatcher("orderSucecssfully.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String signature = req.getParameter("signature");
        int billId = Integer.parseInt(req.getParameter("billId"));

        BillService.saveSignature(signature, billId);
        req.setAttribute("message", "Chữ ký đã được xác nhận thành công!");

        req.getRequestDispatcher("orderSucecssfully.jsp").forward(req, resp);
    }
}