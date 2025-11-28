package UI;

import java.util.Scanner;
import Classes.Car;
import Classes.Customer;
import Classes.Employee;
import Classes.Person;
import Classes.RentalContract;
import Enum.Role;
import java.util.ArrayList;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    // implementing polymorphism
    static Person customer, employee;
    static public ArrayList<RentalContract> rentalContracts = new ArrayList<>();
    static public ArrayList<Car> carInventory = new ArrayList<>();
    static int periodDays;

    public static void main(String[] args) {
        CarActions.sampleCars();
        RentalContract.sampleRentalContracts();
        int choice;
        do {
            System.out.println(Menu.mainMenu);
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> employeeMenu();
                case 2 -> customerMenu();
                case 3 -> System.out.println("Exiting the program. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }

    public static void employeeMenu() {
        if (employee == null) {

            String name = Validation.getValidatedInput(
                    "Enter your name:",
                    Validation.nameRegex,
                    "Invalid name format. Name should contain only letters and spaces and be at least 2 characters long.");

            String id = Validation.getValidatedInput(
                    "Enter your ID:",
                    Validation.idRegex,
                    "Invalid ID format. ID should contain only digits and be at least 4 characters long.");

            Role role = Validation.getValidatedRole();

            String workEmail = Validation.getValidatedInput(
                    "Enter your work email:",
                    Validation.emailRegex,
                    "Invalid email format. Please enter a valid work email.");

            employee = new Employee(name, id, role, workEmail);
            System.out.println("Your Information: " + employee.toString());
        }

        int choice;
        do {
            System.out.println(Menu.employeeMenu);
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> EmployeeSevices.adminMenu();
                case 2 -> EmployeeSevices.manegerMenu();
                case 3 -> EmployeeSevices.salesmanMenu();
                case 4 -> EmployeeSevices.customerServiceMenu();
                case 5 -> {
                    System.out.println("Exiting Employee Menu");
                    employee = null;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);
    }

    public static void customerMenu() {
        if (customer == null) {
            String name = Validation.getValidatedInput(
                    "Enter your name to continue as Customer:",
                    Validation.nameRegex,
                    "Invalid name format. Name should contain only letters and spaces and be at least 2 characters long.");

            String id = Validation.getValidatedInput(
                    "Enter your ID to continue as Customer:",
                    Validation.idRegex,
                    "Invalid ID format. ID should contain only digits and be at least 4 characters long.");

            String phoneNumber = Validation.getValidatedInput(
                    "Enter your phone number to continue as Customer:",
                    Validation.phoneRegex,
                    "Invalid phone number format. Phone number should contain exactly 10 digits.");
            String license = Validation.getValidatedInput(
                    "Enter your driver's license plate to continue as Customer:",
                    Validation.licensePlateRegex,
                    "Invalid license plate format. License plate should contain only uppercase letters, digits, and hyphens, and be up to 10 characters long.");
            customer = new Customer(name, id, phoneNumber, license);
            System.out.println("Your Information, " + customer.toString() + "!");
        }
        System.out.println("Customer Menu");
        int choice;
        do {
            System.out.println(Menu.customerMenu);
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> CarActions.viewAvailableCars();
                case 2 -> RentalService.rentACar();
                case 3 -> RentalService.returnACar();
                case 4 -> {
                    System.out.println("Exiting Customer Menu");
                    customer = null;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

}