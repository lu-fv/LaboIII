package Service.impl;

import Enums.Category;
import Models.Beverage;
import Models.Food;
import Service.BeverageService;

import java.io.IOException;
import java.util.*;

public class BeverageServiceImpl implements BeverageService {
    private Map<Integer, Beverage> beverages;

    public BeverageServiceImpl() {
        this.beverages = new HashMap<>();
    }

    public Map<Integer, Beverage> getBeverages() {
        return beverages;
    }

    public void setBeverages(Map<Integer, Beverage> beverages) {
        this.beverages = beverages;
    }

    @Override
    public Beverage add(Beverage beverage) throws IOException {
        beverages.put(beverage.getID(), beverage);
        ProductPersistenceImpl.saveBeverages(this);
        return beverage;
    }

    @Override
    public void addAll(List<Beverage> beverageList) {
        for (Beverage b : beverageList) {
            beverages.put(b.getID(), b);
        }
    }

    @Override
    public Beverage create() throws IOException {
        Boolean retry;
        Integer id = null;
        do {
            retry = false;
            try {
                id = ProductServiceImpl.createID();
                return this.add(new Beverage(id,
                        ProductServiceImpl.createName(),
                        ProductServiceImpl.createBrand(),
                        ProductServiceImpl.createCategory(),
                        createLitres()));
            } catch (IllegalArgumentException e) {
                System.out.println("\n" + e.getMessage());
                retry = true;
                ProductServiceImpl.deleteID(id);
            }
        } while (retry);
        return null;
    }

    private Double createLitres() {
        Scanner scanner = new Scanner(System.in);
        Double litres = null;
        try {
            System.out.println("Contenido en litros");
            litres = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("¡¡Error!! Cantidad de litros inválida");
        }

        return litres;
    }

    @Override
    public Beverage modify(Integer id) throws IOException {
        Beverage beverage;
        do {
            try {
                if ((beverage = beverages.get(id)) != null) { //Si encuentro la bebida
                    ProductServiceImpl.modify(beverage); //Modifico los productos de la superclase Product
                    beverage.setLitres(createLitres()); //Modifico los atributos propios de la clase Beverage
                    return this.add(beverage); //Retorno la bebida modificada y reemplazada en el map
                } else {
                    return null; //Retorno null
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true); //Repito hasta que no haya problemas
    }

    @Override
    public Beverage delete(Integer id) {
        return beverages.remove(id);
    }

    @Override
    public void showAll() {
        for (Map.Entry<Integer, Beverage> entry : beverages.entrySet()) {
            System.out.println(entry.getValue() + "\n");
        }
    }

    public void startID() {
        for (Map.Entry<Integer, Beverage> entry : beverages.entrySet()) {
            ProductServiceImpl.add(entry.getKey());
        }
    }
}
