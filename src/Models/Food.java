package Models;

import Enums.Category;

public class Food extends Product {
    private Boolean perishable;

    public Food() {

    }

    public Food(Integer ID, String productName, String brand, Category category, Boolean perishable) {
        super(ID, productName, brand, category);
        this.perishable = perishable;
    }

    public Boolean getPerishable() {
        return perishable;
    }

    public void setPerishable(Boolean perishable) {
        this.perishable = perishable;
    }

    @Override
    public String toString() {
        return super.toString() +
                (perishable ? "\nAlimento perecedero" : "\nAlimento no perecedero");
    }
}
