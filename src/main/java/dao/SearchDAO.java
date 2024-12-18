package dao;

import bean.Product;
import db.JDBIConnector;
import org.jdbi.v3.core.mapper.reflect.BeanMapper;

import java.awt.image.DataBuffer;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SearchDAO {
    public static List<Product> listProductSearch(String txtSearch) {
        List<Product> productList = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("Select product_details.id, image_products.link, product_details.name, product_details.totalPrice, product_details.description\n" +
                                "FROM product_details\n" +
                                "JOIN image_products ON product_details.id = image_products.detailId\n" +
                                "JOIN categories ON product_details.categoryId = categories.id\n" +
                                "WHERE product_details.name LIKE ? OR categories.name LIKE ?")
                        .bind(1, "%"+txtSearch+"%")
                        .bind(2, "%"+txtSearch+"%")
                        .mapToBean(Product.class)
                        .collect(Collectors.toList())
        );
        return productList;
    }
    public static int countProduct(String txtSearch) {
        int count = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("Select product_details.id, image_products.link, product_details.name, product_details.totalPrice, product_details.description\n" +
                                "FROM product_details\n" +
                                "JOIN image_products ON product_details.id = image_products.detailId\n" +
                                "JOIN categories ON product_details.categoryId = categories.id\n" +
                                "WHERE product_details.name LIKE ? OR categories.name LIKE ?")
                        .bind(1, "%"+txtSearch+"%")
                        .bind(2, "%"+txtSearch+"%")
                        .mapTo(int.class)
                        .findOne()
                        .orElse(0) // Giả sử trả về 0 nếu không tìm thấy sản phẩm
        );

        return count;
    }
    public static int getProduct(String txtSearch) {
        int count = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT *\n" +
                                "FROM product_details " +
                                "JOIN categories ON product_details.categoryId = categories.id\n" +
                                "Where categories.name = ?")
                        .bind(1, "%" + txtSearch + "%")
                        .mapTo(int.class)
                        .findOne()
                        .orElse(0)
        );
        return count;
    }
    public static List<Product> getProductBySearching(int indexPage, int pageSize, String txtSearch) {
        List<Product> listSearch = new ArrayList<>();
        try {
            int offset = (indexPage - 1) * pageSize;

            listSearch = JDBIConnector.me().withHandle(handle ->
                    handle.createQuery("WITH x AS (" +
                                    "   SELECT ROW_NUMBER() over (ORDER BY id ASC) AS r, product_details.id, " +
                                    "   image_products.link, product_details.name, categories.name As nameCategory, product_details.description, " +
                                    "   product_details.totalPrice " +
                                    "   FROM product_details " +
                                    "   JOIN image_products ON product_details.id = image_products.detailId " +
                                    "   JOIN categories ON product_details.categoryId = categories.id " +
                                    "   WHERE product_details.name LIKE :search OR categories.name LIKE :search)" +
                                    "   SELECT id, link, name, nameCategory, description, totalPrice " +
                                    "   FROM x WHERE r BETWEEN :offset AND :limit")
                            .bind("search", "%" + txtSearch + "%")
                            .bind("offset", offset)
                            .bind("limit", offset + pageSize)
                            .mapToBean(Product.class)
                            .list()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSearch;
    }
    public static void main(String[] args) {
        int count = SearchDAO.getProduct("Cymbal");
        List<Product> listSearch = SearchDAO.getProductBySearching(1, 9, "d");
        for (Product item : listSearch) {
            System.out.println(item);
        }
    }
}
