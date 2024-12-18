package bean;

public class Product_Color {
    private int id;
    private String nameColor;
    private Product product;
    private String codeColor;

    public String getCodeColor() {
        return codeColor;
    }

    public void setCodeColor(String codeColor) {
        this.codeColor = codeColor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameColor() {
        return nameColor;
    }

    public void setNameColor(String nameColor) {
        this.nameColor = nameColor;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Product_Color{" +
                "id=" + id +
                ", nameColor='" + nameColor + '\'' +
                ", product='" + product + '\'' +
                ", codeColor='" + codeColor + '\'' +
                '}';
    }
}