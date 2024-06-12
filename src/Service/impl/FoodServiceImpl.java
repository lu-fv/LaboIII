package Service.impl;

import Enums.Category;
import Models.Food;
import Service.FoodService;

import java.util.*;

public class FoodServiceImpl implements FoodService {
    private Map<Integer, Food> foods;

    public FoodServiceImpl() {
        this.foods = new HashMap<>();
    }

    @Override
    public Food add(Food food) {
        return foods.put(food.getID(), food);
    }

    @Override
    public void addAll(List<Food> foodList) {
        for (Food f : foodList) {
            foods.put(f.getID(), f);
        }
    }

    @Override
    public Food create() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Id:");
        Integer id = Integer.parseInt(scanner.nextLine());

        System.out.println("Nombre:");
        String name = scanner.nextLine();

        System.out.println("Marca:");
        String brand = scanner.nextLine();

        System.out.println("Id categoría:");
        showCategories();
        Integer categoryId = Integer.parseInt(scanner.nextLine());
        Category category = selectCategory(categoryId);

        System.out.println("Precio:");
        Double price = Double.parseDouble(scanner.nextLine());

        System.out.println("Peso en kg:");
        Double kg = Double.parseDouble(scanner.nextLine());

        return this.add(new Food(id, name, price, brand, category, kg));
    }

    private Category selectCategory(Integer id) {
        if (id == 0) {
            return Category.DAIRY;
        } else if (id == 1) {
            return Category.BAKERY;
        } else if (id == 2) {
            return Category.GROCERY;
        } else if (id == 3) {
            return Category.ALCOHOL;
        } else {
            return null;
        }
    }

    private void showCategories() {
        System.out.println(Category.DAIRY.toString());
        System.out.println(Category.BAKERY.toString());
        System.out.println(Category.GROCERY.toString());
        System.out.println(Category.ALCOHOL.toString());
    }

    @Override
    public Food delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese id");
        Integer id = Integer.parseInt(scanner.nextLine());
        return foods.remove(id);
    }

    @Override
    public Food modify() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese id");
        Integer id = Integer.parseInt(scanner.nextLine());
        Food food;
        if ((food = foods.get(id)) != null) { //modificar todo o solo precio y kg
            System.out.println("Nombre:");
            String name = scanner.nextLine();

            System.out.println("Marca:");
            String brand = scanner.nextLine();

            System.out.println("Id categoría:");
            showCategories();
            Integer categoryId = Integer.parseInt(scanner.nextLine());
            Category category = selectCategory(categoryId);

            System.out.println("Precio:");
            Double price = Double.parseDouble(scanner.nextLine());

            System.out.println("Peso en kg:");
            Double kg = Double.parseDouble(scanner.nextLine());
            return this.add(new Food(id, name, price, brand, category, kg));
        } else {
            return null;
        }
    }

    @Override
    public void showAll() {
        for (Map.Entry<Integer, Food> entry : foods.entrySet()) {
            System.out.println(entry.getValue() + "\n");
        }
    }
}
