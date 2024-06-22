package Service.impl;

import Enums.Category;
import Models.Product;
import Models.ProductForSale;
import Models.Supermarket;
import Service.CartService;
import Service.ProductForSaleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class ProductForSaleServiceImpl implements ProductForSaleService {
    private final File file = new File("product.json");

    private SupermarketServiceImpl supermarket;
    private CartService cartService;
    private HashMap<String, Supermarket> superMarketList = supermarket.supermarketsListJson();

    public ProductForSaleServiceImpl() throws IOException {
    }

    public void addProductForSale(Supermarket s) throws IOException {
        Integer opcId;
        Scanner sc = new Scanner(System.in);

        ObjectMapper mapper = new ObjectMapper();
        Map<Integer, Product> mapProducts = new HashMap<>();

        TypeFactory typeFactory = mapper.getTypeFactory();
        MapType mapType = typeFactory.constructMapType(HashMap.class, Integer.class, Product.class);

        mapProducts = mapper.readValue(file, mapType);

        for (Map.Entry<Integer, Product> entry : mapProducts.entrySet()) {
            System.out.println(entry);
        }
        System.out.println(" ELija el id del producto que desea agregar: ");
        do {
            opcId = sc.nextInt();
            if (!validationId(opcId, mapProducts)) {
                System.out.println(" Ingrese por favor un Id correcto!!! ");
            }
        } while (!validationId(opcId, mapProducts));

        System.out.println(" Ingrese el precio del producto nuevo : ");
        Double newPrice = sc.nextDouble();

        ProductForSale newProductForSale = new ProductForSale(mapProducts.get(opcId), newPrice, false);
        s.getProductListHashSet().add(newProductForSale);

        supermarket.modifySupermarketListProducts(s);
    }

    public Boolean validationId(Integer id, Map<Integer, Product> map) {
        Boolean flag = false;
        for (Map.Entry<Integer, Product> entry : map.entrySet()) {
            if (entry.getKey().equals(id)) {
                flag = true;
            }
        }
        return flag;
    }

    public void RemoveProductForSaleForSupermarket(Supermarket sp, Integer id) throws IOException {

        Scanner st = new Scanner(System.in);

        for (ProductForSale p : superMarketList.get(sp.getCuit()).getProductListHashSet()) {
            if (p.getProduct().getID() == id) {
                System.out.println(" Producto a eliminar: " + p);
                System.out.println(" Es el producto a eliminar? s/n");
                if (st.nextLine().equalsIgnoreCase("s")) {
                    superMarketList.get(sp.getCuit()).getProductListHashSet().remove(p);
                    supermarket.saveSupermarketInJsonFile(superMarketList);
                } else {
                    System.out.println(" volver a menu opciones ");
                }
            }

        }
    }


    public void ModifyProduct(ProductForSale productForSale) {

        Integer opc;

        Scanner st = new Scanner(System.in);

        do {
            System.out.println(" Producto a modificar: " + productForSale);
            System.out.println("ELIGE CAMPO A MODIFICAR");
            System.out.println("1 - Nombre de producto: ");
            System.out.println("2 - Marca: ");
            System.out.println("3 - Categoria: ");
            System.out.println("4 - Precio: ");
            System.out.println("5 - Salir ");
            opc = st.nextInt();
            switch (opc) {
                case 1:
                    System.out.println("ingrese nuevo nombre del producto: ");
                    productForSale.getProduct().setProductName(st.nextLine());

                    break;
                case 2:
                    System.out.println("ingrese nueva marca: ");
                    productForSale.getProduct().setBrand(st.nextLine());

                    break;
                case 3:
                    Category category = null;
                    do {
                        System.out.println(" Categoria actual: " + productForSale.getProduct().getCategory());
                        System.out.println(" Ingrese nueva categoria: ");
                        System.out.println(" Elija alguna de estas categorias: ");
                        System.out.println(java.util.Arrays.asList(Category.values()));
                        category = ProductServiceImpl.selectCategory(st.nextInt());
                        if (category != null) {
                            productForSale.getProduct().setCategory(category);
                        } else {
                            System.out.println(" Ingrese numero de categoria correcto ");
                        }
                    } while (category == null);
                    break;
                case 4:
                    System.out.println("ingrese nuevo precio: ");
                    productForSale.setPrice(st.nextDouble());

                    break;

                default:
                    System.out.println(" ingrese una opcion valida ");
                    break;
            }

        } while (opc != 5);
    }

    public void ModifyProductForSaleInSupermarket(Supermarket sp, Integer id) {
        boolean isProduct = false;
        Scanner sc = new Scanner(System.in);
        try {

            for (ProductForSale p : superMarketList.get(sp.getCuit()).getProductListHashSet()) {
                if (p.getProduct().getID().equals(id)) {
                    this.ModifyProduct(p);
                    isProduct = true;
                    System.out.println(" Producto modificado: " + p);
                }
            }
            if (!isProduct) {
                throw new IllegalArgumentException(" No existe Id numero: " + id);
            } else {
                System.out.println(" Desea guardar los cambios? s/n ");
                if (sc.nextLine().equalsIgnoreCase("s")) {
                    supermarket.saveSupermarketInJsonFile(superMarketList);
                } else {
                    System.out.println(" vuelva al menu de opciones ");
                }
            }
        } catch (IOException e) {
            e.getMessage();
            System.out.println("no existe archivo de Supermercados ");
        }
    }

    public void addCartFromListProductForSale(List<ProductForSale> list) throws IOException {
        Integer numProduct;
        Scanner sc = new Scanner(System.in);
        String continueAdd = "S";
        do {
            System.out.println(">>>>>>>>>>>>>>>>>> LISTA DE PRODUCTOS <<<<<<<<<<<<<<<<<<<<<<");
            for (int i = 0; i < list.size(); i++) {
                System.out.println("[" + (i + 1) + "] - " + list.get(i));
            }
            System.out.println("Ingrese los productos que desea agregar a la lista por numero de opcion...");
            numProduct = sc.nextInt();
            if (numProduct > 0 && numProduct < list.size()) {
                //si ingresa una opcion correcta del listado procedemos a pedir la cantidad de ese producto para añadir al carrito
                System.out.println("Ingrese la cantidad que desea del producto " + list.get(numProduct - 1).getProduct().getProductName());
                cartService.addProductForSale(list.get(numProduct - 1), sc.nextInt());
                System.out.println("Producto agregado al carrito. Desea seguir agregando productos de este listado? s/n");
                continueAdd = sc.nextLine();
            } else {
                System.out.println("Ha seleccionado una opcion incorrecta de producto!. Intenelo nuevamente.");
            }
        } while (numProduct <= 0 || numProduct >= list.size() || continueAdd.equalsIgnoreCase("s"));
    }

}
