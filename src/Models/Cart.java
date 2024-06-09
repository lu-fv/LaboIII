package Models;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private double totalPrice;
    private Map<Product, Integer> cart = new HashMap<>();

    public Cart() {
    }

    public Cart(double totalPrice, HashMap<Product, Integer> cart) {
        this.totalPrice = totalPrice;
        this.cart = cart;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public void setCart(HashMap<Product, Integer> list) {
        this.cart = cart;
    }

}
