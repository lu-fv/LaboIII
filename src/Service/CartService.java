package Service;

import Models.Cart;
import Models.ProductForSale;

import java.io.IOException;
import java.util.List;

public interface CartService {
    void addProductForSale(ProductForSale p, Integer amount) throws IOException;
    void showCartsProductList() throws IOException;
    void addCartFromListProductForSale(List<ProductForSale> list) throws IOException;
    void deleteSomeProductOfCart() throws IOException;
    void modifyCartList() throws IOException;
    void totalPriceOfCart();
}
