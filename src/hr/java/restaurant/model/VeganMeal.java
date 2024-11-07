package hr.java.restaurant.model;

import java.math.BigDecimal;

public final class VeganMeal extends Meal implements Vegan {
    private boolean usesOrganicIngredients;

    public VeganMeal(Long id, String name, Category category, Ingredient[] ingredients, BigDecimal price, boolean usesOrganicIngredients) {
        super(id, name, category, ingredients, price);
        this.usesOrganicIngredients = usesOrganicIngredients;
    }

    public boolean isUsesOrganicIngredients() {
        return usesOrganicIngredients;
    }

    public void setUsesOrganicIngredients(boolean usesOrganicIngredients) {
        this.usesOrganicIngredients = usesOrganicIngredients;
    }

    @Override
    public void checkForAnimalProducts() {
        System.out.println("Ensuring no animal products are present in the meal.");
    }

    @Override
    public void promoteEcoFriendlyPackaging() {
        System.out.println("Promoting eco-friendly packaging for vegan meals.");
    }
}
