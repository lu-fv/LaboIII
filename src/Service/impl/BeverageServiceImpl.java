package Service.impl;

import Enums.Category;
import Models.Beverage;
import Service.BeverageService;

import java.util.*;

public class BeverageServiceImpl implements BeverageService {
    private Map<Integer, Beverage> beverages;

    public BeverageServiceImpl() {
        this.beverages = new HashMap<>();
    }

    @Override
    public Beverage add(Beverage beverage) {
        return beverages.put(beverage.getID(), beverage);
    }

    @Override
    public void addAll(List<Beverage> beverageList) {
        for (Beverage b : beverageList) {
            beverages.put(b.getID(), b);
        }
    }

    @Override
    public Beverage create() {
        try {
            return this.add(new Beverage(ProductServiceImpl.createID(),
                    ProductServiceImpl.createName(),
                    ProductServiceImpl.createBrand(),
                    ProductServiceImpl.createCategory(),
                    createLitres()));
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Ingreso de datos erroneo");
        }
    }

    private Double createLitres() {
        Scanner scanner = new Scanner(System.in);
        Double litres = null;
        try {
            System.out.println("Contenido en litros");
            litres = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Ingreso de datos erroneo");
        }

        return litres;
    }

    @Override
    public Beverage modify() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese id");
        Integer id = Integer.parseInt(scanner.nextLine());
        Beverage beverage;
        if ((beverage = beverages.get(id)) != null) {
            System.out.println("Nombre:");
            String name = scanner.nextLine();
            beverage.setProductName(name);

            System.out.println("Marca:");
            String brand = scanner.nextLine();
            beverage.setBrand(brand);

            System.out.println("Id categoría:");
            ProductServiceImpl.showCategories();
            Integer categoryId = Integer.parseInt(scanner.nextLine());
            Category category = ProductServiceImpl.selectCategory(categoryId);
            beverage.setCategory(category);

            beverage.setLitres(createLitres());
            return beverages.put(beverage.getID(), beverage);
        } else {
            return null;
        }
    }

    @Override
    public Beverage delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese id");
        Integer id = Integer.parseInt(scanner.nextLine());
        return beverages.remove(id);
    }

    @Override
    public void showAll() {
        for (Map.Entry<Integer, Beverage> entry : beverages.entrySet()) {
            System.out.println(entry.getValue() + "\n");
        }
    }
}
