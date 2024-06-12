package Enums;

public enum Category {
    DAIRY(0, "Dairy"),
    BAKERY(1, "Bakery"),
    GROCERY(2, "Grocery"),
    ALCOHOL(3, "Alcohol");

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

    public String getType() {
        return type;
    }
}