package Models;
import Enums.Category;

public abstract class Product {
    private Integer ID;
    private String productName;
    private Integer brand;
    private Category category;

    public Product() {
    }

    public Product(Integer ID, String productName, Integer brand, Category category) {
        this.ID = ID;
        this.productName = productName;
        this.brand = brand;
        this.category = category;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getBrand() {
        return brand;
    }

    public void setBrand(Integer brand) {
        this.brand = brand;
    }

    public Enum getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
