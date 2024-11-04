package hr.java.restaurant.model;

public class Category {
    String Name;
    String Description;

    public Category(String name, String description) {
        Name = name;
        Description = description;
    }

    public Category() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
