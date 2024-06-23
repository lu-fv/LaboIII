package Service.impl;

import Models.Supermarket;
import Service.SupermarketService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

import Enums.Category;
import Models.ProductForSale;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import javax.swing.text.StyledEditorKit;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class SupermarketServiceImpl implements SupermarketService, Serializable {
    private final File supermarketFile = new File("supermarket.json");
    private HashMap<String, Supermarket> superMarketList = supermarketsListJson(); //cuit y supermercado //cuit y supermercado

    public SupermarketServiceImpl() throws IOException {
    }

    //region ABM-----------------------------------------------------------------

    @Override
    public void addSupermarket() throws IOException {
        Scanner sc = new Scanner(System.in);
        String name;
        boolean alfa;
        System.out.println("Ingrese nombre/denominacion: ");
        do {
            name = sc.nextLine();
            alfa = Pattern.matches("^[a-zA-Z]*$", name);
            if (!alfa) {
                System.out.println("Ingrese solo caracteres por favor");
            }
        } while (!alfa);

        if (search(name) == null) {
            System.out.println("Ingrese direccion: ");
            String address = sc.nextLine();
            System.out.println("Ingrese telefono: ");
            String phone;
            do {
                phone = sc.nextLine();
                alfa = Pattern.matches("^[a-zA-Z]*$", phone);
                if (alfa) {
                    System.out.println("Ingrese solo numeros por favor");
                }
                if (phone.length() < 10) {
                    System.out.println("Ingrese la cantidad de numeros correctos");
                }
            } while (alfa || phone.length() < 10);

            System.out.println("Ingrese clave de indentificacion tributaria: ");
            String cuit;
            String newCuit;
            do {
                cuit = sc.nextLine();
                newCuit = cuit.replaceAll("[-]", "");
                alfa = Pattern.matches("^[a-zA-Z]*$", newCuit);
                if (alfa) {
                    System.out.println("Ingrese solo numeros por favor");
                }
                if(newCuit.length() < 12 || newCuit.length() > 13){
                    System.out.println("Ingrese un cuit correcto");
                }
            } while (alfa || newCuit.length() < 12 || newCuit.length() > 13);
            Supermarket s = new Supermarket(name, address, phone, cuit);
            superMarketList.put(s.getCuit(), s);

            saveSupermarketInJsonFile(superMarketList);
            System.out.println("Nuevo Supermercado agregado con exito");
        } else {
            System.out.println("El supermercado que desea dar de alta ya existe en la base, con los siguientes datos:");
            System.out.println(search(name));
        }

    }

    @Override
    public void deleteSupermarket(Supermarket s) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Desea eliminar el supermerdado " + s.getName() + "? s/n");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            this.superMarketList.remove(s.getCuit());
            saveSupermarketInJsonFile(superMarketList);
            System.out.println("El supermercado " + s.getName() + " fue dado de baja exitosamente");
        } else {
            System.out.println("volviendo al menu anterior");
        }

    }

    @Override
    public void modifySupermarketListProducts(Supermarket s) throws IOException {

        for (Map.Entry<String, Supermarket> entry : superMarketList.entrySet()) {
            if (s.getName().equalsIgnoreCase(entry.getKey())) {
                entry.setValue(s);
            }
        }

        saveSupermarketInJsonFile(superMarketList);
    }

    @Override
    public void modifySupermarket(String name) throws IOException {

        Supermarket s = search(name);
        Integer opc;
        Scanner sr = new Scanner(System.in);
        Scanner st = new Scanner(System.in);
        boolean alfa;

        do {
            System.out.println("ELIGE CAMPO A MODIFICAR");
            System.out.println("1 - Nombre");
            System.out.println("2 - Domicilio");
            System.out.println("3 - Telefono");
            System.out.println("4 - CUIT");
            System.out.println("5 - Salir");
            opc = sr.nextInt();
            switch (opc) {
                case 1:
                    System.out.println("Ingrese nuevo nombre: ");
                    String supermarketName;
                    do {
                        supermarketName = st.nextLine();
                        alfa = Pattern.matches("^[a-zA-Z]*$", supermarketName);
                        if (!alfa) {
                            System.out.println("Ingrese solo caracteres por favor");
                        }
                    } while (!alfa);
                    s.setName(supermarketName);
                    break;
                case 2:
                    System.out.println("Ingrese nuevo domicilio: ");
                    s.setAddress(sr.nextLine());
                    break;
                case 3:
                    System.out.println("Ingrese nuevo telefono: ");
                    String phone;
                    do {
                        phone  = sr.nextLine();
                        alfa = Pattern.matches("^[a-zA-Z]*$", phone);
                        if (alfa) {
                            System.out.println("Ingrese solo numeros por favor");
                        }
                        if (phone.length() < 10) {
                            System.out.println("Ingrese la cantidad de numeros correctos");
                        }
                    } while (alfa || phone.length() < 10);
                    s.setPhone(phone);
                    break;
                case 4:
                    System.out.println("Ingrese nueva CUIT: ");
                    String cuit;
                    String newCuit;
                    do {
                        cuit = sr.nextLine();
                        newCuit = cuit.replaceAll("[-]", "");
                        alfa = Pattern.matches("^[a-zA-Z]*$", newCuit);
                        if (alfa) {
                            System.out.println("Ingrese solo numeros por favor");
                        }
                        if(newCuit.length() < 12 || newCuit.length() > 13){
                            System.out.println("Ingrese un cuit correcto");
                        }
                    } while (alfa || newCuit.length() < 12 || newCuit.length() > 13);
                    s.setCuit(cuit);
                case 5:
                    break;
                default:
                    System.out.println("ingrese una opcion valida");
                    break;
            }

        } while (opc != 5);

        saveSupermarketInJsonFile(superMarketList);
        System.out.println("datos guardados exitosamente");

    }

    @Override
    public void supermarketList() {

        for (Map.Entry<String, Supermarket> entry : this.superMarketList.entrySet()) {
            System.out.println("        SUPERMERCADO: ");
            System.out.println("Nombre:     " + entry.getValue().getName());
            System.out.println("Domicilio:  " + entry.getValue().getAddress());
            System.out.println("telefono:   " + entry.getValue().getPhone());
            System.out.println("CUIT:       " + entry.getValue().getCuit());
            System.out.println("        LISTADO DE PRODUCTOS DEL SUPERMERCADO " + entry.getValue().getName() + ":");
            for (ProductForSale p : entry.getValue().getProductListHashSet()) {
                System.out.println(p);
            }
        }

    }

    @Override
    public void saveSupermarketInJsonFile(HashMap<String, Supermarket> superList) throws IOException {
        if (!supermarketFile.exists()) {
            supermarketFile.createNewFile();
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(supermarketFile, superList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    ;

    @Override
    public HashMap<String, Supermarket> supermarketsListJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeFactory typeFactory = mapper.getTypeFactory();
        MapType mapType = typeFactory.constructMapType(HashMap.class, String.class, Supermarket.class);
        return mapper.readValue(supermarketFile, mapType);
    }
    //endregion

    //region BÚSQUEDA POR SUPERMERCADO-------------------------------------------
    @Override
    public Supermarket search(String name) {
        Supermarket supermarket = null;

        for (Map.Entry<String, Supermarket> entry : superMarketList.entrySet()) {
            if (entry.getValue().getName().equalsIgnoreCase(name)) {
                supermarket = entry.getValue();
            }
        }
        return supermarket;
    }

    @Override
    public void showListSupermarket(Supermarket supermarket) {
        if (supermarket != null) {
            System.out.println(supermarket);

        }
    }

    @Override
    public List<ProductForSale> serchByCategoryInSupermarket(Supermarket supermarket, Category category) {

        List<ProductForSale> productForCategory = new ArrayList<>();
        for (ProductForSale p : superMarketList.get(supermarket.getCuit()).getProductListHashSet()) {
            if (p.getProduct().getCategory() == (category)) {
                productForCategory.add(p);
            }
        }
        return productForCategory;
    }

    @Override
    public List<ProductForSale> serchProductInSaleInSupermarket(Supermarket supermarket) {

        List<ProductForSale> productsInSale = new ArrayList<>();
        for (ProductForSale p : superMarketList.get(supermarket.getCuit()).getProductListHashSet()) {
            if (p.getOnSale()) {
                productsInSale.add(p);
            }
        }
        return productsInSale;
    }

    @Override
    public List<ProductForSale> serchProductByNameInSupermarket(Supermarket supermarket, String name) {

        List<ProductForSale> products = new ArrayList<>();
        for (ProductForSale p : superMarketList.get(supermarket.getCuit()).getProductListHashSet()) {
            if (p.getProduct().getProductName().contains(name)) {
                products.add(p);
            }
        }
        return products;
    }
    //endregion

    //region BÚSQUEDA GENERAL----------------------------------------------------
    @Override
    public List<ProductForSale> searchSalesProducts() {
        //creo una lista nueva de productos para cargar los productos que coincidan con el filtro en oferta
        List<ProductForSale> listProduct = new ArrayList<>();

        //recorro la lista de supermercados
        for (Map.Entry<String, Supermarket> entry : superMarketList.entrySet()) {
            //si el supermercado tiene productos en su lista recorro (osea si no esta vacia)
            if (!entry.getValue().getProductListHashSet().isEmpty()) {
                //recorro el Set de productos de cada supermercado
                for (ProductForSale p : entry.getValue().getProductListHashSet()) {
                    if (p.getOnSale()) {
                        //agrego a la lista los productos que esten en oferta
                        listProduct.add(p);
                    }
                }
            }
        }
        return listProduct;
    }

    @Override
    public List<ProductForSale> searchSpecialProductsByName(String name) {

        //me aseguro que este todo en minuscula para comparar despues con los productos del json
        name = name.toLowerCase(Locale.ROOT);
        //creo una lista nueva de productos para cargar los productos que coincidan con el filtro en oferta
        List<ProductForSale> listProduct = new ArrayList<>();

        //recorro la lista de supermercados
        for (Map.Entry<String, Supermarket> entry : superMarketList.entrySet()) {
            //si el supermercado tiene productos en su lista recorro (osea si no esta vacia)
            if (!entry.getValue().getProductListHashSet().isEmpty()) {
                //recorro el Set de productos de cada supermercado
                for (ProductForSale p : entry.getValue().getProductListHashSet()) {
                    if (p.getProduct().getProductName().contains(name)) {
                        //agrego a la lista los productos que esten en oferta
                        listProduct.add(p);
                    }
                }
            }
        }
        return listProduct;
    }

    @Override
    public List<ProductForSale> searchProductsByCategory(Category c) {
        //creo una lista nueva de productos para cargar los productos que coincidan con el filtro en oferta
        List<ProductForSale> listProduct = new ArrayList<>();

        //recorro la lista de supermercados
        for (Map.Entry<String, Supermarket> entry : superMarketList.entrySet()) {
            //si el supermercado tiene productos en su lista recorro (osea si no esta vacia)
            if (!entry.getValue().getProductListHashSet().isEmpty()) {
                //recorro el Set de productos de cada supermercado
                for (ProductForSale p : entry.getValue().getProductListHashSet()) {
                    if (p.getProduct().getCategory() == (c)) {
                        //agrego a la lista los productos que esten en oferta
                        listProduct.add(p);
                    }
                }
            }
        }
        return listProduct;

    }

    @Override
    public Boolean searchSpecialProductsByNameExist(String name) {
        //me aseguro que este todo en minuscula para comparar despues con los productos del json
        name = name.toLowerCase(Locale.ROOT);

        //recorro la lista de supermercados
        for (Map.Entry<String, Supermarket> entry : superMarketList.entrySet()) {
            //si el supermercado tiene productos en su lista recorro (osea si no esta vacia)
            if (!entry.getValue().getProductListHashSet().isEmpty()) {
                //recorro el Set de productos de cada supermercado
                for (ProductForSale p : entry.getValue().getProductListHashSet()) {
                    //paso a minuscula el nombre del producto
                    String nameProductSupermarket = p.getProduct().getProductName().toLowerCase(Locale.ROOT);
                    if (nameProductSupermarket.contains(name)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
//endregion
}
