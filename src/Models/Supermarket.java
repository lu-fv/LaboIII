package Models;
import  java.lang.String;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
//import java.util.Set;

public class Supermarket{
    private String name;
    private String address;
    private String phone;
    private String cuit;
    private Set<ProductForSale> productListHashSet = new HashSet<>();

    public Supermarket() {
    }

    public Supermarket(String name, String address, String phone, String cuit) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.cuit = cuit;
    }

    public Supermarket(String name, String address, String phone, String cuit, Set<ProductForSale> productListHashSet) {
        this.name = name;
        this.address = address;
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

    public Set<ProductForSale> getProductListHashSet() {
        return productListHashSet;
    }

    public void setProductListHashSet(Set<ProductForSale> productListHashSet) {
        this.productListHashSet = productListHashSet;
    }

    @Override
    public String toString() {
        return "Supermarket{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", cuit='" + cuit + '\'' +
                ", productListHashSet=" + productListHashSet +
                '}';
    }
}
