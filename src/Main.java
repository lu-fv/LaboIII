import Enums.Category;
import Models.Food;
import Models.Product;
import Models.Supermarket;
import Service.SupermarketService;
import Service.impl.SupermarketServiceImpl;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {

        HashSet<Product> p= new HashSet<>();
        p.add(new Food(1, "PAPA", 2, Category.GROCERY, 5d));

        Supermarket s= new Supermarket("vea", "indep 123", "25515", "30525522554", p);
        SupermarketService a= new SupermarketServiceImpl();
        a.saveSupermarketInJsonFile(s);

    }
}