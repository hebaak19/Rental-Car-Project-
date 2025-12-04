package UI;

import java.util.Scanner;

import Classes.Car;

public class CarActions {
    static Scanner scanner = new Scanner(System.in);

    public static void sampleCars() {
        Main.carInventory.add(new Car("1122", "Toyota", "2020", 100, true));
        Main.carInventory.add(new Car("3344", "Honda", "2019", 90, true));
        Main.carInventory.add(new Car("5566", "Ford", "2021", 110, true));
    }

    public static void AddCar() {

        System.out.println("Add Car");
        String id = Validation.getValidatedInput(
                "Enter Car ID:",
                Validation.carID,
                "Invalid Car ID format. ID should contain only digits and be at least 4 characters long.");

        if (Main.carInventory.stream().anyMatch(car -> car.getCarId().equals(id))) {
            System.out.println("Car ID already exists. Cannot add duplicate ID.");
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
