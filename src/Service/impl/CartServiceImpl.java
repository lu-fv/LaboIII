package Service.impl;

import Models.Cart;
import Models.ProductForSale;
import Service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class CartServiceImpl implements CartService {
    private final File cartFile = new File("cart.json");
    public Cart<ProductForSale> cart = new Cart<>();

    public Cart<ProductForSale> getCart() {
        return cart;
    }

    public void addProductForSale(ProductForSale p, Integer amount) throws IOException {
        //si esta vacio el carrito lo agrego simplemente
        if (cart.getCart().isEmpty()) {
            //agrego el producto al hashMap
            cart.getCart().put(p, amount);
            //le sumo el valor del producto agregado al precio total del carrito
            cart.setTotalPrice(p.getPrice() * amount);
        } else {
            //sino, primero busco si el producto ya no estaba en la lista
            Integer flag = 0;
            for (Map.Entry<ProductForSale, Integer> entry : cart.getCart().entrySet()) {
                if (entry.getKey().equals(p)) {
                    //si el producto esta dentro de la lista le sumo uno en cantidad
                    entry.setValue(entry.getValue() + 1);
                    //sumo el valor al precio total del carrito
                    cart.setTotalPrice(cart.getTotalPrice() + (p.getPrice() * amount));
                    flag = 1;
                }
            }
            //si despues de recorrer el carrito el producto no estaba porque el flag sigue en cero
            if (flag == 0) {
                //entonces agrego el producto al hashMap
                cart.getCart().put(p, amount);
                //sumo al precio total el nuevo producto agregado
                cart.setTotalPrice(cart.getTotalPrice() + (p.getPrice() * amount));
            }
        }
    }

    public void showCartsProductList() {
        if (!cart.getCart().isEmpty()) {
            for (Map.Entry<ProductForSale, Integer> entry : cart.getCart().entrySet()) {
                System.out.println(entry);
            }
        } else {
            System.out.println("Aun no a agregado productos al carrito!");
        }

    }

    public void saveCartList() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(cartFile, cart.getCart());
        System.out.println("Carrito guardado exitosamente!!!");
    }

    public void deleteSomeProductOfCart() throws IOException {
        Scanner sc = new Scanner(System.in);
        Boolean flag = false;
        ProductForSale product = new ProductForSale();
        for (Map.Entry<ProductForSale, Integer> entry : cart.getCart().entrySet()) {
            System.out.println(entry);
        }
        System.out.println("Ingrese el id del producto que desea eliminar...");
        for (Map.Entry<ProductForSale, Integer> entry : cart.getCart().entrySet()) {
            if (entry.getKey().getProduct().getID() == sc.nextInt()) {
                product = entry.getKey();
                flag = true;
            }
        }
        if (flag) {
            System.out.println("Ustede desea eliminar el siguiente producto " + product + ".Es correcto? s/n");
            if (sc.nextLine().equalsIgnoreCase("s")) {
                cart.getCart().remove(product);
                System.out.println("Producto eliminado exitosamente");
                saveCartList();
            }
        } else {
            System.out.println("No ha ingresado un numero correcto de id");
        }

    }

    public void modifyCartList() throws IOException {
        Scanner sc = new Scanner(System.in);
        Boolean flag = false;
        ProductForSale product = new ProductForSale();
        for (Map.Entry<ProductForSale, Integer> entry : cart.getCart().entrySet()) {
            System.out.println(entry);
        }
        System.out.println("Ingrese el id del producto que desea modificar la cantidad...");
        for (Map.Entry<ProductForSale, Integer> entry : cart.getCart().entrySet()) {
            if (entry.getKey().getProduct().getID() == sc.nextInt()) {
                System.out.println("Ingrese la cantidad deseada...");
                entry.setValue(sc.nextInt());
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("No ha ingresado un numero correcto de id");
        } else {
            System.out.println("Cantidad de producto modificada exitosamente");
            saveCartList();
        }

    }

}
