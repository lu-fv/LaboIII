package Models;
import Enums.Category;
import Utils.Verification;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Food.class, name = "food"),
        @JsonSubTypes.Type(value = Beverage.class, name = "beverage")
})
public abstract class Product implements Serializable {
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
        try {
            Verification.isEmpty(productName);
            this.productName = productName;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Completar nombre");
        }
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        try {
            Verification.isEmpty(brand);
            this.brand = brand;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Completar marca");
        }
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        try {
            Verification.isNull(category);
            this.category = category;
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Ninguna categoría fue seleccionada");
        }
    }

    @Override
    public String toString() {
        return "Producto: " + productName +
                "\nId: " + ID +
                "\nMarca: " + brand +
                "\nCategoría: " + category.getType();
    }
}
