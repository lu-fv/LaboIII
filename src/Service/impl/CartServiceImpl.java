package Service.impl;

import Models.Cart;
import Models.ProductForSale;
import Service.CartService;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CartServiceImpl implements CartService {
    private final File cartFile = new File("cart.json");
    Cart<ProductForSale> cart = new Cart<>();
    public void addProductForSale(ProductForSale p, Integer amount) throws IOException {
        //si esta vacio el carrito lo agrego simplemente
        if (cart.getCart().isEmpty()) {
            //agrego el producto al hashMap
            cart.getCart().put(p, amount);
            //le sumo el valor del producto agregado al precio total del carrito
            cart.setTotalPrice(p.getPrice() * amount);
        } else {
            //sino, primero busco si el producto ya no estaba en la lista
            Integer flag = 0;
            for (Map.Entry<ProductForSale, Integer> entry : cart.getCart().entrySet()) {
                if (entry.getKey().equals(p)) {
                    //si el producto esta dentro de la lista le sumo uno en cantidad
                    entry.setValue(entry.getValue() + 1);
                    //sumo el valor al precio total del carrito
                    cart.setTotalPrice(cart.getTotalPrice() + (p.getPrice() * amount));
                    flag = 1;
                }
            }
            //si despues de recorrer el carrito el producto no estaba porque el flag sigue en cero
            if (flag == 0) {
                //entonces agrego el producto al hashMap
                cart.getCart().put(p, amount);
                //sumo al precio total el nuevo producto agregado
                cart.setTotalPrice(cart.getTotalPrice() + (p.getPrice() * amount));
            }
        }
    }

}
