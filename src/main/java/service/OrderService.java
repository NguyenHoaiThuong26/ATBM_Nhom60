package service;

import bean.Order;
import bean.Product;
import dao.ProductDAO;

import java.util.Map;

public class OrderService {
    public static OrderService instance;
    private ProductDAO productDAO;

    public OrderService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public OrderService() {

    }

    public static OrderService getInstance() {
        if (instance == null) instance = new OrderService();
        return instance;
    }
    public void processOrder(Order order) {
        if (productDAO.isOrderQuantityValid(order)) {
            //Xử lý các bước của đơn hàng.

            //Cập nhật số lượng đơn hàng trong kho khi đã xác nhận đơn hàng
            updateStockQuantityInDatabase(order);
        } else {
            System.out.println("Không thể xác nhận đơn hàng do số lượng sản phẩm vượt quá kho tồn..");
        }
    }

    private void updateStockQuantityInDatabase(Order order) {
        for (Map.Entry<Product, Integer> cartItem : order.getOrderItems().entrySet()) {
            Product product = cartItem.getKey();
            int quantityInCart = cartItem.getValue();
            int quantityInStock = productDAO.getQuantityInStock(product.getId());

            if (quantityInCart < quantityInStock) {
                int newQuantityInStock = quantityInStock - quantityInCart;
                productDAO.updateProductQuantityInStock(newQuantityInStock, product.getId());
                System.out.println("Cập nhật số lượng trong kho cho sản phẩm: " + product.getName() + " với số lượng là: " + newQuantityInStock);
            }
        }
    }

}
