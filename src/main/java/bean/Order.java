package bean;

import com.sun.source.doctree.SerialDataTree;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order implements Serializable {
    private String orderHash;
    private String signature;
    private String publicKey;
    private int id;
    private User customer;
    private List<Item> items;
    private int status;
    private Map<Product, Integer> cartItems;
    public Order(Map<Product, Integer> cartItems) {
        cartItems = new HashMap<>();
    }

    public Order() {
    }

    public Map<Product, Integer> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<Product, Integer> cartItems) {
        this.cartItems = cartItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void displayOrderDetail() {
    }

    public Map<Product, Integer> getOrderItems() {
        return new HashMap<>();
    }
    public Order(String orderHash, String signature, String publicKey) {
        this.orderHash = orderHash;
        this.signature = signature;
        this.publicKey = publicKey;
    }

    public String getOrderHash() {
        return orderHash;
    }

    public void setOrderHash(String orderHash) {
        this.orderHash = orderHash;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderHash='" + orderHash + '\'' +
                ", signature='" + signature + '\'' +
                ", publicKey='" + publicKey + '\'' +
                '}';
    }

}
