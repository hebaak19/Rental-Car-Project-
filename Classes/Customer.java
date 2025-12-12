package Classes;

import java.time.LocalDate;
import Enum.PaymentMethod;
import UI.ErrorMessages;
import UI.Main;
import UI.Validation;

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

    public static void rentACar() {

        Customer currentCustomer = (Customer) Main.customer;
        if (currentCustomer.getCarRented() != null) {
            System.out.println("You already have an active rental (Car ID: " + currentCustomer.getCarRented() + ").");
            System.out.println("Please return your current car before renting another one.");
            return;
        }

        String carId = Validation.getValidatedInput(
                "Enter Car ID to rent:",
                Validation.carID,
                ErrorMessages.INVALID_CAR_ID);
        Car selectedCar = null;
        for (Car car : Main.carInventory) {
            if (car.getCarId().equals(carId) && car.isAvailable()) {
                selectedCar = car;
                break;
            }
        }
        if (selectedCar != null) {
            LocalDate startDate = Validation.validateDate();
            String periodInput = Validation.validateDays();
            Integer periodDays = Integer.parseInt(periodInput);
            while (periodDays <= 0) {
                System.out.println("Invalid number of days. Please enter a positive integer.");
                periodInput = Validation.validateDays();
                periodDays = Integer.parseInt(periodInput);
            }
            LocalDate endDate = startDate.plusDays(periodDays);
            double totalCost = periodDays * selectedCar.getPricePerDay();
            Main.rentalContracts.add(new RentalContract(carId, startDate, endDate, totalCost));
            selectedCar.setAvailable(false);

            currentCustomer.setCarRented(carId);

            PaymentMethod paymentMethod = Validation.vPaymentMethod();
            Payment payment = new Payment(carId, totalCost, LocalDate.now(), paymentMethod);
            payment.processPayment(paymentMethod);

            System.out.println("Review Your payment " + payment.toString());
        } else {
            System.out.println("Car not available for rent.");
        }
    }

    public static void returnACar() {
        Customer currentCustomer = (Customer) Main.customer;
        // check if he rented a car
        if (currentCustomer == null || currentCustomer.getCarRented() == null) {
            System.out.println("You have not rented a car.");
            return;
        }

        String carId = Validation.getValidatedInput(
                "Enter Car ID to return:",
                Validation.carID,
                ErrorMessages.INVALID_CAR_ID);

        Car returningCar = null;
        for (Car car : Main.carInventory) {
            // also check that the car he rented matches the car id
            if (car.getCarId().equals(carId) && !car.isAvailable() && carId.equals(currentCustomer.getCarRented())) {
                returningCar = car;
                break;
            }
        }

        if (returningCar != null) {
            returningCar.setAvailable(true);

            if (carId.equals(currentCustomer.getCarRented())) {
                currentCustomer.setCarRented(null);
            }

            Main.rentalContracts.removeIf(rc -> rc.getId().equals(carId));

            System.out.println("Car returned successfully! Contract closed.");
        } else {
            System.out.println("Invalid Car ID or Car is not rented.");
        }
    }
}
