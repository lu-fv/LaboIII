package Service;


import Models.Supermarket;
import Enums.Category;
import Models.Product;
import Models.ProductForSale;
import java.io.IOException;

public interface SupermarketService {
    public Supermarket addSupermarket();
    public void deleteSupermarket(Supermarket s);
    public void modifySupermarket(Supermarket s);
    public void supermarketList(Supermarket s);
    public void saveSupermarketInJsonFile(Supermarket s) throws IOException;
    public List<ProductForSale> searchSalesProducts();
    public List<ProductForSale> searchSpecialProductsByName(String name);
    public List<ProductForSale> searchProductsByCategory(Category c);
}
