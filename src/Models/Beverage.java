package Models;

import Enums.Category;

public class Beverage extends Product{
    //ATRIBUTOS------------------------------------------
    private Double litres;

    //CONSTRUCTORES--------------------------------------
    public Beverage(){
    }

    public Beverage(Double litres) {
        this.litres = litres;
    }

    public Beverage(Integer ID, String productName, String brand, Category category, Double litres) {
        super(ID, productName, brand, category);
        this.litres = litres;
    }

    //GETTERS & SETTERS---------------------------------

    public Double getLitres() {
        return litres;
    }

    public void setLitres(Double litres) {
        this.litres = litres;
    }

    //TO STRING-----------------------------------------
    @Override
    public String toString() {
        return super.toString() +
                "\nContenido: " + litres + " lt";
    }
}
