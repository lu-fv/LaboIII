package Models;

import Enums.Category;

public class Food extends Product{
    private Boolean perishable;

    public Food(){

    }

    public Food(Integer id, String productName, String brand, Category category, Double unitPrice, Boolean perishable) {
        super(id, productName, brand, category, unitPrice);
        this.perishable = perishable;
    }



    public Boolean getPerishable() {
        return perishable;
    }

    public void setPerishable(Boolean perishable) {
        this.perishable = perishable;
    }
}
