package Models;

public class Beverage extends Product{
    private Double litres;

    public Beverage(){
    }

    public Beverage(Integer ID, String productName, Integer brand, Enum categories, Double litres) {
        super(ID, productName, brand, categories);
        this.litres = litres;
    }

    public Double getLitres() {
        return litres;
    }

    public void setLitres(Double litres) {
        this.litres = litres;
    }
}
