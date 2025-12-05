package Classes;

public class Customer extends Person {
    private String phoneNumber;
    private String license;

    public Customer(String name, String id, String phoneNumber, String license) {
        super(name, id);
        this.phoneNumber = phoneNumber;
        this.license = license;
    }

    @Override
    public String toString() {
        return "Customer" + super.toString() + " phoneNumber: " + phoneNumber + ", license: " + license ;
    }

    public Customer() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
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
