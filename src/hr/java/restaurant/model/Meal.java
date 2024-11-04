package hr.java.restaurant.model;

import java.math.BigDecimal;

public class Meal {
    String Name;
    Category Category;
    Ingredient[] ingredients;
    BigDecimal Price;

    public Meal(String name, hr.java.restaurant.model.Category category, Ingredient[] ingredients, BigDecimal price) {
        Name = name;
        Category = category;
        this.ingredients = ingredients;
        Price = price;
    }

    public Meal() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public hr.java.restaurant.model.Category getCategory() {
        return Category;
    }

    public void setCategory(hr.java.restaurant.model.Category category) {
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
}
