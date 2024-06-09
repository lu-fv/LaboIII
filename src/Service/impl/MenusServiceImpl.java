package Service.impl;

import Service.MenusService;

import java.util.Scanner;

public class MenusServiceImpl implements MenusService {

    @Override
    public void initialMenu(){

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
    public void clientMenu(){
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
        }while(opc!=0);
    }

}
