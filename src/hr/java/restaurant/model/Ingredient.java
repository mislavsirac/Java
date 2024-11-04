package hr.java.restaurant.model;

import java.math.BigDecimal;

public class Ingredient {
    String Name;
    Category Category;
    BigDecimal Kcal;
    String PreparationMethod;

    public Ingredient(String name, hr.java.restaurant.model.Category category, BigDecimal kcal, String preparationMethod) {
        Name = name;
        Category = category;
        Kcal = kcal;
        PreparationMethod = preparationMethod;
    }

    public Ingredient() {
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

    public BigDecimal getKcal() {
        return Kcal;
    }

    public void setKcal(BigDecimal kcal) {
        Kcal = kcal;
    }

    public String getPreparationMethod() {
        return PreparationMethod;
    }

    public void setPreparationMethod(String preparationMethod) {
        PreparationMethod = preparationMethod;
    }
}
