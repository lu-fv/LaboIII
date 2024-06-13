package Service.impl;

import Enums.Category;
import Models.Food;
import Service.FoodService;

import java.text.ParseException;
import java.util.*;
//CREAR CLASE PRODUCTO IMPL (HERENCIA)

public class FoodServiceImpl implements FoodService {
    private Map<Integer, Food> foods;

    public FoodServiceImpl() {
        this.foods = new HashMap<>();
    }

    //AGREGAR
    @Override
    public Food add(Food food) {
        return foods.put(food.getID(), food);
    }

    //AGREGAR TODOS
    @Override
    public void addAll(List<Food> foodList) {
        for (Food f : foodList) {
            foods.put(f.getID(), f);
        }
    }

    //CREAR
    @Override
    public Food create() {
        Scanner scanner = new Scanner(System.in);

        Integer id = null;
        String name = null;
        Double price = null;
        String brand = null;
        Category category = null;
        Double kg = null;

        try {
            System.out.println("Id:");
            id = Integer.parseInt(scanner.nextLine());

            System.out.println("Nombre:");
            name = scanner.nextLine();

            System.out.println("Marca:");
            brand = scanner.nextLine();

            System.out.println("Id categoría:");
            showCategories();
            Integer categoryId = Integer.parseInt(scanner.nextLine());
            category = selectCategory(categoryId);

            System.out.println("Precio:");
            price = Double.parseDouble(scanner.nextLine());

            System.out.println("Peso en kg:");
            kg = Double.parseDouble(scanner.nextLine());

        } catch (NumberFormatException e) {
            throw new NumberFormatException("Ingreso de datos erroneo");
        }
        return this.add(new Food(id, name, price, brand, category, kg));
    }

    //INTERNA CREAR: SELECCIONAR CATEGORÍA
    private Category selectCategory(Integer id) {
        for (Category c : Category.values()) {
            if (id == c.getId()) {
                return c;
            }
        }
        return null;
    }

    //INTERNA CREAR: MOSTRAR CATEGORÍAS
    private void showCategories() {
        for (Category c : Category.values()) {
            System.out.println(c);
        }
    }

    //ELIMINAR
    @Override
    public Food delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese id");
        Integer id = Integer.parseInt(scanner.nextLine());
        return foods.remove(id);
    }

    //MODIFICAR
    @Override
    public Food modify() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese id");
        Integer id = Integer.parseInt(scanner.nextLine());
        Food food;
        if ((food = foods.get(id)) != null) {
            System.out.println("Nombre:");
            String name = scanner.nextLine();
            food.setProductName(name);

            System.out.println("Marca:");
            String brand = scanner.nextLine();
            food.setBrand(brand);

            System.out.println("Id categoría:");
            showCategories();
            Integer categoryId = Integer.parseInt(scanner.nextLine());
            Category category = selectCategory(categoryId);
            food.setCategory(category);

            System.out.println("Precio:");
            Double price = Double.parseDouble(scanner.nextLine());
            food.setPrice(price);

            System.out.println("Peso en kg:");
            Double kg = Double.parseDouble(scanner.nextLine());
            return foods.put(food.getID(), food);
        } else {
            return null;
        }
    }

    //MOSTRAR TODOS LOS ELEMENTOS
    @Override
    public void showAll() {
        for (Map.Entry<Integer, Food> entry : foods.entrySet()) {
            System.out.println(entry.getValue() + "\n");
        }
    }
}
