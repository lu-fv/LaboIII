package Service;

import Models.Cart;
import Models.ProductForSale;

import java.io.IOException;
import java.util.List;

public interface CartService {
    Cart<ProductForSale> getCartForSave();
    void addProductForSale(ProductForSale p, Integer amount) throws IOException;
    void showCartsProductList();
    void addCartFromListProductForSale(List<ProductForSale> list) throws IOException;
    void deleteSomeProductOfCart() throws IOException;
    void modifyCartList() throws IOException;
    void totalPriceOfCart();
}
