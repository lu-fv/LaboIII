package Models;

import Enums.Category;
import Interface.Warning;
import Utils.Verification;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serializable;

@JsonTypeName("Beverage")
public class Beverage extends Product implements Warning, Serializable {
    //ATTRIBUTES------------------------------------------
    private Double litres;

    //CONSTRUCTORS--------------------------------------
    public Beverage(){
    }

    public Beverage(Double litres) {
        this.litres = litres;
    }

    public Beverage(Integer ID, String productName, String brand, Category category, Double litres) {
        super(ID, productName, brand, category);
        this.setLitres(litres);
    }

    //GETTERS & SETTERS---------------------------------

    public Double getLitres() {
        return litres;
    }

    //@NotNull
    public void setLitres(Double litres) {
        try {
            Verification.isNegative(litres);
            Verification.isNull(litres);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("La cantidad de litros no puede ser negativa");
        } catch (NullPointerException e) {
            throw new NullPointerException("Completar datos: Litros");
        }
        this.litres = litres;
    }

    //TO STRING-----------------------------------------
    @Override
    public String toString() {
        return super.toString() +
                "\nContenido: " + litres + " lt" +
                (this.getCategory() == Category.ALCOHOL ? "\n" + warning() : "");
    }

    private String warning() {
        return "ATENCIÓN: Producto con alcohol. Prohibido para menores.";
    }
}
