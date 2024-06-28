package Service;

import Enums.Category;
import Models.Food;
import Models.Product;
import Models.ProductForSale;
import Models.Supermarket;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface SupermarketService {
    //region ABM-----------------------------------------------------------------
    void addSupermarket() throws IOException;

    void deleteSupermarket(Supermarket s) throws IOException;

    void modifySupermarketListProducts(Supermarket s) throws IOException;

    void modifySupermarket(String name) throws IOException;

    void supermarketList();

    void saveSupermarketInJsonFile(HashMap<String, Supermarket>superList) throws IOException;

    HashMap<String,Supermarket> supermarketsListJson () throws IOException;
    //endregion

    //region BÚSQUEDA POR SUPERMERCADO-------------------------------------------
    Supermarket search(String name) throws IOException;
    void showListSupermarket(Supermarket supermarket);
    List<ProductForSale> serchByCategoryInSupermarket(Supermarket supermarket, Category category);
    List<ProductForSale> serchProductInSaleInSupermarket(Supermarket supermarket);
    List<ProductForSale> serchProductByNameInSupermarket(Supermarket supermarket, String name);
    //endregion

    //region BÚSQUEDA GENERAL----------------------------------------------------
    List<ProductForSale> searchSalesProducts() throws IOException;
    List<ProductForSale> searchSpecialProductsByName(String name) throws IOException;
    List<ProductForSale> searchProductsByCategory(Category c) throws IOException;
    public Boolean searchSpecialProductsByNameExist(String name);
    //endregion

    //region ACTUALIZACIÓN DE DATOS DE UN PRODUCTO
    void updateProductData(Product updated) throws IOException;
    //endregion
}
