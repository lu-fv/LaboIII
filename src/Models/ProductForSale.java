package Models;

public class ProductForSale {
    private Product product;
    private Double price;
    private Boolean onSale;

    public ProductForSale() {

    }

    public ProductForSale(Product product, Double price, Boolean onSale) {
        this.setProduct(product);
        this.setPrice(price);
        this.setOnSale(onSale);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getOnSale() {
        return onSale;
    }

    public void setOnSale(Boolean onSale) {
        this.onSale = onSale;
    }

    @Override
    public String toString() {
        return product +
                "\nPrice: $" + price;
    }

    public Double applyDiscount(Integer percentage) {
        return this.price - percentage * this.price / 100;
    }
}
