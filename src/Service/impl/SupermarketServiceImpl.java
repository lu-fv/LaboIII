package Service.impl;

import Models.Product;
import Models.Supermarket;
import Service.SupermarketService;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.HashSet;

import Enums.Category;
import Models.ProductForSale;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SupermarketServiceImpl implements SupermarketService, Serializable {
    private final File supermarketFile = new File("supermarket.json");

    //region ABM-----------------------------------------------------------------
    @Override
    public Supermarket addSupermarket() {
        Scanner sc = new Scanner(System.in);
        Scanner si = new Scanner(System.in);
        Set<Product> products = new HashSet<>();
        System.out.println("ingrese nombre/denominacion: ");
        String name = sc.nextLine();
        System.out.println("Ingrese direccion: ");
        String adress = sc.nextLine();
        System.out.println("Ingrese telefono: ");
        String phone = sc.nextLine();
        System.out.println("Ingrese clave de indentificacion tributaria: ");
        String cuit = sc.nextLine();
        Integer opc;

        do {
            System.out.println("1 - Ingresar un alimento: ");
            System.out.println("2 - Ingrese una bebida: ");
            System.out.println("3 - salir");
            opc = si.nextInt();

            switch (opc) {
                case 1:
                    ///funcion de alta de alimento
                    //products.add(//return de la funcion);
                    break;
                case 2:
                    ///funcion de alta de bebida
                    //products.add(//return de la funcion);
                    break;
                default:
                    System.out.println("opcion incorrecta");
                    break;
            }
        } while (opc != 3);

        Set<ProductForSale> listProduct = new HashSet<>();
        Supermarket s = new Supermarket(name, adress, phone, cuit, listProduct);

        return s;
    }

    @Override
    public void deleteSupermarket(Supermarket s) {
        Scanner sr = new Scanner(System.in);
        System.out.println("Ingrese CUIT del supermercado a eliminar: ");
        String cuit = sr.nextLine();

    }

    @Override
    public void modifySupermarket(Supermarket s) {
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

    }

    @Override
    public void supermarketList(Supermarket s) {
        for (ProductForSale p : s.getProductListHashSet()) {
            p.toString();
        }
    }

    @Override
    public void saveSupermarketInJsonFile(Supermarket s) throws IOException {
        if (!supermarketFile.exists()) {
            supermarketFile.createNewFile();
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(supermarketFile, s);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    //endregion

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
    public List<ProductForSale> searchSalesProducts() {
        List<ProductForSale> listProduct = new ArrayList<>();

        Supermarket vea = this.search("vea");
        Supermarket carrefour = this.search("carrefour");
        Supermarket disco = this.search("disco");

        for (ProductForSale p : vea.getProductListHashSet()){
            if(p.getOnSale()){
                listProduct.add(p);
            }
        }

        for (ProductForSale p : carrefour.getProductListHashSet()){
            if(p.getOnSale()){
                listProduct.add(p);
            }
        }

        for (ProductForSale p : disco.getProductListHashSet()){
            if(p.getOnSale()){
                listProduct.add(p);
            }
        }
        return listProduct;
    }

    @Override
    public List<ProductForSale> searchSpecialProductsByName(String name) {
        List<ProductForSale> listProduct = new ArrayList<>();

        Supermarket vea = this.search("vea");
        Supermarket carrefour = this.search("carrefour");
        Supermarket disco = this.search("disco");

        for (ProductForSale p : vea.getProductListHashSet()){
            if(p.getProduct().getProductName().equalsIgnoreCase(name)){
                listProduct.add(p);
            }
        }

        for (ProductForSale p : carrefour.getProductListHashSet()){
            if(p.getProduct().getProductName().equalsIgnoreCase(name)){
                listProduct.add(p);
            }
        }

        for (ProductForSale p : disco.getProductListHashSet()){
            if(p.getProduct().getProductName().equalsIgnoreCase(name)){
                listProduct.add(p);
            }
        }
        return listProduct;

    }

    @Override
    public List<ProductForSale> searchProductsByCategory(Category c) {
        List<ProductForSale> listProduct = new ArrayList<>();

        Supermarket vea = this.search("vea");
        Supermarket carrefour = this.search("carrefour");
        Supermarket disco = this.search("disco");

        for (ProductForSale p : vea.getProductListHashSet()){
            if(p.getProduct().getCategory() == c){
                listProduct.add(p);
            }
        }

        for (ProductForSale p : carrefour.getProductListHashSet()){
            if(p.getProduct().getCategory() == c){
                listProduct.add(p);
            }
        }

        for (ProductForSale p : disco.getProductListHashSet()){
            if(p.getProduct().getCategory() == c){
                listProduct.add(p);
            }
        }
        return listProduct;
    }
    //endregion
}
