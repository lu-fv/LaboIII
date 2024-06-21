package Service;

import Models.Product;
import Models.Supermarket;

import java.io.IOException;
import java.util.Map;

public interface ProductForSaleService {
    public void addProductForSale(Supermarket s) throws IOException;
    public Boolean validationId(Integer id, Map<Integer, Product> map);
}
