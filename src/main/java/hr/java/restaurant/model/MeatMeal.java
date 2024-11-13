package hr.java.restaurant.model;

import java.math.BigDecimal;

public final class MeatMeal extends Meal implements Meat {
    private String cutType;

    public MeatMeal(Long id, String name, Category category, Ingredient[] ingredients, BigDecimal price, String cutType) {
        super(id, name, category, ingredients, price);
        this.cutType = cutType;
    }

    public String getCutType() {
        return cutType;
    }

    public void setCutType(String cutType) {
        this.cutType = cutType;
    }

    @Override
    public void checkForBoneContent() {
        System.out.println("Checking the meal for any bone content.");
    }

    @Override
    public void recommendServingTemperature() {
        System.out.println("Recommending the best serving temperature for the meat meal.");
    }
}
