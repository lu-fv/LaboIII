package Service;

import Enums.Category;
import Models.ProductForSale;
import Models.Supermarket;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface SupermarketService {
    //region ABM-----------------------------------------------------------------
    void addSupermarket() throws IOException;

    void deleteSupermarket() throws IOException;

    public void modifySupermarket(String name) throws IOException;

    void supermarketList(Supermarket s);

    void saveSupermarketInJsonFile(HashMap<String, Supermarket>superList) throws IOException;
    public HashMap<Integer,Supermarket> supermarketsListJson () throws IOException;
    //endregion

    //region BÚSQUEDA POR SUPERMERCADO-------------------------------------------
    Supermarket search(String name) throws IOException;

    void showListSupermarket(Supermarket supermarket);

    Supermarket searchByCategory(Category category);
    //endregion

    //region BÚSQUEDA GENERAL----------------------------------------------------
    List<ProductForSale> searchSalesProducts() throws IOException;

    List<ProductForSale> searchSpecialProductsByName(String name) throws IOException;

    List<ProductForSale> searchProductsByCategory(Category c) throws IOException;
    public Boolean searchSpecialProductsByNameExist(String name);
    //endregion
}
