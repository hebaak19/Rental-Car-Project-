//todo implement payment interface
package UI;

import java.time.LocalDate;
import java.util.Scanner;
import Enum.PaymentMethod;
import Classes.Car;
import Classes.Payment;
import Classes.RentalContract;

public class RentalService {
    static Scanner scanner = new Scanner(System.in);
    static int periodDays;

    public static void rentACar() {
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
            System.out.println("Enter rental period in days:");
            periodDays = scanner.nextInt();
            scanner.nextLine(); // consume newline
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = startDate.plusDays(periodDays);
            double totalCost = periodDays * selectedCar.getPricePerDay();
            Main.rentalContracts.add(new RentalContract(carId, startDate, endDate, totalCost));
            selectedCar.setAvailable(false);
            PaymentMethod paymentMethod = Validation.vPaymentMethod();
            Payment payment = new Payment(carId, totalCost, LocalDate.now(), paymentMethod);
            System.out.println("Car rented successfully! Review Your payment " + payment.toString());
        } else {
            System.out.println("Car not available for rent.");
        }
    }

    public static void returnACar() {
        String carId = Validation.getValidatedInput(
                "Enter Car ID to return:",
                Validation.carID,
                ErrorMessages.INVALID_CAR_ID);
        Car returningCar = null;
        for (Car car : Main.carInventory) {
            if (car.getCarId() == carId && !car.isAvailable()) {
                returningCar = car;
                break;
            }
        }
        if (returningCar != null) {
            returningCar.setAvailable(true);
            System.out.println("Car returned successfully!");
        } else {
            System.out.println("Invalid Car ID or Car is not rented.");
        }
    }
}
