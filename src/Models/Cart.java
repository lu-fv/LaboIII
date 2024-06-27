package Models;

import java.util.HashMap;
import java.util.Map;

public class Cart<T> {
    private double totalPrice = 0;
    private Map<T, Integer> cart = new HashMap<>();

    public Cart() {
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Map<T, Integer> getCart() {
        return cart;
    }

    public void setCart(Map<T, Integer> cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "           TU CARRITO DE COMPRAS:" +
                "         Precio total:[" + totalPrice +
                "]\n Lista de productos:" + cart;
    }
}
