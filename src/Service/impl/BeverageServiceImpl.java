package Service.impl;

import Enums.Category;
import Models.Beverage;
import Models.Food;
import Service.BeverageService;
import Utils.Verification;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class BeverageServiceImpl implements BeverageService, Serializable {
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
        //ProductPersistenceImpl.saveProducts(null, beverages);
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
        Scanner sc = new Scanner(System.in);
        Integer opc = null;
        Beverage beverage = null;
        do {
            try {
                if ((beverage = beverages.get(id)) != null) { //Si encuentro la bebida
                    System.out.println("> Datos actuales:");
                    System.out.println(beverage + "\n");
                    System.out.println("            [1] Modificar nombre");
                    System.out.println("            [2] Modificar marca");
                    System.out.println("            [3] Modificar categoría");
                    System.out.println("            [4] Modificar litros");
                    System.out.println("            [0] Salir");

                    opc = Integer.parseInt(sc.nextLine()); //Pregunto qué se quiere cambiar

                    if (opc == 4) {
                        beverage.setLitres(createLitres()); //Modifico los atributos propios de la clase Beverage
                    } else {
                        ProductServiceImpl.modify(beverage, opc); //Modifico los productos de la superclase Product
                    }
                } else {
                    return null; //Retorno null
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            if (opc == 0) {
                return this.add(beverage); //Retorno la bebida modificada y reemplazada en el map
            }
        } while (true); //Repito hasta que no haya problemas
    }

    @Override
    public Beverage delete(Integer id) throws IOException {
        Scanner sc = new Scanner(System.in);
        Boolean retry;
        Integer opc = null;
        Beverage beverage = null;
        if ((beverage = beverages.get(id)) != null) {
            SupermarketServiceImpl s = new SupermarketServiceImpl();
            if (!s.searchSpecialProductsByNameExist(beverage.getProductName())) {
                System.out.println(beverage);
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

    public Beverage remove(Integer id) {
        ProductServiceImpl.deleteID(id);
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
