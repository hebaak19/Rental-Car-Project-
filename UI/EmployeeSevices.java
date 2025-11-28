package UI;
import java.time.LocalDate;
import java.util.Scanner;

import Classes.Car;
import Classes.Payment;
import Classes.RentalContract;

public class EmployeeSevices {
    static Scanner scanner = new Scanner(System.in);

    public static void adminMenu() {

        int choice;
        do {
            System.out.println(Menu.adminMenu);
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    CarActions.AddCar();
                    break;
                case 2:
                    CarActions.removeCar();
                    break;
                case 3:
                    System.out.println("View All Cars");
                    for (Car car : Main.carInventory) {
                        System.out.println(car);
                    }
                    break;
                case 4:
                    System.out.println("Exiting Admin Menu");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

    }

    public static void manegerMenu() {

        int choice;
        do {
            System.out.println(Menu.managerMenu);
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Viewing Rental contracts...");
                    for (RentalContract rc : Main.rentalContracts) {
                        System.out.println("Contract ID: " + rc.getId() + ", Start Date: " + rc.getStartDate()
                                + ", End Date: " + rc.getEndDate() + ", Total Cost: " + rc.getTotalCost());
                    }
                    break;
                case 2:
                    System.out.println("Exiting Manager Menu");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 2);
    }

    public static void salesmanMenu() {
        int choice;
        do {
            System.out.println(Menu.salesmanMenu);
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> {
                    System.out.println("Total sales = " + calculateTotalCost());
                }

                case 2 -> System.out.println("Exiting Salesman Menu");

                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 2);
    }

    public static void customerServiceMenu() {
        int choice;
        do {
            System.out.println(Menu.customerServiceMenu);
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> EmployeeSevices.viewActiveContracts();
                case 2 -> {
                    Payment payment = new Payment();
                    payment.refund();
                }
                case 3 -> System.out.println("Exiting Customer Service Menu");
                default ->
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 2);
    }

    public static double calculateTotalCost() {
        double sum = 0;
        if (!Main.rentalContracts.isEmpty()) {
            for (RentalContract rc : Main.rentalContracts) {
                sum += rc.getTotalCost();

            }
            return sum;
        }
        return 0.0;
    }

    public static void viewActiveContracts() {
        boolean found = false;
        System.out.println("Active Rental Contracts:");
        for (RentalContract rc : Main.rentalContracts) {
            if (!rc.getEndDate().isBefore(LocalDate.now())) {
                System.out.println(rc.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No active rental contracts found.");
        }
    }
}
