package bean;

public class Bill {
    int id;
    User user;
    int userId;
    String fullName;
    String phone;
    String address;
    String createDate;
    double totalPrice;
    String paymentMethod;
    String status;


    public Bill(int id, String createDate, double totalPrice) {
        this.id = id;
        this.createDate = createDate;
        this.totalPrice = totalPrice;
    }

    public Bill(int id, User user, int userId, String fullName, String phone, String address, String createDate, double totalPrice, String paymentMethod, String status) {
        this.id = id;
        this.user = user;
        this.userId = userId;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.createDate = createDate;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public Bill() {

    }

    public Bill(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public Bill(User user, String name, String phone, String address, double totalPrice, String paymentMethod) {
        this.user = user;
        this.createDate = new java.util.Date().toString();
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.fullName = name;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", user=" + user +
                ", userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", createDate='" + createDate + '\'' +
                ", totalPrice=" + totalPrice +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}