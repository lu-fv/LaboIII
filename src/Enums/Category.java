package Enums;

public enum Category {
    DAIRY(0, "Lácteos"),
    BAKERY(1, "Panadería"),
    GROCERY(2, "Almacén"),
    ALCOHOL(3, "Bebidas alcohólicas");


    private Integer id;
    private String type;

    Category(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return id + ": " + type;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}