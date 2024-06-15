package Models;

import Enums.Category;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.lang.String;
import java.util.*;
//import java.util.Set;

public class Supermarket<T> {
    private String name;
    private String address;
    private String phone;
    private String cuit;
    private Set<T> productListHashSet = new HashSet<>();

    public Supermarket() {
    }

    public Supermarket(String name, String address, String phone, String cuit, HashSet<T> productListHashSet) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.cuit = cuit;
        this.productListHashSet = productListHashSet;
    }

    public Supermarket(String carrefour, String address, String number, String cuit, Set<Product> listaProducto) {
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

    public Set<T> getProductListHashSet() {
        return productListHashSet;
    }

    public void setProductListHashSet(Set<T> productListHashSet) {
        this.productListHashSet = productListHashSet;
    }



}
