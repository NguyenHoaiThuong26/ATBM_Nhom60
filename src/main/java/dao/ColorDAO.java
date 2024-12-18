package dao;

import bean.Image_Product;
import bean.Product_Color;
import db.JDBIConnector;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class ColorDAO {
    public static List<Product_Color> getColorByProductId(int id) {
        List<Product_Color> colorList = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("Select product_color.nameColor from product_color\n" +
                                "where detailId = :id")
                        .bind("id", id)
                        .mapToBean(Product_Color.class)
                        .collect(Collectors.toList())
        );
        return colorList;
    }

    //    public static List<Product_Color> getCodeColor(String codeColor) {
//        List<Product_Color> colorList = JDBIConnector.me().withHandle(handle ->
//                handle.createQuery("Select Distinct product_color.codeColor from product_color\n"
//                        .matches(Product_Color.class)
//                        .collect(Collectors.toList()))
//        );
//        return colorList;
//    }
    public static List<Product_Color> getListColorCodeByIdProduct(int id) {
        List<Product_Color> listColor = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT product_color.* " +
                                "From product_color " +
                                "Inner Join product_details On product_color.detailId = product_details.id " +
                                "Where product_color.detailId = :id")
                        .bind("id", id)
                        .mapToBean(Product_Color.class)
                        .collect(Collectors.toList())
        );
        return listColor;
    }
    public static void main(String[] args) {

        List<Product_Color> colorList = ColorDAO.getListColorCodeByIdProduct(1);
        System.out.println(colorList);
    }
}