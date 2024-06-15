package Service.impl;

import Models.Product;
import Models.Supermarket;
import Service.SupermarketService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SupermarketServiceImpl implements SupermarketService, Serializable {
private final File supermarketFile= new File("supermarket.json");

    @Override
    public Supermarket addSupermarket() {
        Scanner sc= new Scanner(System.in);
        Scanner si= new Scanner(System.in);
        Set<Product>products= new HashSet<>();

        System.out.println("ingrese nombre/denominacion: ");
        String name= sc.nextLine();
        System.out.println("Ingrese direccion: ");
        String adress= sc.nextLine();
        System.out.println("Ingrese telefono: ");
        String phone= sc.nextLine();
        System.out.println("Ingrese clave de indentificacion tributaria: ");
        String cuit= sc.nextLine();
        Integer opc;

        do{
            System.out.println("1 - Ingresar un alimento: ");
            System.out.println("2 - Ingrese una bebida: ");
            System.out.println("3 - salir");
            opc=si.nextInt();

            switch(opc){
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
        }while(opc!=3);
        Supermarket s= new Supermarket(name, adress, phone, cuit, (HashSet<Product>) products);

        return s;

    }

    @Override
    public void deleteSupermarket(Supermarket s) {
        Scanner sr= new Scanner(System.in);
        System.out.println("Ingrese CUIT del supermercado a eliminar: ");
        String cuit = sr.nextLine();

    }

    @Override
    public void modifySupermarket(Supermarket s) {
        Integer opc;
        Scanner sr= new Scanner(System.in);
        Scanner st= new Scanner(System.in);

        do{
            System.out.println("ELIGE CAMPO A MODIFICAR");
            System.out.println("1 - domicilio");
            System.out.println("2 - telefono");
            System.out.println("3 - CUIT");
            System.out.println("4 - salir");
            opc= sr.nextInt();
            switch(opc){
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


        }while(opc!=4);

    }

    @Override
    public void supermarketList(Supermarket s) {
        for(Product p: s.getProductListHashSet()){
            p.toString();
        }

    }

    public void saveSupermarketInJsonFile(Supermarket s) throws IOException {
        if (!supermarketFile.exists()) {
            supermarketFile.createNewFile();
        }
        try {
            ObjectMapper mapper= new ObjectMapper();
            mapper.writeValue(supermarketFile, s);


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

