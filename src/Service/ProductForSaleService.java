package Service;

import Models.Product;
import Models.ProductForSale;
import Models.Supermarket;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ProductForSaleService {
    void addProductForSale(Supermarket s) throws IOException;
    Boolean validationId(Integer id) throws IOException;
    void removeProductForSaleForSupermarket(Supermarket sp, Integer id) throws IOException;
    void modifyProduct(ProductForSale productForSale);
    void modifyProductForSaleInSupermarket(Supermarket sp, Integer id);
    ProductForSale searchProductoForSaleById(Supermarket s, Integer id);
    boolean isDouble (String price);
    String searchSupermarketByEachProductForSale(ProductForSale product);

}
