package controller;

import bean.Item;
import bean.Product;
import bean.ShoppingCart;
import bean.User;
import dao.ProductDAO;
import service.ProductDetailService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "CartController", value = "/cart")
public class CartController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ProductDetailService productDetailService = new ProductDetailService();


    public CartController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            doGet_DisplayCart(request, response);
        } else {
            if (action.equalsIgnoreCase("update")) {
                doPut(request, response);
            } else if (action.equalsIgnoreCase("remove")) {
                doGet_Remove(request, response);
            }
        }
    }

    protected void doGet_DisplayCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    protected void doGet_Remove(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int index = isExisting(Integer.parseInt(request.getParameter("id")), cart);
        cart.remove(index);
        session.setAttribute("cart", cart);
        response.sendRedirect("cart");
    }

    protected void doGet_Buy(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private int isExisting(int id, List<Item> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getId() == id) {
                return i;
            }
        }
        return -1;
    }

    private boolean checkIsExist(int id, List<Item> cart) {
        return cart.stream()
                .anyMatch(item -> item.getProduct().getId() == id);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String selectedCodeColor = request.getParameter("selectedCodeColor");
        String quantity = request.getParameter("quantity");
        String id = request.getParameter("id");

        if (Objects.isNull(session.getAttribute("cart"))) {
            List<Item> cart = new ArrayList<>();
            Item item = new Item(productDetailService.getProductById(Integer.parseInt(id)), Integer.parseInt(quantity), selectedCodeColor);
            cart.add(item);
            session.setAttribute("cart", cart);
        } else {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            boolean isExist = checkIsExist(Integer.parseInt(id), cart);
            if (isExist) {
                cart.forEach(item -> {
                    if (item.getProduct().getId() == Integer.parseInt(id)) {
                        item.setQuantity(item.getQuantity() + Integer.parseInt(quantity));
                    }
                });
            } else {
                Item item = new Item(productDetailService.getProductById(Integer.parseInt(id)), Integer.parseInt(quantity), selectedCodeColor);
                cart.add(item);
            }
            session.setAttribute("cart", cart);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();

        var quantity = req.getParameter("quantity");
        var id = req.getParameter("productId");
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        cart.forEach(item -> {
            if (item.getProduct().getId() == Integer.parseInt(id)) {
                if(Integer.parseInt(quantity) == 0) {
                    cart.remove(item);
                }else {
                    item.setQuantity(Integer.parseInt(quantity));
                    item.setPrice(item.getProduct().getTotalPrice() * Integer.parseInt(quantity));
                }
            }
        });
        session.setAttribute("cart", cart);
    }
}