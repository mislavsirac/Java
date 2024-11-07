package hr.java.restaurant.model;

public abstract class Person extends Entity{
    String FirstName;
    String LastName;

    public Person(Long id, String firstName, String lastName) {
        super(id);
        FirstName = firstName;
        LastName = lastName;
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
}
