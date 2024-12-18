package service;

import bean.Brand;
import dao.BrandDAO;

import java.util.List;

public class BrandService {
    public static BrandService instance;

    public static BrandService getInstance() {
        if (instance == null) instance = new BrandService();
        return instance;
    }
    public List<Brand> getAllBrands() {
        return BrandDAO.getAllBrands();
    }

    public static void main(String[] args) {
        BrandService brandService = new BrandService();
        System.out.println(brandService.getNameBrandById(1)+"\n");
    }

    public Brand getNameBrandById(int brandId) {
        return BrandDAO.getNameBrandById(brandId);
    }
}
