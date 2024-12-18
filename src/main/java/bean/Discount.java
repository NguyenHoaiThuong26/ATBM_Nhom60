package bean;

import dao.DiscountDAO;

public class Discount {
    int id;
    double amount;
    String startDate;
    String endDate;

    public Discount(int id, double amount, String startDate, String endDate) {
        this.id = id;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Discount() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDiscountStartDate(int discountId){
        return DiscountDAO.getDiscountStartDay(discountId);
    }

    public String getDiscountEndDate(int discountId){
        return DiscountDAO.getDiscountEndDay(discountId);
    }

    public Double getDiscountAmount(int discountId) {
        return 100 * DiscountDAO.getDiscountAmount(id);
    }
    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", amount=" + amount +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
