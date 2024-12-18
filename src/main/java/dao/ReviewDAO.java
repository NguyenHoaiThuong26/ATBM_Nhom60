package dao;


import bean.*;
import db.JDBIConnector;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
public class ReviewDAO {
    public static void insertReview(int userId, int productId, String content) {
        JDBIConnector.me().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO reviews(userId, productId, content, dateReview) VALUES (:userId, :productId, :content, :dateReview)")
                    .bind("userId", userId)
                    .bind("productId", productId)
                    .bind("content", content)
                    .bind("dateReview", getCurrentDate())
                    .execute();
        });
    }

    private static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }

    public static Review getNewReview(int userId, int productId) {
        Optional<Review> review = JDBIConnector.me().withHandle((handle ->
                handle.createQuery("SELECT * FROM reviews WHERE userId = :userId AND productId = :productId ORDER BY id DESC LIMIT 1")
                        .bind("userId", userId)
                        .bind("productId", productId)
                        .mapToBean(Review.class).stream().findFirst()
        ));
        return review.orElse(null);
    }

    public static List<Review> getAllReviewByProductID(int productId) {
        List<Review> reviewList = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT * FROM reviews WHERE reviews.productId = ?")
                        .bind(0, productId)
                        .mapToBean(Review.class)
                        .collect(Collectors.toList())
        );
        return reviewList;
    }

    public static List<User> getAllUser() {
        List<User> userList = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT * FROM users")
                        .mapToBean(User.class)
                        .collect(Collectors.toList())
        );
        return userList;
    }


    public static Product getLast() {
        Optional<Product> product = JDBIConnector.me().withHandle((handle ->
                handle.createQuery("SELECT TOP 1 * FROM products_details ORDER BY products_details.id DESC")
                        .mapToBean(Product.class).stream().findFirst()
        ));
        return product.orElse(null);
    }

    public static Product getProductById(int id) {
        Optional<Product> product = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT * FROM product_details WHERE product_details.id = ?")
                        .bind(0, id)
                        .mapToBean(Product.class).stream().findFirst()
        );

        return product.orElse(null);
    }

    public static List<Review> displayComment() {
        List<Review> commentList = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT * FROM reviews")
                        .mapToBean(Review.class)
                        .collect(Collectors.toList())
        );
        return commentList;
    }








}
