package Models;
import Enums.Category;

public abstract class Product {
    private Integer ID;
    private String productName;
    private Double price;
    private String brand;
    private Category category;

    public Product() {
    }

    public Product(Integer ID, String productName, Double price, String brand, Category category) {
        this.ID = ID;
        this.productName = productName;
        this.price = price;
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
        return "Producto: " + productName +
                "\nId: " + ID +
                "\nPrecio: $" + price +
                "\nMarca: " + brand +
                "\nCategoría: " + category.getType();
    }
}
