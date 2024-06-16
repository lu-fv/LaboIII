package Service;

import Enums.Category;
import Models.ProductForSale;
import Models.Supermarket;

import java.io.IOException;
import java.util.List;

public interface SupermarketService {
    //ABM-----------------------------------------------------------------
    Supermarket addSupermarket();

    void deleteSupermarket(Supermarket<ProductForSale> s);

    void modifySupermarket(Supermarket<ProductForSale> s);

    void supermarketList(Supermarket<ProductForSale> s);

    void saveSupermarketInJsonFile(Supermarket<ProductForSale> s) throws IOException;

    //BÚSQUEDA GENERAL----------------------------------------------------
    List<ProductForSale> searchSalesProducts();

    List<ProductForSale> searchSpecialProductsByName(String name);

    List<ProductForSale> searchProductsByCategory(Category c);

    //BÚSQUEDA POR SUPERMERCADO-------------------------------------------
    Supermarket Search(String name) throws IOException;
    void ShowListSupermarket(Supermarket supermarket);
     Supermarket SearchByCategory(Category category);

}
