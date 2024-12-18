package service;

import bean.Item;
import bean.Product;
import dao.ProductDAO;

import java.util.List;

public class ProductDetailService {
    public static ProductDetailService instance;
    public static ProductDetailService getInstance() {
        if (instance == null) instance = new ProductDetailService();
        return instance;
    }
    public List<Product> getProductList() {
        return ProductDAO.getProductList();
    }

    public List<Product> getProductByCategory(String cateName) {
        return ProductDAO.getProductByCategory(cateName);
    }
    public List<Product> getProductByPriceRange(double minPrice, double maxPrice) {
        return ProductDAO.getProductByPriceRange(minPrice, maxPrice);
    }
    public List<Product> getProductByBrand(String brandName) {
        return ProductDAO.getProductByBrand(brandName);
    }
    public List<Product> getProductAZ(String AZorZA) {
        return ProductDAO.getProductAZ(AZorZA);
    }
    public List<Product> getProductAzPrice(String AZorZA) {
        return ProductDAO.getProductAzPrice(AZorZA);
    }
    public List<Product> getProductByDiscount() {
        return ProductDAO.getProductByDiscount();
    }
    public Product getProductById(int id) {
        return ProductDAO.getProductById(id).get(0);
    }
    public int getQuantityInStock(int id) {
        return ProductDAO.getQuantityInStock(id);
    }
    public int getTotalProductNumber() {
        return ProductDAO.getTotalProductNumber();
    }
    public static void main(String[] args) {
        ProductDetailService productDetailService = new ProductDetailService();
        System.out.println(productDetailService.getProductList());
        System.out.println(productDetailService.getProductByCategory("Cymbal"));
    }



}
