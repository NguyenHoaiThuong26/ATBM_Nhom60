package service;

import bean.Discount;
import bean.Product_Color;
import dao.ColorDAO;
import dao.DiscountDAO;

import java.util.List;

public class DiscountService {
    public static DiscountService instance;
    public static DiscountService getInstance() {
        if (instance == null) instance = new DiscountService();
        return instance;
    }
    public double getDiscount(int id) {
        return DiscountDAO.getDiscount(id);
    }
}
