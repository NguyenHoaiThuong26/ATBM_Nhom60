package dao;

import bean.Discount;
import bean.Image_Product;
import db.JDBIConnector;

import java.util.List;
import java.util.stream.Collectors;

public class DiscountDAO {
    public static double getDiscount(int id) {
        double discount = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("Select amount from discount\n" +
                                "where id = :id")
                        .bind("id", id)
                        .mapTo(double.class)
                        .findOne()
                        .orElse((double) 0)
        );
        return discount;
    }

    public static void changeDiscountDate(int id, String startDate, String endDate) {
        JDBIConnector.me().useHandle(handle ->
                handle.createUpdate("update discount set startDate = :startDate, endDate = :endDate where id = :id")
                        .bind("startDate", startDate)
                        .bind("endDate", endDate)
                        .bind("id", id)
                        .execute()
        );
    }

    public static List<Discount> getDiscountList() {
        return JDBIConnector.me().withHandle(handle ->
                handle.createQuery("select id, amount, startDate, endDate from discount")
                        .mapToBean(Discount.class)
                        .collect(Collectors.toList())
        );
    }

    public static Double getDiscountAmount(int id){
        return  JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT amount FROM discount WHERE id = :id")
                        .bind("id", id)
                        .mapTo(Double.class)
                        .findOne()
                        .orElse(null)
        );
    }

    public static String getDiscountStartDay(int id){
        return JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT startDate FROM discount WHERE id = :id")
                        .bind("id", id)
                        .mapTo(String.class)
                        .findOne()
                        .orElse(null)
        );
    }

    public static String getDiscountEndDay(int id){
        return JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT endDate FROM discount WHERE id = :id")
                        .bind("id", id)
                        .mapTo(String.class)
                        .findOne()
                        .orElse(null)
        );
    }

    public static boolean addDiscount(double amount, String startDate, String endDate) {
        try {
            return JDBIConnector.me().withHandle(handle ->
                    handle.createUpdate("insert into discount(amount, startDate, endDate) " +
                                    "values (:amount, :startDate, :endDate)")
                            .bind("amount", amount)
                            .bind("startDate", startDate)
                            .bind("endDate", endDate)
                            .execute() > 0
            );
        }catch (Exception e) {
            return false;
        }
    }

    public static boolean removeDiscount(int id) {
        try {
            return JDBIConnector.me().withHandle(handle ->
                    handle.createUpdate("delete from discount where id = :id")
                            .bind("id", id)
                            .execute() > 0
            );
        }catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
//        double amount = DiscountDAO.getDiscount(1);
//        System.out.println(amount);
        System.out.println(getDiscountList());
    }
}