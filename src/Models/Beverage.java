package Models;

import Enums.Category;

public class Beverage extends Product{
    private Double litres;

    public Beverage(){
    }

    public Beverage(Double litres) {
        this.litres = litres;
    }

    public Beverage(Integer ID, String productName, Double price, String brand, Category category, Double litres) {
        super(ID, productName, price, brand, category);
        this.litres = litres;
    }

    public Double getLitres() {
        return litres;
    }

    public void setLitres(Double litres) {
        this.litres = litres;
    }
}
