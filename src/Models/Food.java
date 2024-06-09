package Models;

import Enums.Category;

public class Food extends Product{
    private Double kg;

    public Food(){

    }
    public Food(Integer ID, String productName, Integer brand, Category category, Double kg) {
        super(ID, productName, brand, category);
        this.kg = kg;
    }

    public Double getKg() {
        return kg;
    }

    public void setKg(Double kg) {
        this.kg = kg;
    }
}
