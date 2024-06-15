package Models;
import Enums.Category;

public abstract class Product {
    private Integer id;
    private String productName;
    private String brand;
    private Category category;
    private Double unitPrice;

    private Boolean sale = false;

    public Product() {
    }

    public Product(Integer id, String productName, String brand, Category category, Double unitPrice) {
        this.id = id;
        this.productName = productName;
        this.brand = brand;
        this.category = category;
        this.unitPrice = unitPrice;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Enum getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Boolean getSale() {
        return sale;
    }

    public void setSale(Boolean sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", brand='" + brand + '\'' +
                ", category=" + category +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
