package Classes;

public class Customer extends Person {
    private String phoneNumber;
    private String license;
    private String carRented;

    public Customer(String name, String id, String phoneNumber, String license) {
        super(name, id);
        this.phoneNumber = phoneNumber;
        this.license = license;
    }

    @Override
    public String toString() {
        return "Customer" + super.toString() + " phoneNumber: " + phoneNumber + ", license: " + license;
    }

    public Customer() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCarRented() {
        return carRented;
    }

    public void setCarRented(String carRented) {
        this.carRented = carRented;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

}
