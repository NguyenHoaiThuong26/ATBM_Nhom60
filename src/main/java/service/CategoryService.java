package service;

import bean.Category;
import dao.CategoryDAO;
import db.JDBIConnector;

import java.util.List;

public class CategoryService {
    public static CategoryService instance;

    public static CategoryService getInstance() {
        if (instance == null) instance = new CategoryService();
        return instance;
    }

    public List<Category> getAllCategories() {
        return CategoryDAO.getAllCategory();
    }

    public static void main(String[] args) {
        CategoryService categoryService = new CategoryService();
        System.out.println(categoryService.getAllCategories());
    }
}
