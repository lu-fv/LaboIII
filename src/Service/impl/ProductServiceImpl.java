package Service.impl;

import Enums.Category;
import Models.Product;
import Service.ProductService;
import Utils.Verification;


import java.util.*;

public class ProductServiceImpl implements ProductService {
    private static Set<Integer> idHashSet = new HashSet<>();

    //START--------------------------------------------------------------
    public static void add(Integer id) {
        idHashSet.add(id);
    }

    //CREAR--------------------------------------------------------------
    public static Integer createID() {
        Scanner scanner = new Scanner(System.in);
        Integer id = null;
        Boolean retry = true;
        do {//cambiar por excepción
            try {
                System.out.println("Id:");
                id = Integer.parseInt(scanner.nextLine());

                Verification.contains(idHashSet, id);

                idHashSet.add(id);
                retry = false;
            } catch (NumberFormatException e) {
                throw new NumberFormatException("¡¡Error!! ID inválido");
            } catch (IllegalArgumentException e) {
                System.out.println("Este ID ya existe");
            }
        } while (retry);
        return id;
    }

    public static String createName() {
        Scanner scanner = new Scanner(System.in);
        String name = null;

        System.out.println("Nombre:");
        name = scanner.nextLine();

        try {
            Verification.isEmpty(name);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("¡¡Error!! Completar nombre de producto");
        }


        return name;
    }

    public static String createBrand() {
        Scanner scanner = new Scanner(System.in);

        String brand = null;

        System.out.println("Marca:");
        brand = scanner.nextLine();

        try {
            Verification.isEmpty(brand);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("¡¡Error!! Completar marca");
        }


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
            throw new NumberFormatException("¡¡Error!! ID inválido");
        }
        return category;
    }

    //INTERNA: SELECCIONAR CATEGORÍA
    //-------------------DUDA: ¿DEBERÍA AGREGAR ESTOS DOS MÉTODOS A LA INTERFAZ?
    protected static Category selectCategory(Integer id) {
        for (Category c : Category.values()) {
            if (Objects.equals(id, c.getId())) {
                return c;
            }
        }
        throw new IllegalArgumentException("Esta categoría no existe");
    }

    //INTERNA: MOSTRAR CATEGORÍAS
    protected static void showCategories() {
        for (Category c : Category.values()) {
            System.out.println(c);
        }
    }

    public static void deleteID(Integer id) {
        idHashSet.remove(id);
    }

    //MODIFICAR----------------------------------------------------------
    public static void modify(Product product) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nombre:");
        String name = scanner.nextLine();
        product.setProductName(name);

        System.out.println("Marca:");
        String brand = scanner.nextLine();
        product.setBrand(brand);

        try {
            System.out.println("Id categoría:");
            ProductServiceImpl.showCategories();
            Integer categoryId = Integer.parseInt(scanner.nextLine());
            Category category = ProductServiceImpl.selectCategory(categoryId);
            product.setCategory(category);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("¡¡Error!! ID inválido");
        }

    }

    public static Integer askForID() {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Ingrese id"); //Pido ID
            Integer id = Integer.parseInt(sc.nextLine()); //Se ingresa un ID
            return id;
        } catch (NumberFormatException e) {
            System.out.println("¡¡Error!! Ingreso de datos erroneo");
        }
        return null;
    }
}
