package hr.java.restaurant.model;

import java.time.LocalDateTime;

public class Order {
    Restaurant Restaurant;
    Meal[] Meals;
    Deliverer Deliverer;
    LocalDateTime DeliveryDateAndTime;

    public Order(hr.java.restaurant.model.Restaurant restaurant, Meal[] meals, hr.java.restaurant.model.Deliverer deliverer, LocalDateTime deliveryDateAndTime) {
        Restaurant = restaurant;
        Meals = meals;
        Deliverer = deliverer;
        DeliveryDateAndTime = deliveryDateAndTime;
    }

    public Order() {
    }

    public hr.java.restaurant.model.Restaurant getRestaurant() {
        return Restaurant;
    }

    public void setRestaurant(hr.java.restaurant.model.Restaurant restaurant) {
        Restaurant = restaurant;
    }

    public Meal[] getMeals() {
        return Meals;
    }

    public void setMeals(Meal[] meals) {
        Meals = meals;
    }

    public hr.java.restaurant.model.Deliverer getDeliverer() {
        return Deliverer;
    }

    public void setDeliverer(hr.java.restaurant.model.Deliverer deliverer) {
        Deliverer = deliverer;
    }

    public LocalDateTime getDeliveryDateAndTime() {
        return DeliveryDateAndTime;
    }

    public void setDeliveryDateAndTime(LocalDateTime deliveryDateAndTime) {
        DeliveryDateAndTime = deliveryDateAndTime;
    }
}
