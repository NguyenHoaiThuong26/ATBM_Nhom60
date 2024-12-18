package dao;

import bean.Category;
import bean.Product;
import bean.Supplier;
import db.JDBIConnector;

import javax.swing.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryDAO {

    public static List<Category> getAllCategory(){
        List<Category> categoryList = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("select id, name from categories")
                        .mapToBean(Category.class)
                        .collect(Collectors.toList())
        );
        return categoryList;
    }

    public static String getCategoryName(int id) {
        return JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT name FROM categories WHERE id = :id")
                        .bind("id", id)
                        .mapTo(String.class)
                        .findOne()
                        .orElse(null)
        );
    }



    public static String getSupplierName(int id) {
        return JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT name FROM suppliers WHERE id = :id")
                        .bind("id", id)
                        .mapTo(String.class)
                        .findOne()
                        .orElse(null)
        );
    }

    public static boolean addNewCategory(String newCategory) {
        try {
            return JDBIConnector.me().withHandle(handle ->
                    handle.createUpdate("INSERT INTO categories(name) VALUES (:name)")
                            .bind("name", newCategory)
                            .execute() > 0);
        } catch (Exception e) {
            System.out.println("Không thể thêm");
            return false;
        }
    }

    public static boolean removeCategory(int id) {
        try {
            return JDBIConnector.me().withHandle(handle ->
                    handle.createUpdate("delete from categories where id = :id")
                            .bind("id", id)
                            .execute() > 0
            );
        }catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
//        Product product = ProductDAO.getProductById(4).get(0);
//        System.out.println(product.toString());
//        System.out.println(getDiscountAmount(product.getDiscountId()));
        System.out.println(addNewCategory("new cate"));
    }
}
