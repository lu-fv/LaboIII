package Models;

import java.util.HashMap;

public class Cart {

    private double totalPrice;
    private HashMap<Product, Integer> List;

    public Cart() {
    }

    public Cart(double totalPrice, HashMap<Product, Integer> list) {
        this.totalPrice = totalPrice;
        List = list;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public HashMap<Product, Integer> getList() {
        return List;
    }

    public void setList(HashMap<Product, Integer> list) {
        List = list;
    }

}
