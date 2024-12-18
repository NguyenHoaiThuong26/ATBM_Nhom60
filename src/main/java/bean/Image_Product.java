package bean;

public class Image_Product {
    int id;
    String link;

    public Image_Product(int id, String link) {
        this.id = id;
        this.link = link;
    }

    public Image_Product() {
    }

    int detailId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    @Override
    public String toString() {
        return "Image_Product{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", detailId=" + detailId +
                '}';
    }
}
