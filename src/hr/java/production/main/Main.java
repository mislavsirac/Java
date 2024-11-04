package hr.java.production.main;

import hr.java.restaurant.model.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Category[] categories = inputCategories(scanner);
        Ingredient[] ingredients = inputIngredients(scanner, categories);
        Meal[] meals = inputMeals(scanner, categories, ingredients);
        Chef[] chefs = inputChefs(scanner);
        Waiter[] waiters = inputWaiters(scanner);
        Deliverer[] deliverers = inputDeliverers(scanner);
        Restaurant[] restaurants = inputRestaurants(scanner, meals, chefs, waiters, deliverers);
        Order[] orders = inputOrders(scanner, restaurants, meals, deliverers);

        findRestaurantsWithMostExpensiveOrder(restaurants, orders);
        findDelivererWithMostDeliveries(orders, deliverers);

        scanner.close();
    }

    private static Category[] inputCategories(Scanner scanner) {
        Category[] categories = new Category[3];
        for (int i = 0; i < 3; i++) {
            System.out.printf("Enter category %d name: ", i + 1);
            String name = scanner.nextLine();
            System.out.print("Enter category description: ");
            String description = scanner.nextLine();
            categories[i] = new Category(name, description);
        }
        return categories;
    }

    private static Ingredient[] inputIngredients(Scanner scanner, Category[] categories) {
        Ingredient[] ingredients = new Ingredient[5];
        for (int i = 0; i < 5; i++) {
            System.out.printf("Enter ingredient %d name: ", i + 1);
            String name = scanner.nextLine();
            int categoryIndex = getValidIndex(scanner, "ingredient category index (0-2)", 0, 2);
            BigDecimal kcal = getValidBigDecimal(scanner, "ingredient kcal");
            System.out.print("Enter ingredient preparation method: ");
            String preparationMethod = scanner.nextLine();
            ingredients[i] = new Ingredient(name, categories[categoryIndex], kcal, preparationMethod);
        }
        return ingredients;
    }

    private static Meal[] inputMeals(Scanner scanner, Category[] categories, Ingredient[] ingredients) {
        Meal[] meals = new Meal[3];
        for (int i = 0; i < 3; i++) {
            System.out.printf("Enter meal %d name: ", i + 1);
            String name = scanner.nextLine();
            int categoryIndex = getValidIndex(scanner, "meal category index (0-2)", 0, 2);
            BigDecimal price = getValidBigDecimal(scanner, "meal price");
            meals[i] = new Meal(name, categories[categoryIndex], ingredients, price);
        }
        return meals;
    }

    private static Chef[] inputChefs(Scanner scanner) {
        Chef[] chefs = new Chef[3];
        for (int i = 0; i < 3; i++) {
            System.out.printf("Enter chef %d's first name: ", i + 1);
            String firstName = scanner.nextLine();
            System.out.print("Enter chef's last name: ");
            String lastName = scanner.nextLine();
            BigDecimal salary = getValidBigDecimal(scanner, "chef's salary");
            chefs[i] = new Chef(firstName, lastName, salary);
        }
        return chefs;
    }

    private static Waiter[] inputWaiters(Scanner scanner) {
        Waiter[] waiters = new Waiter[3];
        for (int i = 0; i < 3; i++) {
            System.out.printf("Enter waiter %d's first name: ", i + 1);
            String firstName = scanner.nextLine();
            System.out.print("Enter waiter's last name: ");
            String lastName = scanner.nextLine();
            BigDecimal salary = getValidBigDecimal(scanner, "waiter's salary");
            waiters[i] = new Waiter(firstName, lastName, salary);
        }
        return waiters;
    }

    private static Deliverer[] inputDeliverers(Scanner scanner) {
        Deliverer[] deliverers = new Deliverer[3];
        for (int i = 0; i < 3; i++) {
            System.out.printf("Enter deliverer %d's first name: ", i + 1);
            String firstName = scanner.nextLine();
            System.out.print("Enter deliverer's last name: ");
            String lastName = scanner.nextLine();
            BigDecimal salary = getValidBigDecimal(scanner, "deliverer's salary");
            deliverers[i] = new Deliverer(firstName, lastName, salary);
        }
        return deliverers;
    }

    private static Restaurant[] inputRestaurants(Scanner scanner, Meal[] meals, Chef[] chefs, Waiter[] waiters, Deliverer[] deliverers) {
        Restaurant[] restaurants = new Restaurant[3];
        for (int i = 0; i < 3; i++) {
            System.out.printf("Enter restaurant %d name: ", i + 1);
            String name = scanner.nextLine();
            System.out.print("Enter restaurant address (street): ");
            String street = scanner.nextLine();
            System.out.print("Enter restaurant address (house number): ");
            String houseNumber = scanner.nextLine();
            System.out.print("Enter restaurant address (city): ");
            String city = scanner.nextLine();
            System.out.print("Enter restaurant address (postal code): ");
            String postalCode = scanner.nextLine();
            Address address = new Address(street, houseNumber, city, postalCode);
            restaurants[i] = new Restaurant(name, address, meals, chefs, waiters, deliverers);
        }
        return restaurants;
    }

    private static Order[] inputOrders(Scanner scanner, Restaurant[] restaurants, Meal[] meals, Deliverer[] deliverers) {
        Order[] orders = new Order[3];
        for (int i = 0; i < 3; i++) {
            int restaurantIndex = getValidIndex(scanner, "restaurant index (0-2)", 0, 2);
            LocalDateTime deliveryDateTime = getValidDateTime(scanner, "delivery date and time (yyyy-MM-ddTHH:mm)");
            int delivererIndex = getValidIndex(scanner, "deliverer index (0-2)", 0, 2);
            orders[i] = new Order(restaurants[restaurantIndex], meals, deliverers[delivererIndex], deliveryDateTime);
        }
        return orders;
    }

    private static int getValidIndex(Scanner scanner, String prompt, int min, int max) {
        int index;
        while (true) {
            System.out.print("Enter " + prompt + ": ");
            try {
                index = Integer.parseInt(scanner.nextLine());
                if (index >= min && index <= max) {
                    break;
                } else {
                    System.out.printf("Invalid index. Please enter a value between %d and %d.%n", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        return index;
    }

    private static BigDecimal getValidBigDecimal(Scanner scanner, String prompt) {
        BigDecimal value;
        while (true) {
            System.out.print("Enter " + prompt + ": ");
            try {
                value = new BigDecimal(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid decimal number.");
            }
        }
        return value;
    }

    private static LocalDateTime getValidDateTime(Scanner scanner, String prompt) {
        LocalDateTime dateTime;
        while (true) {
            System.out.print("Enter " + prompt + ": ");
            try {
                dateTime = LocalDateTime.parse(scanner.nextLine());
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter a date in the format yyyy-MM-ddTHH:mm.");
            }
        }
        return dateTime;
    }

    private static void findRestaurantsWithMostExpensiveOrder(Restaurant[] restaurants, Order[] orders) {
        BigDecimal maxPrice = BigDecimal.ZERO;

        for (Order order : orders) {
            BigDecimal orderPrice = BigDecimal.ZERO;
            for (Meal meal : order.getMeals()) {
                orderPrice = orderPrice.add(meal.getPrice());
            }
            if (orderPrice.compareTo(maxPrice) > 0) {
                maxPrice = orderPrice;
            }
        }

        List<Restaurant> restaurantsWithMaxPriceOrder = new ArrayList<>();

        for (Order order : orders) {
            BigDecimal orderPrice = BigDecimal.ZERO;
            for (Meal meal : order.getMeals()) {
                orderPrice = orderPrice.add(meal.getPrice());
            }
            if (orderPrice.equals(maxPrice)) {
                Restaurant restaurant = order.getRestaurant();
                if (!restaurantsWithMaxPriceOrder.contains(restaurant)) {
                    restaurantsWithMaxPriceOrder.add(restaurant);
                }
            }
        }

        if (!restaurantsWithMaxPriceOrder.isEmpty()) {
            System.out.println("Restaurants with the most expensive order:");
            for (Restaurant restaurant : restaurantsWithMaxPriceOrder) {
                System.out.println("Name: " + restaurant.getName());
                System.out.println("Address: " + restaurant.getAddress().getStreet() + ", " +
                        restaurant.getAddress().getHouseNumber() + ", " +
                        restaurant.getAddress().getCity() + ", " +
                        restaurant.getAddress().getPostalCode());
                System.out.println("Meals offered: ");
                for (Meal meal : restaurant.getMeals()) {
                    System.out.println(" - " + meal.getName() + ": " + meal.getPrice());
                }
                System.out.println("Chefs: ");
                for (Chef chef : restaurant.getChefs()) {
                    System.out.println(" - " + chef.getFirstName() + " " + chef.getLastName());
                }
                System.out.println("Waiters: ");
                for (Waiter waiter : restaurant.getWaiters()) {
                    System.out.println(" - " + waiter.getFirstName() + " " + waiter.getLastName());
                }
                System.out.println("Deliverers: ");
                for (Deliverer deliverer : restaurant.getDeliverers()) {
                    System.out.println(" - " + deliverer.getFirstName() + " " + deliverer.getLastName());
                }
                System.out.println("---------------------------------");
            }
        } else {
            System.out.println("No orders found.");
        }
    }

    private static void findDelivererWithMostDeliveries(Order[] orders, Deliverer[] deliverers) {
        int[] deliveryCounts = new int[deliverers.length];

        for (Order order : orders) {
            Deliverer deliverer = order.getDeliverer();
            for (int i = 0; i < deliverers.length; i++) {
                if (deliverers[i].equals(deliverer)) {
                    deliveryCounts[i]++;
                    break;
                }
            }
        }

        int maxDeliveries = 0;
        for (int count : deliveryCounts) {
            if (count > maxDeliveries) {
                maxDeliveries = count;
            }
        }

        System.out.println("Deliverer(s) with the most deliveries (" + maxDeliveries + " deliveries):");
        for (int i = 0; i < deliverers.length; i++) {
            if (deliveryCounts[i] == maxDeliveries) {
                Deliverer deliverer = deliverers[i];
                System.out.println("Name: " + deliverer.getFirstName() + " " + deliverer.getLastName());
                System.out.println("Salary: " + deliverer.getSalary());
                System.out.println("---------------------------------");
            }
        }
    }

}
