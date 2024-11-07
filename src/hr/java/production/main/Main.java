package hr.java.production.main;

import hr.java.restaurant.model.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"))) {

            Category[] categories = inputCategories(scanner);
            Ingredient[] ingredients = inputIngredients(scanner, categories);
            Meal[] meals = inputMeals(scanner, categories, ingredients);
            Chef[] chefs = inputChefs(scanner);
            Waiter[] waiters = inputWaiters(scanner);
            Deliverer[] deliverers = inputDeliverers(scanner);
            Restaurant[] restaurants = inputRestaurants(scanner, meals, chefs, waiters, deliverers);
            Order[] orders = inputOrders(scanner, restaurants, meals, deliverers);
            Meal[] veganMeals = createVeganMeals(scanner, categories, ingredients);
            Meal[] vegetarianMeals = createVegetarianMeals(scanner, categories, ingredients);
            Meal[] meatMeals = createMeatMeals(scanner, categories, ingredients);

            analyzeEmployees(chefs, waiters, deliverers);
            analyzeMeals(veganMeals, vegetarianMeals, meatMeals);
            findRestaurantsWithMostExpensiveOrder(restaurants, orders);
            findDelivererWithMostDeliveries(orders, deliverers);

        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        }
    }

    private static Category[] inputCategories(Scanner scanner) {
        Category[] categories = new Category[3];
        for (int i = 0; i < 3; i++) {
            System.out.printf("Enter category %d name: ", i + 1);
            String name = scanner.nextLine();
            System.out.print("Enter category description: ");
            String description = scanner.nextLine();
            categories[i] = new Category.Builder()
                    .withId((long)i)
                    .withName(name)
                    .withDescription(description)
                    .build();
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
            ingredients[i] = new Ingredient((long)i, name, categories[categoryIndex], kcal, preparationMethod);
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
            meals[i] = new Meal((long)i, name, categories[categoryIndex], ingredients, price);
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

            System.out.print("Enter contract start date (yyyy-MM-dd): ");
            LocalDate startDate = getValidDate(scanner);

            System.out.print("Enter contract end date (yyyy-MM-dd): ");
            LocalDate endDate = getValidDate(scanner);

            System.out.print("Select contract type (1 for FULL_TIME, 2 for PART_TIME): ");
            Contract.ContractType contractType = getValidContractTypeChoice(scanner);

            Contract contract = new Contract(salary, startDate, endDate, contractType);

            chefs[i] = new Chef.Builder()
                    .withId((long) i + 1)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withContract(contract)
                    .build();
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

            System.out.print("Enter contract start date (yyyy-MM-dd): ");
            LocalDate startDate = getValidDate(scanner);

            System.out.print("Enter contract end date (yyyy-MM-dd): ");
            LocalDate endDate = getValidDate(scanner);

            System.out.print("Select contract type (1 for FULL_TIME, 2 for PART_TIME): ");
            Contract.ContractType contractType = getValidContractTypeChoice(scanner);

            Contract contract = new Contract(salary, startDate, endDate, contractType);

            waiters[i] = new Waiter.Builder()
                    .withId((long) i + 1)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withContract(contract)
                    .build();
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

            System.out.print("Enter contract start date (yyyy-MM-dd): ");
            LocalDate startDate = getValidDate(scanner);

            System.out.print("Enter contract end date (yyyy-MM-dd): ");
            LocalDate endDate = getValidDate(scanner);

            System.out.print("Select contract type (1 for FULL_TIME, 2 for PART_TIME): ");
            Contract.ContractType contractType = getValidContractTypeChoice(scanner);

            Contract contract = new Contract(salary, startDate, endDate, contractType);

            deliverers[i] = new Deliverer.Builder()
                    .withId((long) i + 1)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withContract(contract)
                    .build();
        }
        return deliverers;
    }

    private static LocalDate getValidDate(Scanner scanner) {
        LocalDate date;
        while (true) {
            try {
                date = LocalDate.parse(scanner.nextLine());
                break;
            } catch (DateTimeParseException e) {
                System.out.print("Invalid date format. Please enter a date in the format yyyy-MM-dd: ");
            }
        }
        return date;
    }

    // Helper method for contract type selection
    private static Contract.ContractType getValidContractTypeChoice(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("1")) {
                return Contract.ContractType.FULL_TIME;
            } else if (input.equals("2")) {
                return Contract.ContractType.PART_TIME;
            } else {
                System.out.print("Invalid choice. Please enter 1 for FULL_TIME or 2 for PART_TIME: ");
            }
        }
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
            Address address = new Address.Builder()
                    .withId((long)i)
                    .withStreet(street)
                    .withHouseNumber(houseNumber)
                    .withCity(city)
                    .withPostalCode(postalCode)
                    .build();

            System.out.println("Select meals for this restaurant by entering their indices (separated by commas):");
            for (int j = 0; j < meals.length; j++) {
                System.out.printf("%d: %s (Price: %s)%n", j, meals[j].getName(), meals[j].getPrice());
            }

            String[] mealIndices = scanner.nextLine().split(",");
            Meal[] selectedMeals = new Meal[mealIndices.length];

            for (int k = 0; k < mealIndices.length; k++) {
                int index = Integer.parseInt(mealIndices[k].trim());
                selectedMeals[k] = meals[index];
            }

            restaurants[i] = new Restaurant((long)i, name, address, selectedMeals, chefs, waiters, deliverers);
        }
        return restaurants;
    }


    private static Order[] inputOrders(Scanner scanner, Restaurant[] restaurants, Meal[] meals, Deliverer[] deliverers) {
        Order[] orders = new Order[3];
        for (int i = 0; i < 3; i++) {
            int restaurantIndex = getValidIndex(scanner, "restaurant index (0-2)", 0, 2);
            LocalDateTime deliveryDateTime = getValidDateTime(scanner, "delivery date and time (yyyy-MM-ddTHH:mm)");
            int delivererIndex = getValidIndex(scanner, "deliverer index (0-2)", 0, 2);

            System.out.println("Select meals for this order by entering their indices (separated by commas):");
            for (int j = 0; j < meals.length; j++) {
                System.out.printf("%d: %s (Price: %s)%n", j, meals[j].getName(), meals[j].getPrice());
            }

            String[] mealIndices = scanner.nextLine().split(",");
            Meal[] selectedMeals = new Meal[mealIndices.length];

            for (int k = 0; k < mealIndices.length; k++) {
                int mealIndex = Integer.parseInt(mealIndices[k].trim());
                selectedMeals[k] = meals[mealIndex];
            }

            orders[i] = new Order((long) i, restaurants[restaurantIndex], selectedMeals, deliverers[delivererIndex], deliveryDateTime);
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
                System.out.println("Salary: " + deliverer.getContract().getSalary());
                System.out.println("---------------------------------");
            }
        }
    }

    private static void analyzeEmployees(Chef[] chefs, Waiter[] waiters, Deliverer[] deliverers) {
        Person[] employees = new Person[chefs.length + waiters.length + deliverers.length];
        System.arraycopy(chefs, 0, employees, 0, chefs.length);
        System.arraycopy(waiters, 0, employees, chefs.length, waiters.length);
        System.arraycopy(deliverers, 0, employees, chefs.length + waiters.length, deliverers.length);

        Person highestSalaryEmployee = null;
        Person longestContractEmployee = null;
        BigDecimal highestSalary = BigDecimal.ZERO;
        LocalDate earliestStartDate = LocalDate.now();

        for (Person employee : employees) {
            Contract contract = null;

            if (employee instanceof Chef chef) {
                contract = chef.getContract();
            } else if (employee instanceof Waiter waiter) {
                contract = waiter.getContract();
            } else if (employee instanceof Deliverer deliverer) {
                contract = deliverer.getContract();
            }

            if (contract != null) {
                if (contract.getSalary().compareTo(highestSalary) > 0) {
                    highestSalary = contract.getSalary();
                    highestSalaryEmployee = employee;
                }
                if (contract.getStartDate().isBefore(earliestStartDate)) {
                    earliestStartDate = contract.getStartDate();
                    longestContractEmployee = employee;
                }
            }
        }

        System.out.println("Employee with the highest salary:");
        if (highestSalaryEmployee != null) {
            printEmployeeInfo(highestSalaryEmployee);
        }

        System.out.println("Employee with the longest contract:");
        if (longestContractEmployee != null) {
            printEmployeeInfo(longestContractEmployee);
        }
    }

    private static void printEmployeeInfo(Person employee) {
        System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName());

        switch (employee) {
            case Chef chef -> printContractInfo(chef.getContract());
            case Waiter waiter -> printContractInfo(waiter.getContract());
            case Deliverer deliverer -> printContractInfo(deliverer.getContract());
            default -> {
            }
        }
        System.out.println("---------------------------------");
    }

    private static void printContractInfo(Contract contract) {
        System.out.println("Salary: " + contract.getSalary());
        System.out.println("Contract Start Date: " + contract.getStartDate());
        System.out.println("Contract End Date: " + contract.getEndDate());
    }

    private static void analyzeMeals(Meal[] veganMeals, Meal[] vegetarianMeals, Meal[] meatMeals) {
        Meal[] allMeals = new Meal[veganMeals.length + vegetarianMeals.length + meatMeals.length];
        System.arraycopy(veganMeals, 0, allMeals, 0, veganMeals.length);
        System.arraycopy(vegetarianMeals, 0, allMeals, veganMeals.length, vegetarianMeals.length);
        System.arraycopy(meatMeals, 0, allMeals, veganMeals.length + vegetarianMeals.length, meatMeals.length);

        Meal highestCalorieMeal = allMeals[0];
        Meal lowestCalorieMeal = allMeals[0];

        for (Meal meal : allMeals) {
            if (meal.getTotalCalories().compareTo(highestCalorieMeal.getTotalCalories()) > 0) {
                highestCalorieMeal = meal;
            }
            if (meal.getTotalCalories().compareTo(lowestCalorieMeal.getTotalCalories()) < 0) {
                lowestCalorieMeal = meal;
            }
        }

        System.out.println("Meal with the highest calorie count:");
        printMealInfo(highestCalorieMeal);

        System.out.println("Meal with the lowest calorie count:");
        printMealInfo(lowestCalorieMeal);
    }

    private static void printMealInfo(Meal meal) {
        System.out.println("Name: " + meal.getName());
        System.out.println("Calories: " + meal.getTotalCalories());
        if (meal instanceof VeganMeal veganMeal) {
            System.out.println("Uses Organic Ingredients: " + veganMeal.isUsesOrganicIngredients());
        } else if (meal instanceof VegetarianMeal vegetarianMeal) {
            System.out.println("Includes Dairy: " + vegetarianMeal.isIncludesDairy());
        } else if (meal instanceof MeatMeal meatMeal) {
            System.out.println("Cut Type: " + meatMeal.getCutType());
        }
        System.out.println("---------------------------------");
    }

    private static Meal[] createVeganMeals(Scanner scanner, Category[] categories, Ingredient[] ingredients) {
        VeganMeal[] veganMeals = new VeganMeal[3];
        for (int i = 0; i < 3; i++) {
            System.out.printf("Enter vegan meal %d name: ", i + 1);
            String name = scanner.nextLine();
            int categoryIndex = getValidIndex(scanner, "vegan meal category index (0-2)", 0, 2);
            BigDecimal price = getValidBigDecimal(scanner, "vegan meal price");

            System.out.print("Does this vegan meal use organic ingredients? (true/false): ");
            boolean usesOrganicIngredients = Boolean.parseBoolean(scanner.nextLine());

            System.out.println("Select ingredients for this meal by entering their indices (separated by commas):");
            for (int j = 0; j < ingredients.length; j++) {
                System.out.printf("%d: %s%n", j, ingredients[j].getName());
            }

            String[] ingredientIndices = scanner.nextLine().split(",");
            Ingredient[] selectedIngredients = new Ingredient[ingredientIndices.length];

            for (int k = 0; k < ingredientIndices.length; k++) {
                int index = Integer.parseInt(ingredientIndices[k].trim());
                selectedIngredients[k] = ingredients[index];
            }

            veganMeals[i] = new VeganMeal((long)i, name, categories[categoryIndex], selectedIngredients, price, usesOrganicIngredients);
        }
        return veganMeals;
    }

    private static Meal[] createVegetarianMeals(Scanner scanner, Category[] categories, Ingredient[] ingredients) {
        VegetarianMeal[] vegetarianMeals = new VegetarianMeal[3];
        for (int i = 0; i < 3; i++) {
            System.out.printf("Enter vegetarian meal %d name: ", i + 1);
            String name = scanner.nextLine();
            int categoryIndex = getValidIndex(scanner, "vegetarian meal category index (0-2)", 0, 2);
            BigDecimal price = getValidBigDecimal(scanner, "vegetarian meal price");

            System.out.print("Does this vegetarian meal include dairy? (true/false): ");
            boolean includesDairy = Boolean.parseBoolean(scanner.nextLine());

            System.out.println("Select ingredients for this meal by entering their indices (separated by commas):");
            for (int j = 0; j < ingredients.length; j++) {
                System.out.printf("%d: %s%n", j, ingredients[j].getName());
            }

            String[] ingredientIndices = scanner.nextLine().split(",");
            Ingredient[] selectedIngredients = new Ingredient[ingredientIndices.length];

            for (int k = 0; k < ingredientIndices.length; k++) {
                int index = Integer.parseInt(ingredientIndices[k].trim());
                selectedIngredients[k] = ingredients[index];
            }

            vegetarianMeals[i] = new VegetarianMeal((long)i, name, categories[categoryIndex], selectedIngredients, price, includesDairy);
        }
        return vegetarianMeals;
    }


    private static Meal[] createMeatMeals(Scanner scanner, Category[] categories, Ingredient[] ingredients) {
        MeatMeal[] meatMeals = new MeatMeal[3];
        for (int i = 0; i < 3; i++) {
            System.out.printf("Enter meat meal %d name: ", i + 1);
            String name = scanner.nextLine();
            int categoryIndex = getValidIndex(scanner, "meat meal category index (0-2)", 0, 2);
            BigDecimal price = getValidBigDecimal(scanner, "meat meal price");

            System.out.print("Enter the cut type for this meat meal: ");
            String cutType = scanner.nextLine();

            System.out.println("Select ingredients for this meal by entering their indices (separated by commas):");
            for (int j = 0; j < ingredients.length; j++) {
                System.out.printf("%d: %s%n", j, ingredients[j].getName());
            }

            String[] ingredientIndices = scanner.nextLine().split(",");
            Ingredient[] selectedIngredients = new Ingredient[ingredientIndices.length];

            for (int k = 0; k < ingredientIndices.length; k++) {
                int index = Integer.parseInt(ingredientIndices[k].trim());
                selectedIngredients[k] = ingredients[index];
            }

            meatMeals[i] = new MeatMeal((long)i, name, categories[categoryIndex], selectedIngredients, price, cutType);
        }
        return meatMeals;
    }

}
