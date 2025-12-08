
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
            PaymentMethod paymentMethod = Validation.vPaymentMethod();
            Payment payment = new Payment(carId, totalCost, LocalDate.now(), paymentMethod);
            payment.processPayment(paymentMethod);

            for (Classes.Customer customer : Main.customers) {
                if (customer.getCarRented() == null) {
                    customer.setCarRented(carId);
                    break;
                }
            }

            System.out.println("Review Your payment " + payment.toString());
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
            if (car.getCarId().equals(carId) && !car.isAvailable()) {
                returningCar = car;
                break;
            }
        }
        if (returningCar != null) {
            returningCar.setAvailable(true);

            System.out.println("Car returned successfully!");

        } else {
            System.out.println("Invaild Car ID or Car already exsit.");
        }
    }

    public static void customerRefund() {

        String carId = Validation.getValidatedInput(
                "Enter Car ID for refund:",
                Validation.carID,
                ErrorMessages.INVALID_CAR_ID);
        RentalContract contractToRefund = null;
        for (RentalContract contract : Main.rentalContracts) {
            if (contract.getId().equals(carId) && contract.getEndDate().isAfter(LocalDate.now())
                    && contract.isActive()) {
                contractToRefund = contract;
                break;
            }
        }

        if (contractToRefund != null) {
            PaymentMethod method = Validation.vPaymentMethod();
            System.out
                    .println("Processing refund for Car ID: " + carId + ", Amount: " + contractToRefund.getTotalCost()
                            + " via " + method);
            contractToRefund.setActive(false);
        } else {
            System.out.println("No contract found with Car ID: " + carId);
        }
    }
}
