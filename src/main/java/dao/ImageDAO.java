package dao;

import bean.Image_Product;
import bean.Product;
import bean.Product_Color;
import db.JDBIConnector;

import java.util.List;
import java.util.stream.Collectors;

public class ImageDAO {
    public static List<Image_Product> getImageByProductId(int id) {
        List<Image_Product> imageList = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("Select * from image_products\n" +
                                "where detailId = :id")
                        .bind("id", id)
                        .mapToBean(Image_Product.class)
                        .collect(Collectors.toList())
        );
        return imageList;
    }

    public static int addImg(String link, int detailId) {
        return JDBIConnector.me().withHandle(handle ->
                handle.createUpdate("insert into image_products(link, detailId) " +
                        "values (:link, :detailId)")
                        .bind("link", link)
                        .bind("detailId", detailId)
                        .execute()
        );
    }

    public static int removeImg(int idImg) {
        return JDBIConnector.me().withHandle((handle -> {
            return handle.createUpdate("DELETE FROM image_products WHERE id = :id")
                    .bind("id", idImg)
                    .execute();
        }));
    }
    public static void main(String[] args) {

    }


}