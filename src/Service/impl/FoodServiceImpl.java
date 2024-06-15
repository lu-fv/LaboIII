package Service.impl;

import Enums.Category;
import Models.Food;
import Service.FoodService;

import java.text.ParseException;
import java.util.*;
//CREAR CLASE PRODUCTO IMPL (HERENCIA)

public class FoodServiceImpl extends ProductServiceImpl implements FoodService {
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
        try {
            return this.add(new Food(ProductServiceImpl.createID(),
                    ProductServiceImpl.createName(),
                    ProductServiceImpl.createBrand(),
                    ProductServiceImpl.createCategory(),
                    createPerishable()));

        } catch (NumberFormatException e) {
            throw new NumberFormatException("Ingreso de datos erroneo");
        }
    }

    //INTERNA CREAR: CREAR PERISHABLE
    private Boolean createPerishable() {
        Scanner scanner = new Scanner(System.in);

        Boolean perishable = null;
        Integer aux = null;

        System.out.println("Alimento perecedero:");
        System.out.println("1. Sí");
        System.out.println("2. No");
        do { //Cambiar a excepción
            aux = Integer.parseInt(scanner.nextLine());
        } while (aux != 1 && aux != 2);

        if (aux == 1) {
            perishable = true;
        } else {
            perishable = false;
        }
        return perishable;
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
            ProductServiceImpl.showCategories();
            Integer categoryId = Integer.parseInt(scanner.nextLine());
            Category category = ProductServiceImpl.selectCategory(categoryId);
            food.setCategory(category);

            food.setPerishable(createPerishable());
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
