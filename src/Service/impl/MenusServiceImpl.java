package Service.impl;

import Enums.Category;
import Exceptions.ExceptionIncorrectPassword;
import Models.Beverage;
import Models.Food;
import Models.ProductForSale;
import Models.Supermarket;
import Service.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MenusServiceImpl implements MenusService {
    private static final String privateAccesKey = "1234";
    protected FoodServiceImpl foodService;
    protected BeverageServiceImpl beverageService;
    protected SupermarketService supermarketService = new SupermarketServiceImpl();
    protected CartService cartService = new CartServiceImpl();
    protected ProductForSaleService productForSaleService = new ProductForSaleServiceImpl();
    protected ProductServiceImpl productService = new ProductServiceImpl();

    public MenusServiceImpl() throws IOException {
        try {
            foodService = ProductPersistenceImpl.startFoodService();
            beverageService = ProductPersistenceImpl.startBeverageService();
            foodService.startID();
            beverageService.startID();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialMenu() throws IOException {

        Scanner sc = new Scanner(System.in);
        Integer opc = null;
        Boolean retry;

        do {
            System.out.println("================= MENU PRINCIPAL ======================");
            System.out.println("\n");
            System.out.println("            [1] MENU USUARIO");
            System.out.println("            [2] ACCESO PRIVADO");
            System.out.println("            [0] SALIR\n");

            do {
                retry = false;
                try {
                    System.out.print(">");
                    opc = Integer.parseInt(sc.nextLine());

                    switch (opc) {
                        case 1:
                            clientMenu();
                            break;
                        case 2:
                            try {
                                Boolean ok = false;
                                Integer attempts = 3;
                                do {
                                    System.out.println("Ingrese la clave de acceso: ");
                                    Scanner scanner = new Scanner(System.in);
                                    String key = scanner.nextLine();
                                    if (key.equalsIgnoreCase(privateAccesKey)) {
                                        ok = true;
                                    } else {
                                        System.out.println("Clave incorrecta");
                                    }
                                    attempts--;
                                } while (attempts > 0 && !ok);
                                if (!ok) {
                                    throw new ExceptionIncorrectPassword("Todas las contraseñas ingresadas son incorrectas. Llame al 111");
                                } else {
                                    privateAccessMenu();
                                }
                            } catch (ExceptionIncorrectPassword e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Ingrese una opcion correcta!");
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Opción no disponible");
                    retry = true;
                }
            } while (retry);

        } while (opc != 0);
    }//LISTO CON CONTRASEÑA VALIDADA/TRES INTENTOS

    @Override
    public void clientMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        Integer opc = null;
        Boolean retry;

        do {
            System.out.println("======================= CREAR LISTA ============================");
            System.out.println("\n");
            System.out.println("            [1] CREAR LISTA POR SUPERMERCADO");
            System.out.println("            [2] CREAR LISTA EN GENERAL");
            System.out.println("            [0] SALIR\n");

            do {
                retry = false;
                try {
                    System.out.print(">");
                    opc = Integer.parseInt(sc.nextLine());

                    switch (opc) {
                        case 1:
                            shoppingListMenuBySupermarket();
                            break;
                        case 2:
                            shoppingListMenu();
                            break;
                        case 0:
                            System.out.println("\n\n\n\n\n");
                            break;
                        default:
                            System.out.println("Ingrese una opcion correcta!");
                            break;

                    }
                } catch (NumberFormatException e) {
                    System.out.println("Opción no disponible");
                    retry = true;
                }
            } while (retry);

        } while (opc != 0);
    }//LISTO

    @Override
    public void shoppingListMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        Integer opc = null;
        Boolean retry;

        do {
            System.out.println("======================= BUSQUEDA DE PRODUCTOS EN TODOS LOS SUPERMERCADOS ============================");
            System.out.println("\n");
            System.out.println("            [1] POR NOMBRE DE PRODUCTO");
            System.out.println("            [2] POR PRODUCTOS EN OFERTAS");
            System.out.println("            [3] POR CATEGORIA");
            System.out.println("            [4] TU LISTA DE COMPRAS");
            System.out.println("            [5] MODIFICAR TU LISTA DE COMPRAS");
            System.out.println("            [0] SALIR\n");

            do {
                retry = false;
                try {
                    System.out.print(">");
                    opc = Integer.parseInt(sc.nextLine());

                    switch (opc) {
                        case 1:
                            //region Busqueda por nombre de producto en TODOS los supermercados
                            System.out.println("Ingrese el nombre del producto que esta buscando");
                            String nameProduct = sc.nextLine();
                            List<ProductForSale> productsList = supermarketService.searchSpecialProductsByName(nameProduct);
                            if (!productsList.isEmpty()) {
                                cartService.addCartFromListProductForSale(productsList);
                            } else {
                                System.out.println("El supermercado aun no tiene productos con esa descripcion");
                            }
                            //endregion
                            break;
                        case 2:
                            //region Busqueda por oferta en TODOS los supermercados
                            Integer numProductSale;
                            List<ProductForSale> productsInSale = supermarketService.searchSalesProducts();
                            System.out.println(">>>>>>>>>>>>>>>>>> LISTA DE PRODUCTOS EN OFERTA <<<<<<<<<<<<<<<<<<<<<<");
                            if (!productsInSale.isEmpty()) {
                                cartService.addCartFromListProductForSale(productsInSale);
                            } else {
                                System.out.println("El supermercado aun no tiene productos en oferta");
                            }
                            //endregion
                            break;
                        case 3:
                            //region Busqueda de productos por categoria en TODOS los supermercados
                            System.out.println("Ingrese la categoria que desee : ");
                            Integer categorySelect;
                            do {
                                ProductServiceImpl.showCategories();
                                categorySelect = sc.nextInt();
                                switch (categorySelect) {
                                    case 0:
                                        List<ProductForSale> listDairy = supermarketService.searchProductsByCategory(Category.DAIRY);
                                        if (!listDairy.isEmpty()) {
                                            cartService.addCartFromListProductForSale(listDairy);
                                        } else {
                                            System.out.println("No existe la categoria lacteos en los supermercados");
                                        }
                                        break;
                                    case 1:
                                        List<ProductForSale> listBakery = supermarketService.searchProductsByCategory(Category.BAKERY);
                                        if (!listBakery.isEmpty()) {
                                            cartService.addCartFromListProductForSale(listBakery);
                                        } else {
                                            System.out.println("No existe la categoria panaderia en los supermercados");
                                        }
                                        break;
                                    case 2:
                                        List<ProductForSale> listGrocery = supermarketService.searchProductsByCategory(Category.GROCERY);
                                        if (!listGrocery.isEmpty()) {
                                            cartService.addCartFromListProductForSale(listGrocery);
                                        } else {
                                            System.out.println("No existe la categoria alimentos en los supermercados");
                                        }
                                        break;
                                    case 3:
                                        List<ProductForSale> listAlcohol = supermarketService.searchProductsByCategory(Category.ALCOHOL);
                                        if (!listAlcohol.isEmpty()) {
                                            cartService.addCartFromListProductForSale(listAlcohol);
                                        } else {
                                            System.out.println("No existe la categoria bebidas alcoholicas en los supermercados");
                                        }
                                        break;
                                    default:
                                        System.out.println("Ingrese un numero de categoria correcto");
                                        break;
                                }
                            } while (categorySelect != 0 && categorySelect != 1 && categorySelect != 2 && categorySelect != 3);
                            //endregion
                            break;
                        case 4:
                            //region Mostrar carrito
                            cartService.totalPriceOfCart();
                            cartService.showCartsProductList();
                            //endregion
                            break;
                        case 5:
                            //region Modificar algo del carrito
                            do {
                                System.out.println("[1] Desea eliminar un producto");
                                System.out.println("[2] Desea modificar cantidad de un  producto");
                                System.out.println("[0] Salir");
                                opc = sc.nextInt();

                                switch (opc) {
                                    case 1:
                                        cartService.deleteSomeProductOfCart();
                                        break;
                                    case 2:
                                        cartService.modifyCartList();
                                        break;
                                    case 0:
                                        break;
                                    default:
                                        System.out.println("Ingrese una opcion correcta");
                                        break;
                                }
                            } while (opc != 0);
                            //endregion
                            break;
                        case 0:
                            System.out.println("\n\n\n\n\n");
                            break;
                        default:
                            System.out.println("Ingrese una opcion correcta!");
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Opción no disponible");
                    retry = true;
                }
            } while (retry);
        } while (opc != 0);
    }//LISTO SOLO FALTA PROBAR

    @Override
    public void shoppingListMenuBySupermarket() throws IOException {
        Scanner sc = new Scanner(System.in);
        Integer opc = null;
        Boolean retry;
        Integer attempts = 3;//numero de intentos para que no se haga un bucle infinito hasta que ingrese una opcion correcta dentro del do/while
        Supermarket supermarketSelect = new Supermarket();
        List<ProductForSale> productsForSale = new ArrayList<>();

        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<< LISTADO DE SUPERMERCADOS >>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (Map.Entry<String, Supermarket> entry : supermarketService.supermarketsListJson().entrySet()) {
            System.out.println(entry);
            System.out.println("_______________________________________________________________________________");
        }
        System.out.println("Ingrese el supermercado deseado: ");

        //region Validacion de ingreso correcto del supermercado seleccionado de la lista
        do {
            supermarketSelect = supermarketService.search(sc.nextLine());
            if (supermarketSelect == null) {
                System.out.println("Ingrese correctamente el nombre del supermercado...");
            }
            attempts--;
        } while (attempts >= 0 && supermarketSelect == null);
        //endregion

        if (supermarketSelect != null) {
            do {
                System.out.println("======================= BUSQUEDA DE PRODUCTOS POR SUPERMERCADO ============================");
                System.out.println("\n");
                System.out.println("            [1] POR NOMBRE DE PRODUCTO");
                System.out.println("            [2] POR PRODUCTOS EN OFERTAS");
                System.out.println("            [3] POR CATEGORIA");
                System.out.println("            [4] TU LISTA DE COMPRAS");
                System.out.println("            [5] MODIFICAR TU LISTA DE COMPRAS");
                System.out.println("            [0] SALIR\n");

                do {
                    retry = false;
                    try {
                        System.out.print(">");
                        opc = Integer.parseInt(sc.nextLine());

                        switch (opc) {
                            case 1:
                                //region Busqueda de productos por nombre en un supermercado seleccionado
                                System.out.println("Ingrese el nombre del producto buscado: ");
                                productsForSale = supermarketService.serchProductByNameInSupermarket(supermarketSelect, sc.nextLine());
                                if (!productsForSale.isEmpty()) {
                                    cartService.addCartFromListProductForSale(productsForSale);
                                } else {
                                    System.out.println("El supermercado aun no tiene productos con esa descripcion");
                                }
                                //endregion
                                break;
                            case 2:
                                //region Busqueda de productos en oferta en un supermercado seleccionado
                                productsForSale = supermarketService.serchProductInSaleInSupermarket(supermarketSelect);
                                if (!productsForSale.isEmpty()) {
                                    cartService.addCartFromListProductForSale(productsForSale);
                                } else {
                                    System.out.println("El supermercado aun no tiene productos en oferta");
                                }
                                //endregion
                                break;
                            case 3:
                                //region Busqueda de productos por categoria en un supermercado seleccionado
                                Integer categorySelect;
                                do {
                                    ProductServiceImpl.showCategories();
                                    categorySelect = sc.nextInt();
                                    switch (categorySelect) {
                                        case 0:
                                            List<ProductForSale> listDairy = supermarketService.serchByCategoryInSupermarket(supermarketSelect, Category.DAIRY);
                                            if (!listDairy.isEmpty()) {
                                                cartService.addCartFromListProductForSale(listDairy);
                                            } else {
                                                System.out.println("No existe la categoria lacteos en los supermercados");
                                            }
                                            break;
                                        case 1:
                                            List<ProductForSale> listBakery = supermarketService.serchByCategoryInSupermarket(supermarketSelect, Category.BAKERY);
                                            if (!listBakery.isEmpty()) {
                                                cartService.addCartFromListProductForSale(listBakery);
                                            } else {
                                                System.out.println("No existe la categoria panaderia en los supermercados");
                                            }
                                            break;
                                        case 2:
                                            List<ProductForSale> listGrocery = supermarketService.serchByCategoryInSupermarket(supermarketSelect, Category.GROCERY);
                                            if (!listGrocery.isEmpty()) {
                                                cartService.addCartFromListProductForSale(listGrocery);
                                            } else {
                                                System.out.println("No existe la categoria alimentos en los supermercados");
                                            }
                                            break;
                                        case 3:
                                            List<ProductForSale> listAlcohol = supermarketService.serchByCategoryInSupermarket(supermarketSelect, Category.ALCOHOL);
                                            if (!listAlcohol.isEmpty()) {
                                                cartService.addCartFromListProductForSale(listAlcohol);
                                            } else {
                                                System.out.println("No existe la categoria bebidas alcoholicas en los supermercados");
                                            }
                                            break;
                                        default:
                                            System.out.println("Ingrese un numero de categoria correcto");
                                            break;
                                    }
                                } while (categorySelect != 0 && categorySelect != 1 && categorySelect != 2 && categorySelect != 3);
                                //endregion
                                break;
                            case 4:
                                //region Mostrar el carrito
                                cartService.totalPriceOfCart();
                                cartService.showCartsProductList();
                                //endregion
                                break;
                            case 5:
                                //region Modificar carrito
                                do {
                                    System.out.println("[1] Desea eliminar un producto");
                                    System.out.println("[2] Desea modificar cantidad de un  producto");
                                    System.out.println("[0] Salir");
                                    opc = sc.nextInt();

                                    switch (opc) {
                                        case 1:
                                            cartService.deleteSomeProductOfCart();
                                            break;
                                        case 2:
                                            cartService.modifyCartList();
                                            break;
                                        case 0:
                                            break;
                                        default:
                                            System.out.println("Ingrese una opcion correcta");
                                            break;
                                    }
                                } while (opc != 0);
                                //endregion
                                break;
                            case 0:
                                System.out.println("\n\n\n\n\n");
                                break;
                            default:
                                System.out.println("Ingrese una opcion correcta!");
                                break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Opción no disponible");
                        retry = true;
                    }
                } while (retry);
            } while (opc != 0);
        } else {
            System.out.println("Se ha ingresado un nombre incorrecto! Vuelva a intentarlo.");
        }
    }//LISTO SOLO FALTA PROBAR

    @Override
    public void privateAccessMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        Integer opc = null;
        Boolean retry;

        do {
            System.out.println("======================= ADMINISTRACIÓN ============================");
            System.out.println("\n");
            System.out.println("            [1] PRODUCTOS");
            System.out.println("            [2] SUPERMERCADOS");
            System.out.println("            [0] SALIR\n");

            do {
                retry = false;
                try {
                    System.out.print(">");
                    opc = Integer.parseInt(sc.nextLine());

                    switch (opc) {
                        case 0:
                            System.out.println("Volviendo...");
                            System.out.println("\n\n\n\n\n");
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
                } catch (NumberFormatException e) {
                    System.out.println("Opción no disponible");
                    retry = true;
                }
            } while (retry);
        } while (opc != 0);
    }//LISTO

    @Override
    public void privateAccessProductMenu() {
        Scanner sc = new Scanner(System.in);
        Integer opc = null;
        Boolean retry;

        do {
            System.out.println("======================= ABML PRODUCTO ============================");
            System.out.println("            [1] CREAR PRODUCTO");
            System.out.println("            [2] MODIFICAR PRODUCTO");
            System.out.println("            [3] ELIMINAR PRODUCTO");
            System.out.println("            [4] VER PRODUCTOS");
            System.out.println("            [0] SALIR\n");

            do {
                retry = false;
                try {
                    System.out.print(">");
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
                } catch (NumberFormatException e) {
                    System.out.println("Opción no disponible");
                    retry = true;
                }
            } while (retry);
        } while (opc != 0);
    }//FALTA VALIDACIONES...

    @Override
    public void privateAccessCreateProductMenu() {
        Scanner sc = new Scanner(System.in);
        Integer opc = null;
        Boolean retry;

        do {
            System.out.println("======================= MENU CREAR PRODUCTO ============================");
            System.out.println("            [1] CREAR ALIMENTO");
            System.out.println("            [2] CREAR BEBIDA");
            System.out.println("            [0] SALIR\n");

            do {
                retry = false;
                try {
                    System.out.print(">");
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
                        case 2:
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
                } catch (NumberFormatException e) {
                    System.out.println("Opción no disponible");
                    retry = true;
                }
            } while (retry);
        } while (opc != 0);
    }

    public void privateAccessModifyProductMenu() {
        Scanner sc = new Scanner(System.in);
        Integer opc = null;
        Boolean retry;

        do {
            System.out.println("======================= MENU MODIFICAR PRODUCTO ============================");
            System.out.println("            [1] MODIFICAR ALIMENTO");
            System.out.println("            [2] MODIFICAR BEBIDA");
            System.out.println("            [0] SALIR\n");

            do {
                retry = false;
                try {
                    System.out.print(">");
                    opc = Integer.parseInt(sc.nextLine());

                    switch (opc) {
                        case 0:
                            System.out.println("Volviendo...");
                            break;
                        case 1:
                            try {
                                Food modified;
                                if ((modified = foodService.modify(ProductServiceImpl.askForID())) != null) { //Si encuentro el producto buscado y logro modificarlo
                                    System.out.println("Información modificada");
                                    supermarketService.updateProductData(modified); //Actualizo los datos del producto en los supermercados que lo contengan
                                } else {
                                    System.out.println("ID no encontrado");
                                }
                            } catch (IOException e) {
                                System.out.println("Hubo un error al intentar guardar los cambios");
                            }

                            break;
                        case 2:
                            try {
                                Beverage modified;
                                if ((modified = beverageService.modify(ProductServiceImpl.askForID())) != null) { //Si encuentro el producto buscado y logro modificarlo
                                    System.out.println("Información modificada");
                                    supermarketService.updateProductData(modified); //Actualizo los datos del producto en los supermercados que lo contengan
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
                } catch (NumberFormatException e) {
                    System.out.println("Opción no disponible");
                    retry = true;
                }
            } while (retry);
        } while (opc != 0);
    }

    public void privateAccessDeleteProductMenu() { //AGREGAR BÚSQUEDA DE PRODUCTO EN SUPERMERCADOS
        Scanner sc = new Scanner(System.in);
        Integer opc = null;
        Boolean retry;
        Integer id;

        do {
            System.out.println("======================= MENU ELIMINAR PRODUCTO ============================");
            System.out.println("            [1] ELIMINAR ALIMENTO");
            System.out.println("            [2] ELIMINAR BEBIDA");
            System.out.println("            [0] SALIR\n");

            do {
                retry = false;
                try {
                    System.out.print(">");
                    opc = Integer.parseInt(sc.nextLine());

                    switch (opc) {
                        case 0:
                            System.out.println("Volviendo...");
                            break;
                        case 1:
                            try {
                                System.out.println("ID:");
                                id = Integer.parseInt(sc.nextLine());
                                if (foodService.delete(id) != null) {
                                    System.out.println("Producto eliminado");
                                } else {
                                    System.out.println("El producto no pudo ser eliminado");
                                    System.out.println("Si se trata de un error, corrobore que\nexista y que no esté en ningún supermercado");
                                }
                            } catch (IOException e) {
                                System.out.println("Error");
                            } catch (NumberFormatException e) {
                                System.out.println("Ingreso de datos erroneo");
                            }
                            break;
                        case 2:
                            try {
                                System.out.println("ID:");
                                id = Integer.parseInt(sc.nextLine());
                                if (beverageService.delete(id) != null) {
                                    System.out.println("Producto eliminado");
                                } else {
                                    System.out.println("El producto no pudo ser eliminado");
                                    System.out.println("Si se trata de un error, corrobore que\nexista y que no esté en ningún supermercado");
                                }
                            } catch (IOException e) {
                                System.out.println("Error");
                            } catch (NumberFormatException e) {
                                System.out.println("Ingreso de datos erroneo");
                            }
                            break;
                        default:
                            System.out.println("Opción no disponible");
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Opción no disponible");
                    retry = true;
                }
            } while (retry);
        } while (opc != 0);
    }

    @Override
    public void privateAccessSupermarketMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        Integer opc = null;
        Boolean retry;
        Supermarket supermarketExists;
        ProductForSale product;


        do {
            System.out.println("======================= ABML SUPERMERCADO ============================");
            System.out.println("            [1] CREAR SUPERMERCADO");
            System.out.println("            [2] MODIFICAR SUPERMERCADO");
            System.out.println("            [3] ELIMINAR SUPERMERCADO");
            System.out.println("            [4] LISTADO DE SUPERMERCADOS");
            System.out.println("            [5] AÑADIR PRODUCTO");
            System.out.println("            [6] MODIFICAR PRECIO");
            System.out.println("            [7] ELIMINAR PRODUCTO");
            System.out.println("            [8] APLICAR OFERTA");
            System.out.println("            [9] SACAR OFERTA");
            System.out.println("            [0] SALIR\n");

            do {
                retry = false;
                try {
                    System.out.print(">");
                    opc = Integer.parseInt(sc.nextLine());

                    switch (opc) {
                        case 0:
                            System.out.println("Volviendo...");
                            System.out.println("\n\n\n\n\n");
                            break;
                        case 1:
                            //region Crear un nuevo supermercado
                            supermarketService.addSupermarket();
                            //endregion
                            break;
                        case 2:
                            //region Modificar un supermercado ya existente
                            System.out.println("Ingrese el nombre del supermercado a modificar: ");
                            String name = sc.nextLine();
                            if (supermarketService.search(name) == null) {
                                System.out.println("el supermercado que desea modificar no existe en la base de datos");
                            } else {
                                supermarketService.modifySupermarket(name);
                            }
                            //endregion
                            break;
                        case 3:
                            //region Eliminar un supermercado existente
                            System.out.println("Ingrese el nombre del supermercado a eliminar: ");
                            String superName = sc.nextLine();
                            if (supermarketService.search(superName) == null) {
                                System.out.println("el supermercado que desea modificar no existe en la base de datos");
                            } else {
                                supermarketService.deleteSupermarket(supermarketService.search(superName));
                            }
                            //endregion
                            break;
                        case 4:
                            //region Listado de supermercados existentes
                            supermarketService.supermarketList();
                            //endregion
                            break;
                        case 5:
                            //region Agregar producto vendible a un supermercado existente
                            System.out.println("Ingrese el nombre del supermercado: ");
                            name = sc.nextLine();
                            if (supermarketService.search(name) == null) {
                                System.out.println("el supermercado que desea no existe en la base de datos");
                            } else {
                                productForSaleService.addProductForSale(supermarketService.search(name));
                            }
                            //endregion
                            break;
                        case 6:
                            //region Modificar Precio del Producto Vendible de un supermercado existente
                            System.out.println("Ingrese el nombre del supermercado: ");
                            name = sc.nextLine();
                            supermarketExists = supermarketService.search(name);
                            if (supermarketExists == null) { //No encuentro el supermercado
                                System.out.println("Este supermercado no existe en la base de datos");
                            } else { //Encuentro el supermercado
                                System.out.println("Ingrese el id del producto que desea modificar...");
                                product = productForSaleService.searchProductoForSaleById(supermarketExists, sc.nextInt());
                                if (product == null) { //No encuentro el producto en el supermercado
                                    System.out.println("No existe el Id ingresado dentro del listado del supermercado " + supermarketExists);
                                } else {
                                    System.out.println("Ingrese el precio nuevo : ");
                                    product.setPrice(sc.nextDouble());
                                    supermarketService.modifySupermarketListProducts(supermarketExists);
                                }
                            }
                            //endregion de un superme
                            break;
                        case 7:
                            //region Eliminar producto vendible de un supermercado existente
                            System.out.println("Ingrese el nombre del supermercado: ");
                            name = sc.nextLine();
                            supermarketExists = supermarketService.search(name);
                            if (supermarketExists == null) {
                                System.out.println("el supermercado que desea no existe en la base de datos");
                            } else {
                                System.out.println("Ingrese el id del producto que desea modificar...");
                                productForSaleService.removeProductForSaleForSupermarket(supermarketExists, sc.nextInt());
                            }
                            //endregion
                            break;
                        case 8:
                            //region Aplicar Oferta
                            System.out.println("Ingrese el nombre del supermercado: ");
                            name = sc.nextLine();
                            supermarketExists = supermarketService.search(name);
                            if (supermarketExists == null) {
                                System.out.println("el supermercado que desea no existe en la base de datos");
                            } else {
                                menuDiscountOn(supermarketExists);
                            }
                            //endregion
                            break;
                        case 9:
                            //region Sacar Oferta
                            System.out.println("Ingrese el nombre del supermercado: ");
                            name = sc.nextLine();
                            supermarketExists = supermarketService.search(name);
                            if (supermarketExists == null) {
                                System.out.println("el supermercado que desea no existe en la base de datos");
                            } else {
                                menuDiscountOff(supermarketExists);
                            }
                            //endregion
                            break;
                        default:
                            System.out.println("Opción no disponible");
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Opción no disponible");
                    retry = true;
                }
            } while (retry);
        } while (opc != 0);
    } //LISTO FALTA PROBAR

    public void menuDiscountOn(Supermarket s) throws IOException {
        Integer opc = null;
        Boolean retry;
        Category c;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("            APLICAR OFERTA: ");
            System.out.println("          [1] - Por Categoria");
            System.out.println("          [2] - Por producto puntual");
            System.out.println("          [3] - Por Marca");
            System.out.println("          [0] - Salir");
            System.out.println("          Ingrese una opcion:");

            do {
                retry = false;
                try {
                    System.out.print(">");
                    opc = Integer.parseInt(sc.nextLine());

                    switch (opc) {
                        case 1:
                            ProductServiceImpl.showCategories();
                            System.out.println("Ingrese la categoria deseada:");
                            c = ProductServiceImpl.selectCategory(sc.nextInt());
                            if (c != null) {
                                System.out.println("Ingrese el porcentaje de descuento deseado:");
                                Integer percent = sc.nextInt();
                                for (ProductForSale p : s.getProductList()) {
                                    if (p.getProduct().getCategory() == c) {
                                        p.setDiscountPercent(percent);
                                        p.setOnSale(true);
                                        System.out.println(p);
                                    }
                                }
                            }
                            break;
                        case 2:
                            System.out.println("Ingrese el ID del producto:");
                            Integer idSelect = sc.nextInt();
                            if (productForSaleService.validationId(idSelect)) {
                                System.out.println("Ingrese el porcentaje de descuento deseado:");
                                Integer percent = sc.nextInt();
                                for (ProductForSale p : s.getProductList()) {
                                    if (p.getProduct().getID().equals(idSelect)) {
                                        p.setDiscountPercent(percent);
                                        p.setOnSale(true);
                                        System.out.println(p);
                                    }
                                }
                            }
                            break;
                        case 3:
                            System.out.println("Ingrese la marca:");
                            String brandSelect = sc.nextLine();
                            Boolean flag = false;
                            System.out.println("Ingrese el porcentaje de descuento deseado:");
                            Integer percent = sc.nextInt();
                            for (ProductForSale p : s.getProductList()) {
                                if (p.getProduct().getBrand().equalsIgnoreCase(brandSelect)) {
                                    p.setDiscountPercent(percent);
                                    p.setOnSale(true);
                                    System.out.println(p);
                                    flag = true;
                                }
                            }
                            if (!flag) {
                                System.out.println("No existen productos con esa marca");
                            }
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Ingrese una opcion correcta");
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Opción no disponible");
                    retry = true;
                }
            } while (retry);
        } while (opc != 0);
    }

    public void menuDiscountOff(Supermarket s) throws IOException {
        Scanner sc = new Scanner(System.in);
        Category c;
        Integer opc = null;
        Boolean retry;
        do {
            System.out.println("            RETIRAR OFERTA: ");
            System.out.println("          [1] - Por Categoria");
            System.out.println("          [2] - Por producto puntual");
            System.out.println("          [3] - Por Marca");
            System.out.println("          [0] - Salir");
            System.out.println("          Ingrese una opcion:");

            do {
                retry = false;
                try {
                    System.out.print(">");
                    opc = Integer.parseInt(sc.nextLine());

                    switch (opc) {
                        case 1:
                            ProductServiceImpl.showCategories();
                            System.out.println("Ingrese la categoria deseada:");
                            c = ProductServiceImpl.selectCategory(sc.nextInt());
                            if (c != null) {
                                for (ProductForSale p : s.getProductList()) {
                                    if (p.getProduct().getCategory() == c) {
                                        p.setOnSale(false);
                                    }
                                }
                                System.out.println("Oferta eliminada");
                            }
                            break;
                        case 2:
                            System.out.println("Ingrese el ID del producto:");
                            Integer idSelect = sc.nextInt();
                            if (productForSaleService.validationId(idSelect)) {
                                for (ProductForSale p : s.getProductList()) {
                                    if (p.getProduct().getID().equals(idSelect)) {
                                        p.setOnSale(false);
                                    }
                                }
                                System.out.println("Oferta eliminada");
                            }
                            break;
                        case 3:
                            System.out.println("Ingrese la marca:");
                            String brandSelect = sc.nextLine();
                            for (ProductForSale p : s.getProductList()) {
                                if (p.getProduct().getBrand().equalsIgnoreCase(brandSelect)) {
                                    p.setOnSale(false);
                                }
                            }
                            System.out.println("Oferta eliminada");
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Ingrese una opcion correcta!");
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Opción no disponible");
                    retry = true;
                }
            } while (retry);
        } while (opc != 0);
    }
}