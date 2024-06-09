package Models;

public class Food extends Product{
    private Double kg;

    public Food(){

    }
    public Food(Integer ID, String productName, Integer brand, Enum categories, Double kg) {
        super(ID, productName, brand, categories);
        this.kg = kg;
    }

    public Double getKg() {
        return kg;
    }

    public void setKg(Double kg) {
        this.kg = kg;
    }
}
