package bean;

import dao.*;

import java.util.List;
import java.util.Objects;
public class Product extends ProductDAO {
    private int id;
    private String name;
    private int discountId;
    private int categoryId;
    private int brandId;
    private int supplierId;
    private int quantity;
    private double totalPrice;
    private String description;
    private int status;
    private Image_Product image;

    public Product() {
    }

    public Product(int id, String name, int discountId, int categoryId, int brandId, int supplierId, int quantity, double totalPrice, String description) {
        this.id = id;
        this.name = name;
        this.discountId = discountId;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.supplierId = supplierId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.description = description;
    }

    public Product(int id, String s, String name, String category, double price, String desc) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.totalPrice = totalPrice;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCategoryName(int id) {
        return CategoryDAO.getCategoryName(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Product product = (Product) obj;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public String getBrandName(int id) {
        return BrandDAO.getBrandName(id);
    }

    public String getSupplierName(int id) {
        return CategoryDAO.getSupplierName(id);
    }

    public List<Image_Product> imageProducts(int id) {
        return ImageDAO.getImageByProductId(id);
    }

    public String getDiscountStartDay(int id){
        return DiscountDAO.getDiscountStartDay(id);
    }

    public String getDiscountEndDay(int id) {
        return DiscountDAO.getDiscountEndDay(id);
    }

    public static Double getDiscountAmount(int id) {
        return 100 * DiscountDAO.getDiscountAmount(id);
    }

    public static void main(String[] args) {
        Product product = ProductDAO.adminViewProduct(1);
        System.out.println(product.getDiscountAmount(1));
    }
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", discountId=" + discountId +
                ", categoryId=" + categoryId +
                ", brandId=" + brandId +
                ", supplierId=" + supplierId +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
