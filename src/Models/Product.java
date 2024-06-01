package Models;

public abstract class Product {
    private Integer ID;
    private String productName;
    private Integer brand;
    public Enum Categories;

    public Product() {
    }

    public Product(Integer ID, String productName, Integer brand, Enum categories) {
        this.ID = ID;
        this.productName = productName;
        this.brand = brand;
        Categories = categories;
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

    public Enum getCategories() {
        return Categories;
    }

    public void setCategories(Enum categories) {
        Categories = categories;
    }
}
