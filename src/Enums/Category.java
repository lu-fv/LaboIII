package Enums;

public enum Category {
    DAIRY("Dairy"),
    BAKERY("Bakery"),
    GROCERY("Grocery"),
    ALCOHOL("Alcohol");

    private String type;

    Category(String type) {
        this.type = type;
    }
}