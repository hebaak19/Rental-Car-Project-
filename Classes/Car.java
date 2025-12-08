package Classes;

import java.util.Scanner;

import UI.ErrorMessages;
import UI.Main;
import UI.Validation;

public class Car {
    static Scanner scanner = new Scanner(System.in);
    private String carId;
    private String brand;
    private String model;
    private double pricePerDay;
    private boolean isAvailable;

    public Car(String carId, String brand, String model, double pricePerDay, boolean isAvailable) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.isAvailable = isAvailable;
    }

    // sample cars
    public static void sampleCars() {
        Main.carInventory.add(new Car("1122", "Toyota", "2020", 100, true));
        Main.carInventory.add(new Car("3344", "Honda", "2019", 90, true));
        Main.carInventory.add(new Car("5566", "Ford", "2021", 110, true));
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "Car [carId = " + carId + ", brand = " + brand + ", model = " + model + ", pricePerDay = " + pricePerDay
                + " SAR " + ", isAvailable= " + isAvailable + "]";
    }

    // Car Methods
    public static void AddCar() {

        System.out.println("Add Car");
        String id = Validation.getValidatedInput(
                "Enter Car ID:",
                Validation.carID,
                ErrorMessages.INVALID_CAR_ID);

        if (Main.carInventory.stream().anyMatch(car -> car.getCarId().equals(id))) {
            System.out.println(ErrorMessages.CAR_ID_EXISTS);
            return;
        }
        System.out.println("Enter Car Make:");
        String make = scanner.nextLine();
        System.out.println("Enter Car Model:");
        String model = scanner.nextLine();
        System.out.println("Enter Car Price per Day:");
        double pricePerDay = scanner.nextDouble();
        scanner.nextLine();
        Main.carInventory.add(new Car(id, make, model, pricePerDay, true));
        System.out.println("Car added successfully!");
    }

    public static void removeCar() {
        System.out.println("Remove Car");
        String removeId = Validation.getValidatedInput(
                "Enter Car ID to remove:",
                Validation.carID,
                ErrorMessages.INVALID_CAR_ID);
        Main.carInventory.removeIf(car -> car.getCarId().equals(removeId));
        System.out.println("Car removed successfully!");
    }

    public static void viewAvailableCars() {
        System.out.println("\n========== Available Cars ==========");
        boolean found = false;

        for (Car car : Main.carInventory) {
            if (car.isAvailable()) {
                System.out.println(car.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No cars available at the moment.");
        }
        System.out.println("====================================\n");
    }

}
