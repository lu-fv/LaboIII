package Models;

import Enums.Category;

import javax.swing.text.StyledEditorKit;

public class Beverage extends Product{
    private Double litres;

    public Beverage(){
    }

    public Beverage(Double litres) {
        this.litres = litres;
    }

    public Beverage(Integer id, String productName, String brand, Category category, Double unitPrice, Double litres) {
        super(id, productName, brand, category, unitPrice);
        this.litres = litres;
    }

    public Double getLitres() {
        return litres;
    }

    public void setLitres(Double litres) {
        this.litres = litres;
    }
}
