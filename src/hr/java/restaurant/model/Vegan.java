package hr.java.restaurant.model;

public sealed interface Vegan permits VeganMeal {
    void checkForAnimalProducts();
    void promoteEcoFriendlyPackaging();
}
