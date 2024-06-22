package Service;

import Models.Cart;
import Models.ProductForSale;

import java.io.IOException;

public interface CartService {
    void addProductForSale(ProductForSale p, Integer amount) throws IOException;
    void showCartsProductList();
    void saveCartList() throws IOException;
    void deleteSomeProductOfCart() throws IOException;
    void modifyCartList() throws IOException;
}
