package Models;

import Enums.Category;
import Utils.Verification;

import java.io.Serializable;

public class Food extends Product implements Serializable {
    //ATTRIBUTES-----------------------------------------
    private Boolean perishable;

    //CONSTRUCTORS---------------------------------------
    public Food() {

    }

    public Food(Integer ID, String productName, String brand, Category category, Boolean perishable) {
        super(ID, productName, brand, category);
        this.setPerishable(perishable);
    }

    //GETTERS & SETTERS----------------------------------
    public Boolean getPerishable() {
        return perishable;
    }

    public void setPerishable(Boolean perishable) {
        try {
            Verification.isEmpty(perishable);
        } catch (NullPointerException e) {
            throw new NullPointerException("Completar datos: Perecedero");
        }
        this.perishable = perishable;
    }

    //TO STRING------------------------------------------
    @Override
    public String toString() {
        return super.toString() +
                (perishable ? "\nAlimento perecedero" : "\nAlimento no perecedero");
    }
}
