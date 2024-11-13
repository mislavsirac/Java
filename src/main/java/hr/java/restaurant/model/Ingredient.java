package hr.java.restaurant.model;

import java.math.BigDecimal;

public class Ingredient extends Entity{
    String Name;
    Category Category;
    BigDecimal Kcal;
    String PreparationMethod;

    public Ingredient(Long id, String name, hr.java.restaurant.model.Category category, BigDecimal kcal, String preparationMethod) {
        super(id);
        Name = name;
        Category = category;
        Kcal = kcal;
        PreparationMethod = preparationMethod;
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
