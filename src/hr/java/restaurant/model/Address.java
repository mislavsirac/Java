package hr.java.restaurant.model;

public class Address {
    String Street;
    String HouseNumber;
    String City;
    String PostalCode;

    public Address(String street, String houseNumber, String city, String postalCode) {
        Street = street;
        HouseNumber = houseNumber;
        City = city;
        PostalCode = postalCode;
    }

    public Address() {
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getHouseNumber() {
        return HouseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        HouseNumber = houseNumber;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }
}
