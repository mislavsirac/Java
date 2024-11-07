package hr.java.restaurant.model;

public sealed interface Meat permits MeatMeal {
    void checkForBoneContent();
    void recommendServingTemperature();
}
