package Service.impl;

import Enums.Category;
import Models.Food;
import Models.Supermarket;
import Service.FoodService;
import Utils.Verification;

import java.io.IOException;
import java.util.*;

public class FoodServiceImpl extends ProductServiceImpl implements FoodService {
    private Map<Integer, Food> foods;

    public FoodServiceImpl() {
        this.foods = new HashMap<>();
    }

    public Map<Integer, Food> getFoods() {
        return foods;
    }

    public void setFoods(Map<Integer, Food> foods) {
        this.foods = foods;
    }

    //AGREGAR
    @Override
    public Food add(Food food) throws IOException {
        foods.put(food.getID(), food);
        ProductPersistenceImpl.saveFoods(this);
        //ProductPersistenceImpl.saveProducts(foods, null);
        return food;
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
    public Food create() throws IOException {
        Boolean retry;
        Integer id = null;
        do {
            retry = false;
            try {
                id = ProductServiceImpl.createID();
                return this.add(new Food(id,
                        ProductServiceImpl.createName(),
                        ProductServiceImpl.createBrand(),
                        ProductServiceImpl.createCategory(),
                        createPerishable()));

            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println("\n" + e.getMessage());
                retry = true;
                ProductServiceImpl.deleteID(id);
            }
        } while (retry);
        return null;
    }

    //INTERNA CREAR: CREAR PERISHABLE
    private Boolean createPerishable() {
        Scanner scanner = new Scanner(System.in);
        Integer aux = null;

        try {
            System.out.println("Alimento perecedero:");
            System.out.println("1. Sí");
            System.out.println("2. No");
            aux = Integer.parseInt(scanner.nextLine());
            Verification.isOutOfBounds(aux, 1, 2);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("¡¡Error!! No se ingresó ninguna opción");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Opción no disponible");
        }

        if (aux == 1) {
            return true;
        } else {
            return false;
        }
    }

    //MODIFICAR
    @Override
    public Food modify(Integer id) throws IOException {
        Scanner sc = new Scanner(System.in);
        Integer opc = null;
        Food food = null;
        do {
            try {
                if ((food = foods.get(id)) != null) { //Si encuentro el alimento
                    System.out.println("> Datos actuales:");
                    System.out.println(food + "\n");
                    System.out.println("            [1] Modificar nombre");
                    System.out.println("            [2] Modificar marca");
                    System.out.println("            [3] Modificar categoría");
                    System.out.println("            [4] Modificar perecedero/no perecedero");
                    System.out.println("            [0] Salir");

                    opc = Integer.parseInt(sc.nextLine()); //Pregunto qué se quiere cambiar

                    if (opc == 4) {
                        food.setPerishable(createPerishable()); //Modifico los atributos propios de la clase Food
                    } else {
                        ProductServiceImpl.modify(food, opc); //Modifico los atributos de la superclase Product
                    }
                } else { //Si no encuentro el alimento
                    return null; //Retorno null
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            if (opc == 0) {
                return this.add(food); //Retorno el alimento modificado y reemplazado en el map
            }
        } while (true);
    }

    //ELIMINAR
    @Override
    public Food delete(Integer id) throws IOException { //AGREGAR PERSISTENCIA Y CONFIRMAR ELIMINACIÓN
        Scanner sc = new Scanner(System.in);
        Boolean retry;
        Integer opc = null;
        Food food = null;
        if ((food = foods.get(id)) != null) {
            SupermarketServiceImpl s = new SupermarketServiceImpl();
            if (!s.searchSpecialProductsByNameExist(food.getProductName())) {
                System.out.println(food);
                System.out.println("\nEliminar este producto");
                System.out.println("1.Sí");
                System.out.println("2.No");
                do {
                    retry = false;
                    try {
                        opc = Integer.parseInt(sc.nextLine());
                        Verification.isOutOfBounds(opc, 1, 2);

                        if (opc == 1) {
                            return remove(id);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("¡¡Error!! No se ingresó ninguna opción");
                        retry = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Opción no disponible");
                        retry = true;
                    }
                } while (retry);

            }
        }
        return null;
    }

    public Food remove(Integer id) {
        ProductServiceImpl.deleteID(id);
        return foods.remove(id);
    }

    //MOSTRAR TODOS LOS ELEMENTOS
    @Override
    public void showAll() {
        for (Map.Entry<Integer, Food> entry : foods.entrySet()) {
            System.out.println(entry.getValue() + "\n");
        }
    }

    public void startID() {
        for (Map.Entry<Integer, Food> entry : foods.entrySet()) {
            ProductServiceImpl.add(entry.getKey());
        }
    }
}
