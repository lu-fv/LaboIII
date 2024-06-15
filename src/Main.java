import Enums.Category;
import Service.impl.FoodServiceImpl;
import Service.impl.MenusServiceImpl;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        FoodServiceImpl foodService = new FoodServiceImpl();

        /*foodService.create();
        foodService.create();
        foodService.showAll();
        foodService.delete();
        foodService.showAll();
        foodService.modify();
        foodService.showAll();*/


        MenusServiceImpl menu = new MenusServiceImpl();

        menu.initialMenu();

    }
}