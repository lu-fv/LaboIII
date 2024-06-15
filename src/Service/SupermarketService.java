package Service;

import Enums.Category;
import Models.Product;
import Models.ProductForSale;

import java.util.List;

public interface SupermarketService {
    public List<ProductForSale> searchSalesProducts();
    public List<ProductForSale> searchSpecialProductsByName(String name);
    public List<ProductForSale> searchProductsByCategory(Category c);
}
