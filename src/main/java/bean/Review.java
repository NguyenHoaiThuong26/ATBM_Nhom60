package bean;

import java.time.DateTimeException;
import java.util.Date;
public class Review {
    private int userId;
    private int productId;
    private String content;
    private Date dateReview;
    public Review(int userId, int productID, String content, Date dateReview) {

        this.userId = userId;
        this.productId = productId;
        this.content = content;
        this.dateReview = dateReview;
    }
    public Review() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateReview() {
        return dateReview;
    }

    public void setDateReview(Date dateReview) {
        this.dateReview = dateReview;
    }

    @Override
    public String toString() {
        return "Review{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", content='" + content + '\'' +
                ", dateReview=" + dateReview +
                '}';
    }
}
