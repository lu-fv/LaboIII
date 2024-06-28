package Service.impl;

import Enums.Category;
import Models.Product;
import Service.ProductService;
import Utils.Verification;


import java.util.*;

public class ProductServiceImpl implements ProductService {
    private static Set<Integer> idHashSet = new HashSet<>();

    //GETTER================================================================
    public static Set<Integer> getIdHashSet() {
        return idHashSet;
    }

    //METHODS===============================================================
    //add-------------------------------------------------------------------
    public static void add(Integer id) {
        idHashSet.add(id);
    }

    //create----------------------------------------------------------------
    public static Integer createID() {
        Scanner scanner = new Scanner(System.in);
        Integer id = null;
        Boolean retry = true;
        do {
            try {
                System.out.println("Id:"); //Pido ID
                id = Integer.parseInt(scanner.nextLine());

                Verification.contains(idHashSet, id); //Verifico que el ID no exista en el sistema

                idHashSet.add(id); //Si el ID no está duplicado, se agrega al set
                retry = false;
            } catch (NumberFormatException e) {
                throw new NumberFormatException("¡¡Error!! ID inválido");
            } catch (IllegalArgumentException e) {
                System.out.println("Este ID ya existe");
            }
        } while (retry); //Repito hasta que no haya problemas con los datos ingresados
        return id;
    }

    public static String createName() {
        Scanner scanner = new Scanner(System.in);
        String name = null;

        System.out.println("Nombre:");
        name = scanner.nextLine();

        try {
            Verification.isEmpty(name); //Verifica que se hayan ingresado datos
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
            Verification.isEmpty(brand); //Verifica que se hayan ingresado datos
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
            showCategories(); //Muestro categorías
            Integer categoryId = Integer.parseInt(scanner.nextLine());
            category = selectCategory(categoryId); //Busco la categoría seleccionada
        } catch (NumberFormatException e) {
            throw new NumberFormatException("¡¡Error!! ID inválido");
        }
        return category;
    }

    //INTERNA: SELECCIONAR CATEGORÍA
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

    //modify----------------------------------------------------------------
    public static void modify(Product product, Integer opc) { //Recibo el producto y el dato que quiero modificar
        Scanner scanner = new Scanner(System.in);

        switch (opc) {
            case 0:
                System.out.println("Volviendo...");
                break;
            case 1:
                System.out.println("Nombre:");
                String name = scanner.nextLine();
                product.setProductName(name);
                break;
            case 2:
                System.out.println("Marca:");
                String brand = scanner.nextLine();
                product.setBrand(brand);
                break;
            case 3:
                try {
                    System.out.println("Id categoría:");
                    ProductServiceImpl.showCategories();
                    Integer categoryId = Integer.parseInt(scanner.nextLine());
                    Category category = ProductServiceImpl.selectCategory(categoryId);
                    product.setCategory(category);
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("¡¡Error!! ID inválido");
                }
                break;
            default:
                System.out.println("Opción no disponible");
                break;
        }
    }

    //delete----------------------------------------------------------------
    public static void deleteID(Integer id) {
        idHashSet.remove(id);
    }

    public static Integer askForID() { //Pido un ID y lo devuelvo si no hay problemas con los datos ingresados
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
