package Models;
import  java.lang.String;
import java.util.HashMap;
import java.util.HashSet;
//import java.util.Set;

public class Supermarket {
    private String name;
    private String adresss;
    private String tel;
    private String CUIT;
    HashSet<Product> productListHashSet = new HashSet<>();

    public Supermarket() {
    }

    public Supermarket(String name, String adresss, String tel, String CUIT, HashSet<Product> productListHashSet) {
        this.name = name;
        this.adresss = adresss;
        this.tel = tel;
        this.CUIT = CUIT;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCUIT() {
        return CUIT;
    }

    public void setCUIT(String CUIT) {
        this.CUIT = CUIT;
    }

    public HashSet<Product> getProductListHashSet() {
        return productListHashSet;
    }

    public void setProductListHashSet(HashSet<Product> productListHashSet) {
        this.productListHashSet = productListHashSet;
    }

    
}
