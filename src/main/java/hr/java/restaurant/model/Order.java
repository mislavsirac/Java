package hr.java.restaurant.model;

import java.time.LocalDateTime;

public class Order extends Entity{
    Restaurant Restaurant;
    Meal[] Meals;
    Deliverer Deliverer;
    LocalDateTime DeliveryDateAndTime;

    public Order(Long id, Restaurant restaurant, Meal[] meals, hr.java.restaurant.model.Deliverer deliverer, LocalDateTime deliveryDateAndTime) {
        super(id);
        Restaurant = restaurant;
        Meals = meals;
        Deliverer = deliverer;
        DeliveryDateAndTime = deliveryDateAndTime;
    }

    public Restaurant getRestaurant() {
        return Restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        Restaurant = restaurant;
    }

    public Meal[] getMeals() {
        return Meals;
    }

    public void setMeals(Meal[] meals) {
        Meals = meals;
    }

    public Deliverer getDeliverer() {
        return Deliverer;
    }

    public void setDeliverer(Deliverer deliverer) {
        Deliverer = deliverer;
    }

    public LocalDateTime getDeliveryDateAndTime() {
        return DeliveryDateAndTime;
    }

    public void setDeliveryDateAndTime(LocalDateTime deliveryDateAndTime) {
        DeliveryDateAndTime = deliveryDateAndTime;
    }
}
