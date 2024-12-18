package controller;

import bean.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateQuantityServlet", value = "/updateQuantity")
public class UpdateQuantityController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String minus = request.getParameter("minus");
        int index;
        if (action == null) {
            doPost_DisplayCart(request, response);
        } else {
            if (action.equalsIgnoreCase("add")) {
                doPost_Add(request, response);
            } else if (action.equalsIgnoreCase("minus")) {
                doPost_Minus(request, response);
            }
        }
    }

    private void doPost_Minus(HttpServletRequest request, HttpServletResponse response) {
    }

    private void doPost_Add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String add = request.getParameter("add");
        String currentQuantity = request.getParameter("quantity");
        int quantityUpdate;
        if (add != null && !add.isEmpty()) {
            quantityUpdate = Integer.parseInt(currentQuantity) + 1;
            request.setAttribute("updateQuantity", quantityUpdate);
        }
        request.setAttribute("updateQuantity", currentQuantity);
        response.sendRedirect("updateQuantity");

    }


    private void doPost_DisplayCart(HttpServletRequest request, HttpServletResponse response) {
    }

//    private void updateQuantityInSession(HttpServletRequest request, int index, int currentQuantity) {
//        HttpSession session = request.getSession();
//        List<Item> cart = (List<Item>) session.getAttribute("cart");
//
//        if (cart != null && index >= 0 && index < cart.size()) {
//            cart.get(index).setQuantity(currentQuantity);
//        }
//    }
}