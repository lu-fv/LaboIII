package Service.impl;

import Enums.Category;
import Models.Product;
import Models.ProductForSale;
import Models.Supermarket;
import Service.CartService;
import Service.ProductForSaleService;
import Service.SupermarketService;
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
    private final File fileProduct = new File("product.json");

    private SupermarketService supermarket;
    private CartService cartService;
    private HashMap<String, Supermarket> superMarketList;
    private Map<Integer, Product> mapProducts;

    public ProductForSaleServiceImpl(SupermarketServiceImpl supermarket, CartServiceImpl cartService) throws IOException {
        this.supermarket = supermarket;
        this.cartService = cartService;
        this.superMarketList = supermarket.supermarketsListJson();
        this.mapProducts = productFromJsonToMap();
    }

    public ProductForSaleServiceImpl() throws IOException {
    }

    @Override
    public void addProductForSale(Supermarket s) throws IOException {
        Integer opcId;
        Scanner sc = new Scanner(System.in);

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
        Double newPrice;
        do {
            newPrice = sc.nextDouble();
            if(!isDouble(newPrice.toString())){
                System.out.println("Ingrese el precio correctamente como decimal");
            }
        } while (!isDouble(newPrice.toString()));

        ProductForSale newProductForSale = new ProductForSale(mapProducts.get(opcId), newPrice, false);
        s.getProductListHashSet().add(newProductForSale);

        supermarket.modifySupermarketListProducts(s);
    }

    @Override
    public Boolean validationId(Integer id, Map<Integer, Product> map) {
        Boolean flag = false;
        for (Map.Entry<Integer, Product> entry : map.entrySet()) {
            if (entry.getKey().equals(id)) {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void removeProductForSaleForSupermarket(Supermarket sp, Integer id) throws IOException {

        Scanner st = new Scanner(System.in);

        for (ProductForSale p : superMarketList.get(sp.getCuit()).getProductListHashSet()) {
            if (p.getProduct().getID() == id) {
                System.out.println(" PRODUCTO A ELIMINAR: " + p);
                System.out.println(" Es el producto que desea eliminar? s/n");
                if (st.nextLine().equalsIgnoreCase("s")) {
                    superMarketList.get(sp.getCuit()).getProductListHashSet().remove(p);
                    supermarket.saveSupermarketInJsonFile(superMarketList);
                } else {
                    System.out.println(" Volviendo al menu opciones ");
                }
            }

        }
    }

    @Override
    public void modifyProduct(ProductForSale productForSale) {

        Integer opc;

        Scanner st = new Scanner(System.in);

        do {
            System.out.println("       PRODUCTO A MODIFICAR: " + productForSale);
            System.out.println("            1 - Nombre de producto: ");
            System.out.println("            2 - Marca: ");
            System.out.println("            3 - Categoria: ");
            System.out.println("            4 - Precio: ");
            System.out.println("            5 - Salir ");
            System.out.println("     Seleccione la opcion deseada: ");
            opc = st.nextInt();
            switch (opc) {
                case 1:
                    System.out.println("Ingrese nuevo nombre del producto: ");
                    productForSale.getProduct().setProductName(st.nextLine());

                    break;
                case 2:
                    System.out.println("Ingrese nueva marca: ");
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
                    System.out.println("Ingrese nuevo precio: ");
                    Double newPrice;
                    do{
                        newPrice = st.nextDouble();
                        if(!isDouble(newPrice.toString())){
                            System.out.println("Ingrese el precio correctamente como decimal");
                        }
                    }while (!isDouble(newPrice.toString()));
                    productForSale.setPrice(newPrice);

                    break;

                default:
                    System.out.println(" Ingrese una opcion valida ");
                    break;
            }

        } while (opc != 5);
    }

    @Override
    public void modifyProductForSaleInSupermarket(Supermarket sp, Integer id) {
        boolean isProduct = false;
        Scanner sc = new Scanner(System.in);
        try {
            for (ProductForSale p : superMarketList.get(sp.getCuit()).getProductListHashSet()) {
                if (p.getProduct().getID().equals(id)) {
                    this.modifyProduct(p);
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
            System.out.println(e.getMessage());
            System.out.println("no existe archivo de Supermercados ");
        }
    }

    @Override
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

    @Override
    public ProductForSale searchProductoForSaleById(Supermarket s, Integer id) {
        for (ProductForSale p : superMarketList.get(s.getCuit()).getProductListHashSet()) {
            if (p.getProduct().getID() == id) {
                return p;
            }
        }
        return null;
    }

    private Map<Integer, Product> productFromJsonToMap() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        TypeFactory typeFactory = mapper.getTypeFactory();
        MapType mapType = typeFactory.constructMapType(HashMap.class, Integer.class, Product.class);

        return mapper.readValue(fileProduct, mapType);
    }

    @Override
    public boolean isDouble(String price) {
        try {
            Double.parseDouble(price);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

}
