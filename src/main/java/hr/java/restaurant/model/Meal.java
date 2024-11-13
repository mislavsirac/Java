package hr.java.restaurant.model;

import java.math.BigDecimal;
import java.util.Arrays;

public class Meal extends Entity{
    String Name;
    Category Category;
    Ingredient[] ingredients;
    BigDecimal Price;

    public Meal(Long id, String name, Category category, Ingredient[] ingredients, BigDecimal price) {
        super(id);
        Name = name;
        Category = category;
        this.ingredients = ingredients;
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Category getCategory() {
        return Category;
    }

    public void setCategory(Category category) {
        Category = category;
    }

    public Ingredient[] getIngridients() {
        return ingredients;
    }

    public void setIngridients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal price) {
        Price = price;
    }

    public BigDecimal getTotalCalories() {
        return Arrays.stream(ingredients)
                .map(Ingredient::getKcal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
