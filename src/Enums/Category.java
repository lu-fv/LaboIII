package Enums;

public enum Category {
    DAIRY(0, "Lacteos"),
    BAKERY(1, "Panaderia"),
    GROCERY(2, "Alimentos"),
    ALCOHOL(3, "Bebidas alcoholicas"),
    ALCOHOL_FREE(4,"Bebidas sin alcohol");


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