package Service;


import Models.Supermarket;
import Enums.Category;
import Models.Product;
import Models.ProductForSale;
import java.io.IOException;
import java.util.List;

public interface SupermarketService {
    public Supermarket<ProductForSale> addSupermarket();
    public void deleteSupermarket(Supermarket<ProductForSale> s);
    public void modifySupermarket(Supermarket<ProductForSale> s);
    public void supermarketList(Supermarket<ProductForSale> s);
    public void saveSupermarketInJsonFile(Supermarket<ProductForSale> s) throws IOException;
    public List<ProductForSale> searchSalesProducts();
    public List<ProductForSale> searchSpecialProductsByName(String name);
    public List<ProductForSale> searchProductsByCategory(Category c);
}
