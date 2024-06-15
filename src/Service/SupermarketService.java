package Service;

import Enums.Category;
import Models.Product;

import java.util.List;

public interface SupermarketService {
    public List<Product> searchSalesProducts();
    public List<Product> searchSpecialProductsByName(String name);
    public List<Product> searchProductsByCategory(Category c);
}
