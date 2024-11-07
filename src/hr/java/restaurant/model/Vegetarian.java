package hr.java.restaurant.model;

public sealed interface Vegetarian permits VegetarianMeal {
    void checkForEggs();
    void ensureNoMeat();
}
