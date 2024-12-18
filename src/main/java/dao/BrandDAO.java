package dao;

import bean.Brand;
import db.JDBIConnector;

import java.util.List;
import java.util.stream.Collectors;

public class BrandDAO {
    public static List<Brand> getAllBrands(){
        List<Brand> brandList = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("select id, name from brands")
                        .mapToBean(Brand.class)
                        .collect(Collectors.toList())
        );
        return brandList;
    }

    public static Brand getNameBrandById(int brandId) {
        Brand brand = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT name FROM brands " +
                                "WHERE id = :id")
                        .bind("id", brandId)
                        .mapToBean(Brand.class)
                        .findOne()
                        .orElse(null) // Giả sử trả về null nếu không tìm thấy sản phẩm
        );
        return brand;
    }

    public static int getBiggestBrandId() {
        return JDBIConnector.me().withHandle(handle ->
                handle.createQuery("select max(id) from brands")
                        .mapTo(Integer.class)
                        .findOne()
                        .orElse(null)
        );
    }

    public static boolean addNewBrand(String newBrand) {
        int id = getBiggestBrandId() + 1;
        try {
            return JDBIConnector.me().withHandle(handle ->
                    handle.createUpdate("INSERT INTO brands(id, name) VALUES (:id, :name)")
                            .bind("name", newBrand)
                            .bind("id", id)
                            .execute() > 0);
        } catch (Exception e) {
            System.out.println("Lỗi");
            return false;
        }
    }

    public static String getBrandName(int id) {
        return JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT name FROM brands WHERE id = :id")
                        .bind("id", id)
                        .mapTo(String.class)
                        .findOne()
                        .orElse(null)
        );
    }

    public static boolean removeBrand(int id) {
        try {
            return JDBIConnector.me().withHandle(handle ->
                    handle.createUpdate("delete from brands where id = :id")
                            .bind("id", id)
                            .execute() > 0
            );
        }catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        Brand brand = getNameBrandById(1);
        System.out.println(getBiggestBrandId());
    }
}
