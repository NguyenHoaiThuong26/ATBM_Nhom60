package dao;

import bean.*;
import db.JDBIConnector;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductDAO {
    public static List<Product> getProductList() {
        List<Product> productList = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("Select *  from product_details")
                        .mapToBean(Product.class)
                        .collect(Collectors.toList())
        );
        return productList;
    }

    public static List<Product> getProductByCategory(String cateName) {
        List<Product> productList = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT product_details.id, product_details.name, description, totalPrice FROM product_details \n" +
                                "JOIN categories ON product_details.categoryId = categories.id \n" +
                                "WHERE categories.name = ?")
                        .bind(0, cateName)
                        .mapToBean(Product.class)
                        .collect(Collectors.toList())
        );
        return productList;
    }

    public static List<Product> getProductByPriceRange(double minPrice, double maxPrice) {
        List<Product> productList = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT product_details.id, product_details.name, description, totalPrice FROM product_details \n" +
                                "WHERE product_details.totalPrice Between :minPrice And :maxPrice")
                        .bind("minPrice", minPrice)
                        .bind("maxPrice", maxPrice)
                        .mapToBean(Product.class)
                        .collect(Collectors.toList())
        );
        return productList;
    }

    public static List<Product> getProductByBrand(String brandName) {
        List<Product> productList = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT product_details.id, product_details.name, description, totalPrice FROM product_details \n" +
                                "Join brands on brands.id = product_details.brandId \n" +
                                "Where brands.name = ?")
                        .bind(0, brandName)
                        .mapToBean(Product.class)
                        .collect(Collectors.toList())
        );
        return productList;
    }

    public static List<Product> getProductAZ(String AZorZA) {
        List<Product> productList = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT product_details.id, product_details.name, description, totalPrice\n" +
                                "FROM product_details \n" +
                                "Order By name " + AZorZA)
                        .mapToBean(Product.class)
                        .collect(Collectors.toList())
        );
        return productList;
    }

    public static List<Product> getProductAzPrice(String AZorZA) {
        List<Product> productList = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT product_details.id, product_details.name, description, totalPrice\n" +
                                "FROM product_details \n" +
                                "Order By totalPrice " + AZorZA)
                        .mapToBean(Product.class)
                        .collect(Collectors.toList())
        );
        return productList;
    }

    public static List<Product> getProductByDiscount() {
        List<Product> productList = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT product_details.id, product_details.name, description, totalPrice " +
                                "From product_details " +
                                "Join discount on discount.id = product_details.categoryId " +
                                "Where discount.amount > 0")
                        .mapToBean(Product.class)
                        .collect(Collectors.toList())
        );
        return productList;
    }

    public static List<Product> getProductById(int id) {
        List<Product> productList = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT product_details.id, product_details.name, description, totalPrice " +
                                "From product_details Where product_details.id = ?")
                        .bind(0, id)
                        .mapToBean(Product.class)
                        .collect(Collectors.toList())
        );
        return productList;
    }

    public static int getQuantityInStock(int id) {
        int quantityInStock = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT quantity FROM product_details WHERE id = :id")
                        .bind("id", id)
                        .mapTo(int.class)
                        .findOne()
                        .orElse(0) // Giả sử trả về 0 nếu không tìm thấy sản phẩm
        );
        return quantityInStock;
    }

    public static int getTotalProductNumber() {
        int totalProductNumber = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT count(*) FROM product_details")
                        .mapTo(int.class)
                        .findOne()
                        .orElse(0) // Giả sử trả về 0 nếu không tìm thấy sản phẩm
        );
        return totalProductNumber;
    }

    public void updateProductQuantityInStock(int newQuantity, int id) {
        JDBIConnector.me().useHandle(handle ->
                handle.createUpdate("UPDATE product_details SET quantity = :newQuantity WHERE id = :productId")
                        .bind("newQuantity", newQuantity)
                        .bind("productId", id)
                        .execute()
        );
    }

    //    Phương thức để kiểm tra xem số lượng trong đơn hàng có vượt quá số lượng trong kho hay không
    public boolean isOrderQuantityValid(Order order) {
        for (Map.Entry<Product, Integer> entry : order.getOrderItems().entrySet()) {
            Product product = entry.getKey();
            int orderQuantity = entry.getValue();
            int stockQuantity = getQuantityInStock(product.getId());

            if (orderQuantity > stockQuantity) {
                System.out.println("Số lượng sản phẩm " + product.getName() + " trong đơn hàng vượt quá số lượng trong kho.");
                return false;
            }
        }
        return true;
    }

    public Product getProductId(int id) {
        Product product = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT product_details.id, product_details.name, totalPrice, quantity FROM product_details WHERE id = :id")
                        .bind("id", id)
                        .mapTo(Product.class)
                        .findOne()
                        .orElse(null) // Giả sử trả về null nếu không tìm thấy sản phẩm
        );
        return product;
    }

    public static List<Product> adminSearchProductByCategories(String value) {
        List<Product> productList = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT * FROM product_details WHERE product_details.categoryId IN (SELECT id FROM categories WHERE LOWER(name) like LOWER(:like))")
                        .bind("like", "%" + value + "%")
                        .mapToBean(Product.class)
                        .collect(Collectors.toList())
        );
        return productList;
    }

    public static List<Product> adminSearchProductByBrands(String value) {
        List<Product> productList = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT * FROM product_details WHERE product_details.brandId IN (SELECT id FROM brands WHERE LOWER(name) like LOWER(:like))")
                        .bind("like", "%" + value + "%")
                        .mapToBean(Product.class)
                        .collect(Collectors.toList())
        );
        return productList;
    }

    public static List<Product> adminSearchProductByName(String value) {
        List<Product> productList = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT * FROM product_details WHERE LOWER(name) like LOWER(:like)")
                        .bind("like", "%" + value + "%")
                        .mapToBean(Product.class)
                        .collect(Collectors.toList())
        );
        return productList;
    }

    public static Product adminViewProduct(int id) {
        Product product = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT * FROM product_details WHERE id = :id")
                        .bind("id", id)
                        .mapToBean(Product.class)
                        .findOne()
                        .orElse(null) // Giả sử trả về null nếu không tìm thấy sản phẩm
        );
        return product;

    }

    public static <Product> List<Product> mergeListsDistinct(List<Product>... lists) {
        return Stream.of(lists)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    public static void changeInforProduct(Product product) {
        JDBIConnector.me().useHandle(handle ->
                handle.createUpdate("UPDATE product_details set " +
                                "name = :name, discountId = :discountId, categoryId = :categoryId, brandId = :brandId, supplierId = :supplierId, quantity = :quantity, totalPrice = :totalPrice, description = :description" +
                                " where id = :id")
                        .bind("name", product.getName())
                        .bind("discountId", product.getDiscountId())
                        .bind("categoryId", product.getCategoryId())
                        .bind("brandId", product.getBrandId())
                        .bind("supplierId", product.getSupplierId())
                        .bind("quantity", product.getQuantity())
                        .bind("totalPrice", product.getTotalPrice())
                        .bind("description", product.getDescription())
                        .bind("id", product.getId())
                        .execute()
        );
        System.out.println("Done");
    }

    public static List<Product> adminSearchProduct(String value) {
        List<Product> productList = mergeListsDistinct(adminSearchProductByName(value), adminSearchProductByCategories(value), adminSearchProductByBrands(value));
        return productList;
    }

    public static void removeProduct(int id) {
        JDBIConnector.me().withHandle(handle ->
                handle.createUpdate("delete from product_details where id = :id")
                        .bind("id", id)
                        .execute()
        );
    }

    public static void addProduct(Product product) {
        JDBIConnector.me().withHandle(handle ->
                handle.createUpdate("insert into product_details(name, discountId, categoryId, brandId, supplierId, quantity, totalPrice, description) " +
                                "values (:name, :discountId, :categoryId, :brandId, :supplierId, :quantity, :totalPrice, :description)")
                        .bind("name", product.getName())
                        .bind("discountId", product.getDiscountId())
                        .bind("categoryId", product.getCategoryId())
                        .bind("brandId", product.getBrandId())
                        .bind("supplierId", product.getSupplierId())
                        .bind("quantity", product.getQuantity())
                        .bind("totalPrice", product.getTotalPrice())
                        .bind("description", product.getDescription()).execute());


    }

    public static void main(String[] args) {
//        List<Product> productByCate = ProductDAO.getProductByCategory("Electric");
//        List<Product> productByPrice = ProductDAO.getProductByPriceRange(0, 10000000);
//        List<Product> productByBrand = ProductDAO.getProductByBrand("Manhwa");
//        List<Product> productByASC = ProductDAO.getProductAZ("ASC");
//        List<Product> productDiscount = ProductDAO.getProductByDiscount();
//        int totalProductNumber = ProductDAO.getTotalProductNumber();
//        Item item = ProductDAO.getItemById(1);
        addProduct(new Product(0, "test", 1, 1, 1, 1, 100, 101, "test"));
    }


}
