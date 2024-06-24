package Service.impl;

import Models.Supermarket;
import Service.MenusService;

import java.io.IOException;
import java.util.Scanner;

public class MenusServiceImpl implements MenusService {
    private FoodServiceImpl foodService;
    private BeverageServiceImpl beverageService;
    private SupermarketServiceImpl supermarketService;

    public MenusServiceImpl() {
        try {
            foodService = ProductPersistenceImpl.startFoodService();
            beverageService = ProductPersistenceImpl.startBeverageService();
            foodService.startID();
            beverageService.startID();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.supermarketService = new SupermarketServiceImpl();
    }

    @Override
    public void initialMenu() {

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
    }

    @Override
    public void clientMenu() {
        Scanner sc = new Scanner(System.in);
        Integer opc;

        do {
            System.out.println("======================= OFERTAS.COM ============================");
            System.out.println("\n");
            System.out.println("            [1] CREAR LISTA DE COMPRAS");
            System.out.println("            [2] COMPARADOR DE PRECIOS");
            System.out.println("            [3] EL MAS BARATO");
            System.out.println("            [0] SALIR\n");

            opc = sc.nextInt();

            switch (opc) {
                case 1:
                    break;
                case 2:
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
    public void shoppingListMenu() {
        Scanner sc = new Scanner(System.in);
        Integer opc;

        do {
            System.out.println("======================= LISTA DE COMPRAS ============================");
            System.out.println("\n");
            System.out.println("            [1] CREAR LISTA POR SUPERMERCADO");
            System.out.println("            [2] CREAR LISTA EN EL SUPERMERCADO MAS BARATO");
            System.out.println("            [3] CREAR LISTA CON EL MAS BARATO (indemendientemente del supermercado???????");
            System.out.println("            [4] TU LISTA DE COMPRAS");
            System.out.println("            [0] SALIR\n");

            opc = sc.nextInt();

            switch (opc) {
                case 1:
                    System.out.println("            [1] BUSCAR PRODUCTO POR CATEGORIA");
                    System.out.println("            [2] BUSCAR OFERTAS");
                    System.out.println("            [3] TODOS LOS PRODUCTOS");
                    break;
                case 2:
                    break;
                case 3:
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

    public void privateAccessMenu() {
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

    public void privateAccessProductMenu() {
        Scanner sc = new Scanner(System.in);
        Integer opc;

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
                    privateAccessModifyProductMenu();
                    break;
                case 3:
                    privateAccessDeleteProductMenu();
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

    public void privateAccessCreateProductMenu() {
        Scanner sc = new Scanner(System.in);
        Integer opc;

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
                    try {
                        foodService.create();
                    } catch (IOException e) {
                        System.out.println("Hubo un error al intentar guardar los cambios");
                    }
                    break;
                case 2: //Agregar throws y try-catch correspondientes
                    try {
                        beverageService.create();
                    } catch (IOException e) {
                        System.out.println("Hubo un error al intentar guardar los cambios");
                    }
                    break;
                default:
                    System.out.println("Opción no disponible");
                    break;
            }
        } while (opc != 0);
    }

    public void privateAccessModifyProductMenu() {
        Scanner sc = new Scanner(System.in);
        Integer opc;

        do {
            System.out.println("            [1] MODIFICAR ALIMENTO");
            System.out.println("            [2] MODIFICAR BEBIDA");
            System.out.println("            [0] SALIR\n");

            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 0:
                    System.out.println("Volviendo...");
                    break;
                case 1:
                    try {
                        if (foodService.modify(ProductServiceImpl.askForID()) != null) {
                            System.out.println("Información modificada");
                        } else {
                            System.out.println("ID no encontrado");
                        }
                    } catch (IOException e) {
                        System.out.println("Hubo un error al intentar guardar los cambios");
                    }

                    break;
                case 2: //Agregar throws y try-catch correspondientes
                    try {
                        if (beverageService.modify(ProductServiceImpl.askForID()) != null) {
                            System.out.println("Información modificada");
                        } else {
                            System.out.println("ID no encontrado");
                        }
                    } catch (IOException e) {
                        System.out.println("Hubo un error al intentar guardar los cambios");
                    }
                    break;
                default:
                    System.out.println("Opción no disponible");
                    break;
            }
        } while (opc != 0);
    }

    public void privateAccessDeleteProductMenu() { //AGREGAR BÚSQUEDA DE PRODUCTO EN SUPERMERCADOS
        Scanner sc = new Scanner(System.in);
        Integer opc;

        do {
            System.out.println("            [1] ELIMINAR ALIMENTO");
            System.out.println("            [2] ELIMINAR BEBIDA");
            System.out.println("            [0] SALIR\n");

            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 0:
                    System.out.println("Volviendo...");
                    break;
                case 1:

                    break;
                case 2:

                    break;
                default:
                    System.out.println("Opción no disponible");
                    break;
            }
        } while (opc != 0);
    }

    public void privateAccessSupermarketMenu() {
        Scanner sc = new Scanner(System.in);
        Integer opc;

        do {
            System.out.println("            [1] CREAR SUPERMERCADO");
            System.out.println("            [2] MI SUPERMERCADO");
            System.out.println("            [3] ELIMINAR SUPERMERCADO");
            System.out.println("            [0] SALIR\n");

            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 0:
                    System.out.println("Volviendo...");
                    break;
                case 1:

                    break;
                case 2:
                    privateAccessThisSupermarketMenu(null);
                    break;
                case 3:

                    break;
                default:
                    System.out.println("Opción no disponible");
                    break;
            }
        } while (opc != 0);
    }

    public void privateAccessThisSupermarketMenu(Supermarket supermarket) {
        Scanner sc = new Scanner(System.in);
        Integer opc;

        do {
            System.out.println("            [1] VER SUPERMERCADO");
            System.out.println("            [2] MODIFICAR SUPERMERCADO");
            System.out.println("            [3] AÑADIR PRODUCTO");
            System.out.println("            [4] MODIFICAR PRECIO");
            System.out.println("            [5] ELIMINAR PRODUCTO");
            System.out.println("            [0] SALIR\n");

            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 0:
                    System.out.println("Volviendo...");
                    break;
                case 1:

                    break;
                case 2:
                    supermarketService.modifySupermarket(supermarket);
                    break;
                case 3:

                    break;
                default:
                    System.out.println("Opción no disponible");
                    break;
            }
        } while (opc != 0);
    }


}
