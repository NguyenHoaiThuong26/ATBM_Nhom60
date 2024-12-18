package bean;

public class Payment_Method {
    private String paymentMethod;
    private double amount;

    public Payment_Method(String paymentMethod, double totalAmount) {
        this.paymentMethod = paymentMethod;
    }

    public Payment_Method() {
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void processPayment() {
        System.out.println("Thanh toán thành công. Số tiền đã thanh toán: " + amount);
    }
}
