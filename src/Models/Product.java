package Models;
import Enums.Category;

public abstract class Product {
    private Integer ID;
    private String productName;
    private String brand;
    private Category category;

    public Product() {
    }

    public Product(Integer ID, String productName, String brand, Category category) {
        this.setID(ID);
        this.setProductName(productName);
        this.setBrand(brand);
        this.setCategory(category);
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "         PRODUCTO: " + productName +
                "\n            ID: " + ID +
                "\n         Marca: " + brand +
                "\n     Categoría: " + category.getType();
    }
}
