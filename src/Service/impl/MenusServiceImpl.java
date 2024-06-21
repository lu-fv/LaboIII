package Service.impl;

import Enums.Category;
import Models.Product;
import Models.ProductForSale;
import Models.Supermarket;
import Service.MenusService;
import Service.SupermarketService;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class MenusServiceImpl implements MenusService {
    protected FoodServiceImpl foodService;
    protected BeverageServiceImpl beverageService;
    protected SupermarketService supermarketService;
    protected ProductForSaleServiceImpl productForSale;

    public MenusServiceImpl() {
        this.foodService = new FoodServiceImpl();
        this.beverageService = new BeverageServiceImpl();
        this.supermarketService = new SupermarketServiceImpl();
        this.productForSale = new ProductForSaleServiceImpl();
    }

    @Override
    public void initialMenu() throws IOException {

        Scanner sc = new Scanner(System.in);
        Integer opc;

        do {
            System.out.println("================= MENU PRINCIPAL ======================");
            System.out.println("\n");
            System.out.println("            [1] MENU USUARIO");
            System.out.println("            [2] ACCESO PRIVADO");
            System.out.println("            [0] SALIR\n");

            opc = sc.nextInt();

            switch (opc) {
                case 1:
                    clientMenu();
                    break;
                case 2:
                    privateAccessMenu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Ingrese una opcion correcta!");
                    break;
            }

        } while (opc != 0);
    }//listo

    @Override
    public void clientMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        Integer opc;

        do {
            System.out.println("======================= CREAR LISTA ============================");
            System.out.println("\n");
            System.out.println("            [1] CREAR LISTA POR SUPERMERCADO");
            System.out.println("            [2] CREAR LISTA EN GENERAL");
            System.out.println("            [0] SALIR\n");

            opc = sc.nextInt();

            switch (opc) {
                case 1:

                    break;
                case 2:
                    shoppingListMenu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Ingrese una opcion correcta!");
                    break;
            }

        } while (opc != 0);
    }

    @Override
    public void shoppingListMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        Integer opc;

        do {
            System.out.println("======================= BUSQUEDA DE PRODUCTOS EN TODOS LOS SUPERMERCADOS ============================");
            System.out.println("\n");
            System.out.println("            [1] POR NOMBRE DE PRODUCTO");
            System.out.println("            [2] POR PRODUCTOS EN OFERTAS");
            System.out.println("            [3] POR CATEGORIA");
            System.out.println("            [4] TU LISTA DE COMPRAS");
            System.out.println("            [0] SALIR\n");

            opc = sc.nextInt();

            switch (opc) {
                case 1:
                    System.out.println("Ingrese el nombre del producto que esta buscando");
                    String nameProduct = sc.nextLine();
                    System.out.println(">>>>>>>>>>>>>>>>>> LISTA DE PRODUCTOS <<<<<<<<<<<<<<<<<<<<<<");
                    supermarketService.searchSpecialProductsByName(nameProduct).forEach(System.out::println);
                    break;
                case 2:
                    System.out.println(">>>>>>>>>>>>>>>>>> LISTA DE PRODUCTOS EN OFERTA <<<<<<<<<<<<<<<<<<<<<<");
                    supermarketService.searchSalesProducts().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Ingrese la categoria que desee : ");
                    Integer categorySelect;
                    do {
                        categorySelect = sc.nextInt();
                        ProductServiceImpl.showCategories();
                        switch (categorySelect) {
                            case 0:
                                supermarketService.searchProductsByCategory(Category.DAIRY).forEach(System.out::println);
                                break;
                            case 1:
                                supermarketService.searchProductsByCategory(Category.BAKERY).forEach(System.out::println);
                                break;
                            case 2:
                                supermarketService.searchProductsByCategory(Category.GROCERY).forEach(System.out::println);
                                break;
                            case 3:
                                supermarketService.searchProductsByCategory(Category.ALCOHOL).forEach(System.out::println);
                                break;
                            default:
                                System.out.println("Ingrese un numero de categoria correcto");
                                break;
                        }
                    } while (categorySelect != 0 && categorySelect != 1 && categorySelect != 2 && categorySelect != 3);
                    break;
                case 4:
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Ingrese una opcion correcta!");
                    break;
            }
        } while (opc != 0);
    }

    @Override
    public void privateAccessMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        Integer opc;

        do {
            System.out.println("======================= ADMINISTRACIÓN ============================");
            System.out.println("\n");
            System.out.println("            [1] PRODUCTOS");
            System.out.println("            [2] SUPERMERCADOS");
            System.out.println("            [0] SALIR\n");

            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 0:
                    System.out.println("Volviendo...");
                    break;
                case 1:
                    privateAccessProductMenu();
                    break;
                case 2:
                    privateAccessSupermarketMenu();
                    break;
                default:
                    System.out.println("Opción no disponible");
                    break;
            }
        } while (opc != 0);
    }

    @Override
    public void privateAccessProductMenu() {
        Scanner sc = new Scanner(System.in);
        Integer opc;

        System.out.println("======================= ABML PRODUCTOS ============================");
        do {
            System.out.println("            [1] CREAR PRODUCTO");
            System.out.println("            [2] MODIFICAR PRODUCTO");
            System.out.println("            [3] ELIMINAR PRODUCTO");
            System.out.println("            [4] VER PRODUCTOS");
            System.out.println("            [0] SALIR\n");

            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 0:
                    System.out.println("Volviendo...");
                    break;
                case 1:
                    privateAccessCreateProductMenu();
                    break;
                case 2:
                    foodService.modify();
                    break;
                case 3:
                    foodService.delete();
                    break;
                case 4:
                    System.out.println("=BEBIDAS============");
                    beverageService.showAll();
                    System.out.println("=COMIDAS============");
                    foodService.showAll();
                    break;
                default:
                    System.out.println("Opción no disponible");
                    break;
            }
        } while (opc != 0);
    }

    @Override
    public void privateAccessCreateProductMenu() {
        Scanner sc = new Scanner(System.in);
        Integer opc;

        System.out.println("======================= CREAR PRODUCTO ============================");
        do {
            System.out.println("            [1] CREAR ALIMENTO");
            System.out.println("            [2] CREAR BEBIDA");
            System.out.println("            [0] SALIR\n");

            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 0:
                    System.out.println("Volviendo...");
                    break;
                case 1:
                    foodService.create();
                    break;
                case 2:
                    beverageService.create();
                    break;
                default:
                    System.out.println("Opción no disponible");
                    break;
            }
        } while (opc != 0);
    }

    @Override
    public void privateAccessSupermarketMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        Integer opc;
        Supermarket supermarketSelect = new Supermarket();

        for (Map.Entry<Integer, Supermarket> entry : supermarketService.supermarketsListJson().entrySet()) {
            System.out.println(entry);
            System.out.println("Presione SI en el supermercado deseado sino presione cualquier tecla");
            if (sc.nextLine().equalsIgnoreCase("si")) {
                supermarketSelect = entry.getValue();
                break;
            }
        }

        if (supermarketSelect != null || supermarketService.supermarketsListJson().isEmpty()) {
            System.out.println("======================= ABML SUPERMERCADOS ============================");
            do {
                System.out.println("            [1] CREAR SUPERMERCADO");
                System.out.println("            [2] MODIFICAR SUPERMERCADO");
                System.out.println("            [3] ELIMINAR SUPERMERCADO");
                System.out.println("            [4] AÑADIR PRODUCTO");
                System.out.println("            [5] MODIFICAR PRECIO");
                System.out.println("            [6] ELIMINAR PRODUCTO");
                System.out.println("            [0] SALIR\n");

                opc = Integer.parseInt(sc.nextLine());

                switch (opc) {
                    case 0:
                        System.out.println("Volviendo...");
                        break;
                    case 1:
                        supermarketService.addSupermarket();
                        break;
                    case 2:
                        supermarketService.modifySupermarket(supermarketSelect.getName());
                        break;
                    case 3:
                        supermarketService.deleteSupermarket();
                        break;
                    case 4:
                        productForSale.addProductForSale(supermarketSelect);
                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                    default:
                        System.out.println("Opción no disponible");
                        break;
                }
            } while (opc != 0);
        } else {
            System.out.println("El supermercado que desea no se encuentra en el listado. Desea agregarlo? si o no");
            if (sc.nextLine().equalsIgnoreCase("si")) {
                supermarketService.addSupermarket();
            } else {
                System.out.println("volviendo al menu anterior");
            }
        }

    }

}
