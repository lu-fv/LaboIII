package Models;
import java.io.Serializable;
import  java.lang.String;
import java.util.*;
//import java.util.Set;

public class Supermarket implements Serializable {
    private String name;
    private String address;
    private String phone;
    private String cuit;
    private List<ProductForSale> productList;

    public Supermarket() {
    }

    public Supermarket(String name, String address, String phone, String cuit) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.cuit = cuit;
        this.productList=new ArrayList<>();
    }

    public Supermarket(String name, String address, String phone, String cuit, List<ProductForSale> productList) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.cuit = cuit;
        this.productList = productList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public List<ProductForSale> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductForSale> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "         SUPERMERCADO:\n" +
                "Nombre:      '" + name + "'\n" +
                "Direccion:   " + address + "\n" +
                "Telefono:    " + phone + "\n" +
                "CUIT:        " + cuit + "\n" +
                "        LISTADO DE PRODUCTOS DEL SUPERMERCADO " + name + ":\n" + productList;

    }
}
