package Service.impl;

import Enums.Category;
import Exceptions.ExceptionIncorrectPassword;
import Models.ProductForSale;
import Models.Supermarket;
import Service.CartService;
import Service.MenusService;
import Service.ProductForSaleService;
import Service.SupermarketService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MenusServiceImpl implements MenusService {
    private static final String privateAccesKey = "1234";
    protected FoodServiceImpl foodService;
    protected BeverageServiceImpl beverageService;
    protected SupermarketService supermarketService;
    protected CartService cartService;
    protected ProductForSaleService productForSaleService;

    public MenusServiceImpl() throws IOException {
        this.foodService = new FoodServiceImpl();
        this.beverageService = new BeverageServiceImpl();
        this.supermarketService = new SupermarketServiceImpl();
        this.cartService = new CartServiceImpl();
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
                        } while (attempts > 0 && ok == false);
                        if(!ok){
                            throw new ExceptionIncorrectPassword("Todas las contraseñas ingresadas son incorrectas. Llame al 111");
                        }else{
                            privateAccessMenu();
                        }
                    }catch(ExceptionIncorrectPassword e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Ingrese una opcion correcta!");
                    break;
            }

        } while (opc != 0);
    }//LISTO CON CONTRASEÑA VALIDADA/TRES INTENTOS
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
                    shoppingListMenuBySupermarket();
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
    }//LISTO
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
            System.out.println("            [5] MODIFICAR TU LISTA DE COMPRAS");
            System.out.println("            [0] SALIR\n");

            opc = sc.nextInt();

            switch (opc) {
                case 1:
                    //region Busqueda por nombre de producto en TODOS los supermercados
                    System.out.println("Ingrese el nombre del producto que esta buscando");
                    String nameProduct = sc.nextLine();
                    List<ProductForSale> productsList = supermarketService.searchSpecialProductsByName(nameProduct);
                    if (!productsList.isEmpty()) {
                        productForSaleService.addCartFromListProductForSale(productsList);
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
                        productForSaleService.addCartFromListProductForSale(productsInSale);
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
                                    productForSaleService.addCartFromListProductForSale(listDairy);
                                } else {
                                    System.out.println("No existe la categoria lacteos en los supermercados");
                                }
                                break;
                            case 1:
                                List<ProductForSale> listBakery = supermarketService.searchProductsByCategory(Category.BAKERY);
                                if (!listBakery.isEmpty()) {
                                    productForSaleService.addCartFromListProductForSale(listBakery);
                                } else {
                                    System.out.println("No existe la categoria panaderia en los supermercados");
                                }
                                break;
                            case 2:
                                List<ProductForSale> listGrocery = supermarketService.searchProductsByCategory(Category.GROCERY);
                                if (!listGrocery.isEmpty()) {
                                    productForSaleService.addCartFromListProductForSale(listGrocery);
                                } else {
                                    System.out.println("No existe la categoria alimentos en los supermercados");
                                }
                                break;
                            case 3:
                                List<ProductForSale> listAlcohol = supermarketService.searchProductsByCategory(Category.ALCOHOL);
                                if (!listAlcohol.isEmpty()) {
                                    productForSaleService.addCartFromListProductForSale(listAlcohol);
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
                    //region Mostrar carrito y grabar
                    cartService.showCartsProductList();
                    System.out.println("Desea confirmar el carrito?. s/n");
                    if (sc.nextLine().equalsIgnoreCase("s")) {
                        cartService.saveCartList();
                    } else {
                        System.out.println("Continue agregando productos.");
                    }
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
                    break;
                default:
                    System.out.println("Ingrese una opcion correcta!");
                    break;
            }
        } while (opc != 0);
    }//LISTO SOLO FALTA PROBAR
    @Override
    public void shoppingListMenuBySupermarket() throws IOException {
        Scanner sc = new Scanner(System.in);
        Integer opc;
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

                opc = sc.nextInt();

                switch (opc) {
                    case 1:
                        //region Busqueda de productos por nombre en un supermercado seleccionado
                        System.out.println("Ingrese el nombre del producto buscado: ");
                        productsForSale = supermarketService.serchProductByNameInSupermarket(supermarketSelect, sc.nextLine());
                        if (!productsForSale.isEmpty()) {
                            productForSaleService.addCartFromListProductForSale(productsForSale);
                        } else {
                            System.out.println("El supermercado aun no tiene productos con esa descripcion");
                        }
                        //endregion
                        break;
                    case 2:
                        //region Busqueda de productos en oferta en un supermercado seleccionado
                        productsForSale = supermarketService.serchProductInSaleInSupermarket(supermarketSelect);
                        if (!productsForSale.isEmpty()) {
                            productForSaleService.addCartFromListProductForSale(productsForSale);
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
                                        productForSaleService.addCartFromListProductForSale(listDairy);
                                    } else {
                                        System.out.println("No existe la categoria lacteos en los supermercados");
                                    }
                                    break;
                                case 1:
                                    List<ProductForSale> listBakery = supermarketService.serchByCategoryInSupermarket(supermarketSelect, Category.BAKERY);
                                    if (!listBakery.isEmpty()) {
                                        productForSaleService.addCartFromListProductForSale(listBakery);
                                    } else {
                                        System.out.println("No existe la categoria panaderia en los supermercados");
                                    }
                                    break;
                                case 2:
                                    List<ProductForSale> listGrocery = supermarketService.serchByCategoryInSupermarket(supermarketSelect, Category.GROCERY);
                                    if (!listGrocery.isEmpty()) {
                                        productForSaleService.addCartFromListProductForSale(listGrocery);
                                    } else {
                                        System.out.println("No existe la categoria alimentos en los supermercados");
                                    }
                                    break;
                                case 3:
                                    List<ProductForSale> listAlcohol = supermarketService.serchByCategoryInSupermarket(supermarketSelect, Category.ALCOHOL);
                                    if (!listAlcohol.isEmpty()) {
                                        productForSaleService.addCartFromListProductForSale(listAlcohol);
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
                        //region Mostrar el carrito y grabar
                        cartService.showCartsProductList();
                        System.out.println("Desea confirmar el carrito?. s/n");
                        if (sc.nextLine().equalsIgnoreCase("s")) {
                            cartService.saveCartList();
                        } else {
                            System.out.println("Continue agregando productos.");
                        }
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
                        break;
                    default:
                        System.out.println("Ingrese una opcion correcta!");
                        break;
                }
            } while (opc != 0);
        } else {
            System.out.println("Se ha ingresado un nombre incorrecto! Vuelva a intentarlo.");
        }
    }//LISTO SOLO FALTA PROBAR
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
    }//LISTO
    @Override
    public void privateAccessProductMenu() {
        Scanner sc = new Scanner(System.in);
        Integer opc;
        System.out.println("======================= ABML PRODUCTO ============================");
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
    }//FALTA VALIDACIONES...
    @Override
    public void privateAccessCreateProductMenu() {
        Scanner sc = new Scanner(System.in);
        Integer opc;
        System.out.println("======================= MENU CREAR PRODUCTO============================");
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
    }//LISTO
    @Override
    public void privateAccessSupermarketMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        Integer opc;
        Supermarket supermarketExist;
        ProductForSale product;

        System.out.println("======================= ABML SUPERMERCADO ============================");
        do {
            System.out.println("            [1] CREAR SUPERMERCADO");
            System.out.println("            [2] MODIFICAR SUPERMERCADO");
            System.out.println("            [3] ELIMINAR SUPERMERCADO");
            System.out.println("            [4] LISTADO DE SUPERMERCADOS");
            System.out.println("            [5] AÑADIR PRODUCTO");
            System.out.println("            [6] MODIFICAR PRECIO");
            System.out.println("            [7] ELIMINAR PRODUCTO");
            System.out.println("            [0] SALIR\n");

            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 0:
                    System.out.println("Volviendo...");
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
                    supermarketExist = supermarketService.search(name);
                    if (supermarketExist == null) {
                        System.out.println("el supermercado que desea no existe en la base de datos");
                    } else {
                        System.out.println("Ingrese el id del producto que desea modificar...");
                        product = productForSaleService.searchProductoForSaleById(supermarketExist,sc.nextInt());
                        if(product == null){
                            System.out.println("No existe el Id ingresado dentro del listado del supermercado "+ supermarketExist);
                        }else{
                            System.out.println("Ingrese el precio nuevo : ");
                            product.setPrice(sc.nextDouble());
                            supermarketService.modifySupermarketListProducts(supermarketExist);
                        }
                    }
                    //endregion de un superme
                    break;
                case 7:
                    //region Eliminar producto vendible de un supermercado existente
                    System.out.println("Ingrese el nombre del supermercado: ");
                    name = sc.nextLine();
                    supermarketExist = supermarketService.search(name);
                    if (supermarketExist == null) {
                        System.out.println("el supermercado que desea no existe en la base de datos");
                    } else {
                        System.out.println("Ingrese el id del producto que desea modificar...");
                        productForSaleService.removeProductForSaleForSupermarket(supermarketExist,sc.nextInt());
                    }
                   //endregion
                    break;
                default:
                    System.out.println("Opción no disponible");
                    break;
            }
        } while (opc != 0);
    } //LISTO FALTA PROBAR
}