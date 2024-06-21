package Service;

import Models.Product;
import Models.ProductForSale;
import Models.Supermarket;

import java.io.IOException;
import java.util.Map;

public interface ProductForSaleService {
     void addProductForSale(Supermarket s) throws IOException;
     Boolean validationId(Integer id, Map<Integer, Product> map);
    void RemoveProductForSaleForSupermarket(Supermarket sp, Integer id) throws IOException;
    void ModifyProduct(ProductForSale productForSale);
    void ModifyProductForSaleInSupermarket(Supermarket sp, Integer id);
}
