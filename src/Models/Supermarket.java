package Models;
import  java.lang.String;
import java.util.HashMap;
import java.util.HashSet;
//import java.util.Set;

public class Supermarket {
    private String name;
    private String adresss;
    private String phone;
    private String cuit;
    HashSet<Product> productListHashSet = new HashSet<>();

    public Supermarket() {
    }

    public Supermarket(String name, String adresss, String phone, String cuit, HashSet<Product> productListHashSet) {
        this.name = name;
        this.adresss = adresss;
        this.phone = phone;
        this.cuit = cuit;
        this.productListHashSet = productListHashSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresss() {
        return adresss;
    }

    public void setAdresss(String adresss) {
        this.adresss = adresss;
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

    public HashSet<Product> getProductListHashSet() {
        return productListHashSet;
    }

    public void setProductListHashSet(HashSet<Product> productListHashSet) {
        this.productListHashSet = productListHashSet;
    }

    
}
