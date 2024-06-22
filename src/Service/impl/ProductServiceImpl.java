package Service.impl;

import Enums.Category;
import java.util.*;

public class ProductServiceImpl {
    private static Set<Integer> idHashSet = new HashSet<>();

    //CREAR--------------------------------------------------------------
    public static Integer createID() {
        Scanner scanner = new Scanner(System.in);
        Integer id = null;
        try {
            do {//cambiar por excepción
                System.out.println("Id:");
                id = Integer.parseInt(scanner.nextLine());
            } while (idHashSet.contains(id));

            idHashSet.add(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Ingreso de datos erroneo");
        }
        return id;
    }

    public static String createName() {
        Scanner scanner = new Scanner(System.in);
        String name = null;

        System.out.println("Nombre:");
        name = scanner.nextLine();

        return name;
    }

    public static String createBrand() {
        Scanner scanner = new Scanner(System.in);

        String brand = null;

        System.out.println("Marca:");
        brand = scanner.nextLine();

        return brand;
    }

    public static Category createCategory() {
        Scanner scanner = new Scanner(System.in);

        Category category = null;

        try {
            System.out.println("Id categoría:");
            showCategories();
            Integer categoryId = Integer.parseInt(scanner.nextLine());
            category = selectCategory(categoryId);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Ingreso de datos erroneo");
        }
        return category;
    }

    //INTERNA CREAR: SELECCIONAR CATEGORÍA
    protected static Category selectCategory(Integer id) {
        for (Category c : Category.values()) {
            if (Objects.equals(id, c.getId())) {
                return c;
            }
        }
        return null;
    }

    //INTERNA CREAR: MOSTRAR CATEGORÍAS
    protected static void showCategories() {
        for (Category c : Category.values()) {
            System.out.println(c);
        }
    }

}
