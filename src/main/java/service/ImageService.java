package service;

import bean.Image_Product;
import bean.Product;
import dao.ImageDAO;
import dao.ProductDAO;

import java.util.List;

public class ImageService {
    public static ImageService instance;
    public static ImageService getInstance() {
        if (instance == null) instance = new ImageService();
        return instance;
    }
    public List<Image_Product> getImageByProductId(int id) {
        return ImageDAO.getImageByProductId(id);
    }
    public static void main(String[] args) {
        System.out.println(ImageService.getInstance().getImageByProductId(18));
    }
}
