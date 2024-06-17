package Models;

import java.util.HashMap;
import java.util.Map;

public class Cart<T> {
    private double totalPrice;
    private Map<T, Integer> cart = new HashMap<>();

    public Cart() {
    }

    public Cart(double totalPrice, Map<T, Integer> cart) {
        this.totalPrice = totalPrice;
        this.cart = cart;
    }

    public Cart(Map<T, Integer> cart) {
        this.cart = cart;
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
        return "Cart{" +
                "totalPrice=" + totalPrice +
                ", cart=" + cart +
                '}';
    }
}
