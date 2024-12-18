package service;

import bean.Review;
import dao.ReviewDAO;

import java.util.List;

public class ReviewService {
    public static ReviewService instance;
    public static ReviewService getInstance() {
        if (instance == null) instance = new ReviewService();
        return instance;
    }
    public List<Review> getReviewByProductId(int id) {
        return ReviewDAO.getAllReviewByProductID(id);
    }
    public Review getNewReview(int userId, int productId) {
        return ReviewDAO.getNewReview(userId, productId);
    }


    public static void main(String[] args) {

//        System.out.println(ReviewService.getInstance().getReviewByProductId(1));
        System.out.println(ReviewService.getInstance().getNewReview(0, 1));
    }
}
