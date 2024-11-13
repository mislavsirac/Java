package hr.java.restaurant.model;

import java.math.BigDecimal;

public final class VegetarianMeal extends Meal implements Vegetarian {
    private boolean includesDairy;

    public VegetarianMeal(Long id, String name, Category category, Ingredient[] ingredients, BigDecimal price, boolean includesDairy) {
        super(id, name, category, ingredients, price);
        this.includesDairy = includesDairy;
    }

    public boolean isIncludesDairy() {
        return includesDairy;
    }

    public void setIncludesDairy(boolean includesDairy) {
        this.includesDairy = includesDairy;
    }

    @Override
    public void checkForEggs() {
        System.out.println("Checking if the meal includes eggs.");
    }

    @Override
    public void ensureNoMeat() {
        System.out.println("Ensuring no meat is included in the meal.");
    }
}
