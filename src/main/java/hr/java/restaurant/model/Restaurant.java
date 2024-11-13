package hr.java.restaurant.model;

public class Restaurant extends Entity {
    String Name;
    Address Address;
    Meal[] Meals;
    Chef[] Chefs;
    Waiter[] Waiters;
    Deliverer[] Deliverers;

    public Restaurant(Long id, String name, Address address, Meal[] meals, Chef[] chefs, Waiter[] waiters, Deliverer[] deliverers) {
        super(id);
        Name = name;
        Address = address;
        Meals = meals;
        Chefs = chefs;
        Waiters = waiters;
        Deliverers = deliverers;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Address getAddress() {
        return Address;
    }

    public void setAddress(Address address) {
        Address = address;
    }

    public Meal[] getMeals() {
        return Meals;
    }

    public void setMeals(Meal[] meals) {
        Meals = meals;
    }

    public Chef[] getChefs() {
        return Chefs;
    }

    public void setChefs(Chef[] chefs) {
        Chefs = chefs;
    }

    public Waiter[] getWaiters() {
        return Waiters;
    }

    public void setWaiters(Waiter[] waiters) {
        Waiters = waiters;
    }

    public Deliverer[] getDeliverers() {
        return Deliverers;
    }

    public void setDeliverers(Deliverer[] deliverers) {
        Deliverers = deliverers;
    }
}
