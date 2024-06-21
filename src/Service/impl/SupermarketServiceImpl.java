package Service.impl;

import Models.Product;
import Models.Supermarket;
import Service.SupermarketService;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

import Enums.Category;
import Models.ProductForSale;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.io.IOException;

public class SupermarketServiceImpl implements SupermarketService, Serializable {
    private final File supermarketFile = new File("supermarket.json");
    private HashMap<String, Supermarket> superMarketList = new HashMap<>(); //cuit y supermercado

    //region ABM-----------------------------------------------------------------

    @Override
    public void addSupermarket() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("ingrese nombre/denominacion: ");
        String name = sc.nextLine();
        System.out.println("Ingrese direccion: ");
        String address = sc.nextLine();
        System.out.println("Ingrese telefono: ");
        String phone = sc.nextLine();
        System.out.println("Ingrese clave de indentificacion tributaria: ");
        String cuit = sc.nextLine();

        Supermarket s = new Supermarket(name, address, phone, cuit);
        superMarketList.put(s.getCuit(), s);

        saveSupermarketInJsonFile(superMarketList);

    }

    @Override
    public void deleteSupermarket() throws IOException {
        Scanner sr = new Scanner(System.in);
        System.out.println("Ingrese nombre del supermercado a eliminar: ");
        String name = sr.nextLine();

        Supermarket s = search(name);
        this.superMarketList.remove(s);

        saveSupermarketInJsonFile(superMarketList);
    }

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

        do {
            System.out.println("ELIGE CAMPO A MODIFICAR");
            System.out.println("1 - domicilio");
            System.out.println("2 - telefono");
            System.out.println("3 - CUIT");
            System.out.println("4 - salir");
            opc = sr.nextInt();
            switch (opc) {
                case 1:
                    System.out.println("ingrese nuevo domicilio: ");
                    s.setAddress(st.nextLine());
                    break;
                case 2:
                    System.out.println("ingrese nuevo telefono: ");
                    s.setPhone(st.nextLine());
                    break;
                case 3:
                    System.out.println("ingrese nueva CUIT: ");
                    s.setCuit(st.nextLine());
                    break;
                default:
                    System.out.println("ingrese una opcion valida");
                    break;
            }


        } while (opc != 4);

        saveSupermarketInJsonFile(superMarketList);


    }

    @Override
    public void supermarketList(Supermarket s) {
        System.out.println("SUPERMERCADO: ");
        System.out.println(s.getName());
        System.out.println("CUIT: ");
        System.out.println(s.getCuit());
        System.out.println("LISTA DE PRODUCTOS: ");
        for (ProductForSale p : s.getProductListHashSet()) {
            p.toString();
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
    };
    //endregion

    public HashMap<Integer, Supermarket> supermarketsListJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeFactory typeFactory = mapper.getTypeFactory();
        MapType mapType = typeFactory.constructMapType(HashMap.class, Integer.class, Supermarket.class);
        return mapper.readValue(supermarketFile, mapType);
    }

    //region BÚSQUEDA POR SUPERMERCADO-------------------------------------------
    @Override
    public Supermarket search(String name) {

        Supermarket supermarket = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            // List<Supermarket> supermarkets = mapper.readValue(supermarketFile, List.class); // levanto la lista de supermercado de Json
            List<Supermarket> supermarkets = mapper.readValue(supermarketFile, new TypeReference<List<Supermarket>>() {
            }); // levanto la lista por referencia de supermercado de Json

            for (Supermarket superM : supermarkets) {
                if (superM.getName().equalsIgnoreCase(name)) {
                    supermarket = superM;
                }

            }
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
    public Supermarket searchByCategory(Category category) {

        Supermarket supermarket = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            // List<Supermarket> supermarkets = mapper.readValue(supermarketFile, List.class); // levanto la lista de supermercado de Json
            List<Supermarket> supermarkets = mapper.readValue(supermarketFile, new TypeReference<List<Supermarket>>() {
            }); // levanto la lista por referencia de supermercado de Json
            ProductForSale product = new ProductForSale();
            for (Supermarket superM : supermarkets) {
                if (product.getProduct().getCategory().equals(category)) {
                    supermarket = superM;
                }

            }
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return supermarket;
    }

    //endregion

    //region BÚSQUEDA GENERAL----------------------------------------------------
    @Override
    public List<ProductForSale> searchSalesProducts() throws IOException {
        //creo una lista nueva de productos para cargar los productos que coincidan con el filtro en oferta
        List<ProductForSale> listProduct = new ArrayList<>();

        //leo la lista de supermercados del json
        ObjectMapper objectMapperSupermarket = new ObjectMapper();
        List<Supermarket> listSupermarket = objectMapperSupermarket.readValue(supermarketFile, objectMapperSupermarket.getTypeFactory().constructArrayType(ArrayList.class));

        //recorro la lista de supermercados
        for (Supermarket s : listSupermarket) {
            //si el supermercado tiene productos en su lista recorro (osea si no esta vacia)
            if (!s.getProductListHashSet().isEmpty()) {
                //recorro el Set de productos de cada supermercado
                for (ProductForSale p : s.getProductListHashSet()) {
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
    public List<ProductForSale> searchSpecialProductsByName(String name) throws IOException {

        //me aseguro que este todo en minuscula para comparar despues con los productos del json
        name = name.toLowerCase(Locale.ROOT);
        //creo una lista nueva de productos para cargar los productos que coincidan con el filtro en oferta
        List<ProductForSale> listProduct = new ArrayList<>();

        //leo la lista de supermercados del json
        ObjectMapper objectMapperSupermarket = new ObjectMapper();
        List<Supermarket> listSupermarket = objectMapperSupermarket.readValue(supermarketFile, objectMapperSupermarket.getTypeFactory().constructArrayType(ArrayList.class));

        //recorro la lista de supermercados
        for (Supermarket s : listSupermarket) {
            //si el supermercado tiene productos en su lista recorro (osea si no esta vacia)
            if (!s.getProductListHashSet().isEmpty()) {
                //recorro el Set de productos de cada supermercado
                for (ProductForSale p : s.getProductListHashSet()) {
                    //paso a minuscula el nombre del producto
                    String nameProductSupermarket = p.getProduct().getProductName().toLowerCase(Locale.ROOT);
                    if (nameProductSupermarket.contains(name)) {
                        //agrego a la lista los productos que coincide con la descripcion
                        listProduct.add(p);
                    }
                }
            }
        }
        return listProduct;
    }

    @Override
    public List<ProductForSale> searchProductsByCategory(Category c) throws IOException {
        //creo una lista nueva de productos para cargar los productos que coincidan con el filtro en oferta
        List<ProductForSale> listProduct = new ArrayList<>();

        //leo la lista de supermercados del json
        ObjectMapper objectMapperSupermarket = new ObjectMapper();
        List<Supermarket> listSupermarket = objectMapperSupermarket.readValue(supermarketFile, objectMapperSupermarket.getTypeFactory().constructArrayType(ArrayList.class));

        //recorro la lista de supermercados
        for (Supermarket s : listSupermarket) {
            //si el supermercado tiene productos en su lista recorro (osea si no esta vacia)
            if (!s.getProductListHashSet().isEmpty()) {
                //recorro el Set de productos de cada supermercado
                for (ProductForSale p : s.getProductListHashSet()) {
                    //paso a minuscula el nombre del producto
                    if (p.getProduct().getCategory() == c) {
                        //agrego a la lista los productos que coincide con la categoria
                        listProduct.add(p);
                    }
                }
            }
        }
        return listProduct;
    }
    //endregion

    public Boolean searchSpecialProductsByNameExist(String name) {
        //me aseguro que este todo en minuscula para comparar despues con los productos del json
        name = name.toLowerCase(Locale.ROOT);

        //leo la lista de supermercados del json
        ObjectMapper objectMapperSupermarket = new ObjectMapper();

        try {
            List<Supermarket> listSupermarket = objectMapperSupermarket.readValue(supermarketFile, objectMapperSupermarket.getTypeFactory().constructArrayType(ArrayList.class));
            //recorro la lista de supermercados
            for (Supermarket s : listSupermarket) {
                //si el supermercado tiene productos en su lista recorro (osea si no esta vacia)
                if (!s.getProductListHashSet().isEmpty()) {
                    //recorro el Set de productos de cada supermercado
                    for (ProductForSale p : s.getProductListHashSet()) {
                        //paso a minuscula el nombre del producto
                        String nameProductSupermarket = p.getProduct().getProductName().toLowerCase(Locale.ROOT);
                        if (nameProductSupermarket.contains(name)) {
                            return true;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.getMessage();
            System.out.println("no existe archivo de Supermercados");

        }
        return false;
    }

    public void RemoveProductForSupermarket(Integer id) {
        boolean isProduct = false;
        //leo la lista de supermercados del json
        ObjectMapper objectMapperSupermarket = new ObjectMapper();
        try {
            List<Supermarket> listSupermarket = objectMapperSupermarket.readValue(supermarketFile, objectMapperSupermarket.getTypeFactory().constructArrayType(ArrayList.class));

            //recorro la lista de supermercados
            for (Supermarket s : listSupermarket) {
                //si el supermercado tiene productos en su lista recorro (osea si no esta vacia)
                if (!s.getProductListHashSet().isEmpty()) {
                    //recorro el Set de productos de cada supermercado
                    for (ProductForSale p : s.getProductListHashSet()) {
                        if (p.getProduct().getID().equals(id)) {
                            s.getProductListHashSet().remove(p);
                            isProduct = true;
                        }
                    }
                }
            }
            if (isProduct == false) {
                throw new IllegalArgumentException(" No existe Id numero: " + id);
            } else {
                objectMapperSupermarket.writeValue(this.supermarketFile, listSupermarket);
            }
        } catch (IOException e) {
            e.getMessage();
            System.out.println("no existe archivo de Supermercados");
        }
    }

    private void ModifyProduct(ProductForSale productForSale) {

        Integer opc;
        Scanner sr = new Scanner(System.in);
        Scanner st = new Scanner(System.in);

        do {
            System.out.println("ELIGE CAMPO A MODIFICAR");
            System.out.println("1 - productName");
            System.out.println("2 - brand");
            System.out.println("3 - category");
            System.out.println("4 - price");
            System.out.println("5 - onSale");
            System.out.println("6 - salir");
            opc = sr.nextInt();
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
                        category = ProductServiceImpl.selectCategory(sr.nextInt());
                        if (category != null) {
                            productForSale.getProduct().setCategory(category);
                        } else {
                            System.out.println(" Ingrese numero de categoria correcto ");
                        }
                    } while (category == null);
                    break;
                case 4:
                    System.out.println("ingrese nuevo precio: ");
                    productForSale.setPrice(sr.nextDouble());

                    break;
                case 5:
                    String subOpc = "";
                    do {
                        System.out.println("En oferta? s/n ");
                        if ("s".equals(sr.hasNext())) {
                            productForSale.setOnSale(true);
                        } else if ("n".equals(sr.hasNext())) {
                            productForSale.setOnSale(false);
                        } else {
                            System.out.println(" Elegir opcion 's' o 'n' ");
                        }
                    } while (!subOpc.equals("s") || !subOpc.equals("n"));
                    break;
                default:
                    System.out.println("ingrese una opcion valida");
                    break;
            }

        } while (opc != 6);
    }

    public void ModifyProductInSupermarket(Integer id) {
        boolean isProduct = false;

        ObjectMapper objectMapperSupermarket = new ObjectMapper();
        try {
            List<Supermarket> listSupermarket = objectMapperSupermarket.readValue(supermarketFile, objectMapperSupermarket.getTypeFactory().constructArrayType(ArrayList.class));


            for (Supermarket s : listSupermarket) {

                if (!s.getProductListHashSet().isEmpty()) {

                    for (ProductForSale p : s.getProductListHashSet()) {
                        if (p.getProduct().getID().equals(id)) {
                            this.ModifyProduct(p);
                            isProduct = true;
                        }
                    }
                }
            }
            if (isProduct == false) {
                throw new IllegalArgumentException(" No existe Id numero: " + id);
            } else {
                objectMapperSupermarket.writeValue(this.supermarketFile, listSupermarket);
            }
        } catch (IOException e) {
            e.getMessage();
            System.out.println("no existe archivo de Supermercados");
        }
    }

}
