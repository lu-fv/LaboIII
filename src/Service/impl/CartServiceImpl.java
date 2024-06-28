package Service.impl;

import Models.Cart;
import Models.ProductForSale;
import Service.CartService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CartServiceImpl implements CartService {

    private Cart<ProductForSale> cart = new Cart<>();

    public Cart<ProductForSale> getCart() {
        return cart;
    }

    //Agregar producto al carrito ---------------------------------------------------------
    @Override
    public void addProductForSale(ProductForSale p, Integer amount) throws IOException {

        //si esta vacio el carrito o el producto no existe en el Hash lo agrego simplemente
        if (cart.getCart().isEmpty() || !cart.getCart().containsKey(p)) {
            cart.getCart().put(p, amount);
        } else {
            //sino ...si existe el producto en el carrito modifico la cantidad
            cart.getCart().put(p, cart.getCart().get(p) + amount);
        }
    }

    //Pasa la lista de productos por parametro y pregunta que desea agregar al carrito -----
    @Override
    public void addCartFromListProductForSale(List<ProductForSale> list) throws IOException {
        Boolean correctForm = true;
        do {
            try {
                Integer numProduct;
                Boolean alfa;
                Scanner sc = new Scanner(System.in);
                String continueAdd = "S";
                int i;

                do {
                    //muestro los productos de la lista mostrando con numeros que se relacionan con el i (indice)
                    System.out.println(">>>>>>>>>>>>>>>>>> LISTA DE PRODUCTOS <<<<<<<<<<<<<<<<<<<<<<");

                    for (i = 0; i < list.size(); i++) {
                        System.out.println("[Opcion : " + (i + 1) + "]\n" + list.get(i));
                        System.out.println("Supermercado : " + new ProductForSaleServiceImpl().searchSupermarketByEachProductForSale(list.get(i)));
                        System.out.println("_____________________________________________________________________");
                    }
                    System.out.println("Ingrese los productos que desea agregar a la lista por \'NUMERO DE OPCION\'...");
                    do {
                        numProduct = Integer.parseInt(sc.nextLine());

                    } while (numProduct < 0 || numProduct > i + 1);//Valido que el numero ingresado este dentro del largo de la lista

                    //Si ingresa una opcion correcta del listado procedemos a pedir la cantidad de ese producto para añadir al carrito
                    System.out.println("Ingrese la cantidad que desea del producto '" + list.get(numProduct - 1).getProduct().getProductName() +"' - Marca : "+ list.get(numProduct -1).getProduct().getBrand());
                    Integer amount = Integer.parseInt(sc.nextLine());
                    addProductForSale(list.get(numProduct - 1), amount);

                    System.out.println("Producto agregado al carrito. Desea seguir agregando productos de este listado? s/n");
                    do {
                        System.out.println("Ingrese una opcion correcta, s ó n");
                        continueAdd = sc.nextLine();
                    }while(!continueAdd.equalsIgnoreCase("s") && (!continueAdd.equalsIgnoreCase("n")));

                } while (continueAdd.equalsIgnoreCase("s"));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                correctForm = false;
            }
        } while (!correctForm);
        //suma todos los precios de productos del carrito
        this.totalPriceOfCart();
    }

    //Muestra tu lista del carrito
    @Override
    public void showCartsProductList() throws IOException {
        if (!cart.getCart().isEmpty()) {
            System.out.println("        TU LISTA DE COMPRAS: \n");
            Integer i = 1;
            for (Map.Entry<ProductForSale, Integer> entry : cart.getCart().entrySet()) {
                System.out.println("[" + i + "] \n " + entry.getKey() + " >> cantidad : " + entry.getValue());
                System.out.println("Supermercado : " + new ProductForSaleServiceImpl().searchSupermarketByEachProductForSale(entry.getKey()));
                System.out.println("_______________________________________________________________________________");
                i++;
            }
            System.out.println("          \nPRECIO TOTAL DE LA LISTA : [$" + cart.getTotalPrice() + "]");
        } else {
            System.out.println("Aun no a agregado productos al carrito!");
        }
    }

    //Borra un elemento del carrito
    @Override
    public void deleteSomeProductOfCart() throws IOException {
        Scanner sc = new Scanner(System.in);
        Boolean flag = false;

        ProductForSale product = new ProductForSale();
        //Recorro el Map de productos del carrito
        for (Map.Entry<ProductForSale, Integer> entry : cart.getCart().entrySet()) {
            System.out.println(entry);
        }
        //Pregunto cual desea eliminar
        System.out.println("Ingrese el ID del producto que desea eliminar...");
        Integer idNew = Integer.parseInt(sc.nextLine());
        //Busco ese producto en el Set de cada supermercado
        for (Map.Entry<ProductForSale, Integer> entry : cart.getCart().entrySet()) {
            if (entry.getKey().getProduct().getID().equals(idNew)) {
                //asigno a la variable producto... el producto que estaba buscando
                product = entry.getKey();
                //cambio el flag a true
                flag = true;
                break;
            }
        }
        //Si lo encontro
        if (flag) {
            //Pregunto si desea eliminar dicho producto para evitar confusiones

            System.out.println("Usted desea eliminar el siguiente producto " + product + ".Es correcto? s/n");
            String continueDelete;
            do {//valido que ingrese unicamente s o n
                System.out.println("Ingrese una opcion correcta, \'s\' ó \'n\'");
                continueDelete = sc.nextLine();
            }while(!continueDelete.equalsIgnoreCase("s") && (!continueDelete.equalsIgnoreCase("n")));

            if (continueDelete.equalsIgnoreCase("s")) {
                cart.getCart().remove(product);
                System.out.println("\nProducto eliminado exitosamente");
            }
        } else {
            System.out.println("\nNo ha ingresado un numero correcto de id");
        }
    }

    //Modifica un producto del carrito: o elimina un producto o modifica cantidad
    @Override
    public void modifyCartList() throws IOException {
        Scanner sc = new Scanner(System.in);
        Boolean flag = false;
        ProductForSale product = new ProductForSale();
        for (Map.Entry<ProductForSale, Integer> entry : cart.getCart().entrySet()) {
            System.out.println(entry);
        }
        System.out.println("Ingrese el id del producto que desea modificar la cantidad...");
        Integer idP = Integer.parseInt(sc.nextLine());
        for (Map.Entry<ProductForSale, Integer> entry : cart.getCart().entrySet()) {
            if (entry.getKey().getProduct().getID().equals(idP)) {
                System.out.println("Ingrese la cantidad deseada...");
                entry.setValue(sc.nextInt());
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("No ha ingresado un numero correcto de id");
        } else {
            System.out.println("Cantidad de producto modificada exitosamente");
        }
    }

    //Suma todos los precios de productos del carrito
    @Override
    public void totalPriceOfCart() {
        Double total = 0d;
        for (Map.Entry<ProductForSale, Integer> entry : cart.getCart().entrySet()) {
            total = total + (entry.getKey().getPrice() * entry.getValue());
        }
        cart.setTotalPrice(total);
    }

}
