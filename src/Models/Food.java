package Models;

import Enums.Category;

public class Food extends Product{
    private Double kg;

    public Food(){

    }
    public Food(Integer ID, String productName, Double price, String brand, Category category, Double kg) {
        super(ID, productName, price, brand, category);
        this.setKg(kg);
    }

    public Double getKg() {
        return kg;
    }

    public void setKg(Double kg) {
        this.kg = kg;
    }

    @Override
    public String toString() {
        return super.toString()+
                "\nPeso: " + kg + " kg";
    }
}
