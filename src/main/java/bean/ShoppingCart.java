package bean;

import dao.PaymentDAO;
import dao.ProductDAO;
import service.SessionManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    Map<Product, Integer> cartItems;
    ProductDAO productDAO;
    private Payment_Method payment;
    PaymentDAO paymentDAO;
    private int billId;
    private boolean isCashOnDelivery;

    public ShoppingCart() {
        this.cartItems = new HashMap<>();
        this.billId = BillIdGenerator.generateBillId(); // Viết phương thức generateBillId để tạo billId duy nhất
        this.isCashOnDelivery = false; // Mặc định chọn chuyển khoản
    }
    public Map<Product, Integer> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<Product, Integer> cartItems) {
        this.cartItems = cartItems;
    }
    public void addProduct(Product product, int quantity) {
        cartItems.put(product, cartItems.getOrDefault(product, 0) + quantity);
        System.out.println(quantity + " " + product.getName() + "(s) đã đươc thêm vào giở hàng.");
    }
    // Phương thức Thông báo thêm giỏ hàng thành công
    public void addProductWithMessage(Product product, int quantity) {
        addProduct(product, quantity);
        System.out.println(quantity + " " + product.getName() + "(s) đã được thêm vào giỏ hàng.");
    }
    public void removeProduct(Product product, int quantity) {
        if (cartItems.containsKey(product)) {
            int currentQuantity = cartItems.get(product.getQuantity());
            if (currentQuantity <= quantity) {
                cartItems.remove(product);
            } else {
                currentQuantity -= quantity;
                cartItems.put(product, currentQuantity);
            }
            System.out.println("Số lượng đơn hàng " + product.getName() + " đã bị xóa tổng là: " + quantity);
        } else {
            System.out.println("Đơn hàng không có trong giỏ hàng !!!");
        }
    }
    // Phương thức thông báo xóa giỏ thành công
    public void removeProductWithMessage(Product product, int quantity) {
        removeProduct(product, quantity);
        System.out.println(quantity + " " + product.getName() + "(s) đã được xóa khỏi giỏ hàng.");
    }

    public double calculatedTotal() {
        double totalPrice = 0.0;
        for (Map.Entry<Product, Integer> cartItem : cartItems.entrySet()) {
            totalPrice += cartItem.getKey().getQuantity() * cartItem.getKey().getTotalPrice();
        }
        return totalPrice;
    }

    public void displayCart() {
        for (Map.Entry<Product, Integer> cartItem : cartItems.entrySet()) {
            System.out.println("Tên Sản phẩm " + cartItem.getKey().getName()
                    + " - Số lượng: " + cartItem.getValue()
                    + " - Giá: " + cartItem.getKey().getTotalPrice()
                    + " - Tổng Giá: " + (cartItem.getKey().getTotalPrice() * cartItem.getValue()));
        }
    }

    public void updateQuantity(Product product, int newQuantity) {
        if (cartItems.containsKey(product)) {
            int currentQuantity = cartItems.get(newQuantity);
            if (currentQuantity > 0) {
                cartItems.put(product, currentQuantity);
                System.out.println(product.getName() + " - Số lượng đã được cập nhật thành " + currentQuantity);
            } else {
                System.out.println(product.getName() + " - Đã được xóa khỏi giỏ hàng vì số lượng là " + currentQuantity);
            }
        } else {
            System.out.println(product.getName() + " chưa được thêm vào giỏ hàng");
        }
    }
    // Phương thức thông báo cập nhật số lượng sản phẩm thành công
    public void updateQuantityWithMessage(Product product, int newQuantity) {
        updateQuantity(product, newQuantity);
        System.out.println("Số lượng của " + product.getName() + " đã được cập nhật thành " + newQuantity + ".");
    }

    public void saveCartItemToSession() {
        // Lưu giỏ hàng vòa Session.
        SessionManager.getInstance().setSessionData("cart", cartItems);
    }

    public Map<Product, Integer> restoreCartFromSession() {
        // KHối phục giỏ hàng từ Session.
        Map<Product, Integer> saveCart = (Map<Product, Integer>) SessionManager.getInstance().getSessionData("cart");
        if (saveCart != null && saveCart instanceof Map) {
            return saveCart;
        }
        return new HashMap<>();
    }
    public void confirmOrder() {
        if (cartItems.isEmpty()) {
            System.out.println("Giỏ hàng hiện tại của bạn đang trống...");
            return;
        }
        // Kiểm tra số lượng sản phẩm trong kho hàng và giảm số lượng từ kho nếu đủ
        for (Map.Entry<Product, Integer> cartItem : cartItems.entrySet()) {
            Product product = cartItem.getKey();
            int quantityInCart = cartItem.getValue();
            int quantityInStock = productDAO.getQuantityInStock(product.getId());

            // Kiểm tra xem có đủ số lượng trong kho không.
            if (quantityInCart > quantityInStock) {
                System.out.println("Cửa hàng không đủ số lượng của sản phẩm "+product.getName()+" để đáp ứng cho bạn...");
                System.out.println("Vui chọn lại số lượng");
                return;
            }
            // Cập nhật lại quantity database.
//            product.updateProductQuantityInStock(quantityInCart, product.getId());
        }
        // Tạo đơn hàng mới
        Order newOrder = new Order(cartItems);

        // In thông báo xác nhận và đặt hàng
        System.out.println("Đơn hàng đã được xác nhận và đặt hàng thành công !");
        System.out.println("Thông tin đơn hàng: ");
        newOrder.displayOrderDetail();

        // Đặt lại giỏ hàng sau khi xác nhận đơn hàng,
        cartItems.clear();
    }
    // Phương thức để kiểm tra trạng thái sản phẩm trong giỏ hàng
    public void checkProductAvailability() {
        for (Map.Entry<Product, Integer> cartItem : cartItems.entrySet()) {
            Product product = cartItem.getKey();
            int quantityInCart = cartItem.getValue();
            int quantityInStock = productDAO.getQuantityInStock(product.getId());

            if (quantityInCart > quantityInStock) {
                System.out.println("Cảnh báo: Sản phẩm " + product.getName() + " không đủ số lượng trong kho.");
                System.out.println("Số lượng trong giỏ hàng: " + quantityInCart);
                System.out.println("Số lượng trong kho: " + quantityInStock);
            }
        }
    }

    // Chức năng xóa giỏ hàng
    public void deleteCart() {
        cartItems.clear();
        System.out.println("Bạn đã xóa giỏ hàng");
    }

    // Phương thức liên kết thanh toán.
    public void initiatePayment(String paymentMethod) {
        double totalAmount = calculatedTotal();
        this.payment = new Payment_Method(paymentMethod, totalAmount);

        System.out.println("Tổng số tiền cần thanh toán: " + totalAmount);
        System.out.println("Phương thức thanh toán: " + paymentMethod);
    }

    // Phương thức để xác nhận thanh toán
    public void confirmPayment(boolean isCashOnDelivery) {
        this.isCashOnDelivery = isCashOnDelivery;

        if (payment != null) {
            payment.processPayment();

            // Lưu thông tin thanh toán vào cơ sở dữ liệu

            paymentDAO.savePayment(billId, payment.getPaymentMethod(), isCashOnDelivery);

            // Đặt lại giỏ hàng sau khi thanh toán
            deleteCart();
        } else {
            System.out.println("Vui lòng khởi tạo thanh toán trước khi xác nhận.");
        }
    }

    public static void main(String[] args) {

    }


}
