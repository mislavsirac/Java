package hr.java.restaurant.model;

import java.math.BigDecimal;

public class Waiter{
    String FirstName;
    String LastName;
    BigDecimal Salary;

    public Waiter(String firstName, String lastName, BigDecimal salary) {
        FirstName = firstName;
        LastName = lastName;
        Salary = salary;
    }

    public Waiter() {
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public BigDecimal getSalary() {
        return Salary;
    }

    public void setSalary(BigDecimal salary) {
        Salary = salary;
    }
}
