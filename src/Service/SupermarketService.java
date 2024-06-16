package Service;

import Enums.Category;
import Models.ProductForSale;
import Models.Supermarket;

import java.io.IOException;
import java.util.List;

public interface SupermarketService {
    //region ABM-----------------------------------------------------------------
    Supermarket addSupermarket();

    void deleteSupermarket(Supermarket s);

    void modifySupermarket(Supermarket s);

    void supermarketList(Supermarket s);

    void saveSupermarketInJsonFile(Supermarket s) throws IOException;
    //endregion

    //region BÚSQUEDA POR SUPERMERCADO-------------------------------------------
    Supermarket search(String name) throws IOException;

    void showListSupermarket(Supermarket supermarket);

    Supermarket searchByCategory(Category category);
    //endregion

    //region BÚSQUEDA GENERAL----------------------------------------------------
    List<ProductForSale> searchSalesProducts();

    List<ProductForSale> searchSpecialProductsByName(String name);

    List<ProductForSale> searchProductsByCategory(Category c);
    //endregion
}
