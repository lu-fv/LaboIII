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
            //ingresa el nombre por teclado
            name = sc.nextLine();
            //valido que sean caracteres, retorna true si lo son
            alfa = Pattern.matches("^[a-zA-Z]*$", name);
            if (!alfa) {
                //si es false envio mensaje y vuelve a intentar hasta que sea correcto
                System.out.println("Ingrese solo caracteres por favor");
            }
        } while (!alfa);

        if (search(name) == null) {
            //busco que no exista un supermercado con ese nombre
            System.out.println("Ingrese direccion: ");
            String address = sc.nextLine();
            //ingresa direccion por teclado sin validar
            System.out.println("Ingrese telefono: ");
            String phone;//variable para ingresar el telefono
            String newPhone;//variable para validar telefono
            do {
                //Ingresa telefono por teclado
                phone = sc.nextLine();
                //convierto el telefono a string para validar y le quito los guiones por si lo ingresa como un 0800
                newPhone = phone.toString().replaceAll("[-]", "");

                alfa = Pattern.matches("^[a-zA-Z]*$", newPhone);
                //retorna true si ingresan letras
                if (alfa) {
                    System.out.println("Ingrese solo numeros por favor");
                }
                //valido que sea una cantidad minima de numeros
                if (newPhone.length() < 10) {
                    System.out.println("Ingrese la cantidad de numeros correctos");
                }
            } while (alfa || newPhone.length() < 10);

            System.out.println("Ingrese clave de indentificacion tributaria: ");
            String cuit;
            String newCuit;
            do {
                //Ingresa CUIT
                cuit = sc.nextLine();
                //Elimino los guiones del cuit para validar que sean numeros
                newCuit = cuit.replaceAll("[-]", "");
                //alfa es true si son caracteres
                alfa = Pattern.matches("^[a-zA-Z]*$", newCuit);
                //informo si ingresa letras que ingrese numeros
                if (alfa) {
                    System.out.println("Ingrese solo numeros por favor");
                }
                //informo si no tiene la longitud correcta de un CUIT
                if (newCuit.length() != 11) {
                    System.out.println("Ingrese un cuit correcto");
                }
            } while (alfa || newCuit.length() != 11);//el while continua hasta que no se ingrese correctamente

            //verifico que el cuit no se encuentre en el listado simplemente para poder informar con leyenda que ya se encuentra
            if(superMarketList.containsKey(cuit)){
                System.out.println("El CUIT ya se encuentra en el listado de supermercado");
            }else{
                //instanciamos el nuevo supermercado
                Supermarket s = new Supermarket(name, address, phone, cuit);
                //agregamos al map de lista de supermercados
                superMarketList.put(s.getCuit(), s);
                //y grabamos en el json informando que se grabo con exito
                saveSupermarketInJsonFile(superMarketList);
                System.out.println("Nuevo Supermercado agregado con exito");
            }

        } else {
            System.out.println("El supermercado que desea dar de alta ya existe en la base, con los siguientes datos:");
            System.out.println(search(name));
        }

    }

    @Override
    public void deleteSupermarket(Supermarket s) throws IOException {
        Scanner sc = new Scanner(System.in);

        //pregunto si desea eliminar el supermercado y muestro para verificar datos correctos
        System.out.println("Desea eliminar el supermerdado " + s.getName() + "[CUIT: " + s.getCuit() +"] ? s/n");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            //en caso de afirmacion procedo a eliminar
            this.superMarketList.remove(s.getCuit());
            //guardo la modificacion en el json
            saveSupermarketInJsonFile(superMarketList);
            //informo el movimiento
            System.out.println("El supermercado " + s.getName() + " fue dado de baja exitosamente!!!");
        } else {
            System.out.println("Volviendo al menu anterior");
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
                    String newPhone;
                    do {
                        phone = sr.nextLine();
                        newPhone = phone.replaceAll("[-]", "");
                        alfa = Pattern.matches("^[a-zA-Z]*$", newPhone);
                        if (alfa) {
                            System.out.println("Ingrese solo numeros por favor");
                        }
                        if (newPhone.length() < 10) {
                            System.out.println("Ingrese la cantidad de numeros correctos");
                        }
                    } while (alfa || newPhone.length() < 10);
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
                        if (newCuit.length() != 11) {
                            System.out.println("Ingrese un cuit correcto");
                        }
                    } while (alfa || newCuit.length() != 11);
                    s.setCuit(cuit);
                case 5:
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
                    break;
            }

        } while (opc != 5);

        saveSupermarketInJsonFile(superMarketList);
        System.out.println("Datos guardados exitosamente!!!");

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

        //busca supermercado por nombre en el map general del json
        for (Map.Entry<String, Supermarket> entry : superMarketList.entrySet()) {
            if (entry.getValue().getName().equalsIgnoreCase(name)) {
                supermarket = entry.getValue();
            }
        }
        //si encuentra retorna supermercado sino retorna null
        return supermarket;
    }

    @Override
    public void showListSupermarket(Supermarket supermarket) {
        if (supermarket != null) {
            // muestra la lista de supermercados si existe
            System.out.println(supermarket);
        }
    }

    @Override
    public List<ProductForSale> serchByCategoryInSupermarket(Supermarket supermarket, Category category) {

        //creo una lista de productos que coincidan con la categoria enviada por parametro
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

        //recorro y armo una lista de productos que esten en oferta
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

        //recorro y busco productos que contengan en su descripcion lo pasado por parametro
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
