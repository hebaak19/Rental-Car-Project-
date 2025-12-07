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

    static Person customer, employee;
    static public ArrayList<RentalContract> rentalContracts = new ArrayList<>();
    static public ArrayList<Car> carInventory = new ArrayList<>();
    static public ArrayList<Employee> employees = new ArrayList<>();
    static public ArrayList<Customer> customers = new ArrayList<>();
    static int periodDays;

    public static void main(String[] args) {
        CarActions.sampleCars();
        RentalContract.sampleRentalContracts();

        EmployeeSevices.emploeesData();
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

        String name = Validation.getValidatedInput(
                "Enter your name:",
                Validation.nameRegex,
                ErrorMessages.INVALID_NAME);

        String id = Validation.getValidatedInput(
                "Enter your ID:",
                Validation.idRegex,
                ErrorMessages.INVALID_ID);

        Role role = Validation.getValidatedInput();

        String workEmail = Validation.getValidatedInput(
                "Enter your work email:",
                Validation.emailRegex,
                ErrorMessages.INVALID_EMAIL);

        Employee matched = null;
        for (int i = 0; i < employees.size(); i++) {
            Employee e = employees.get(i);
            if (e.getId().equals(id) && e.getName().equalsIgnoreCase(name) && e.getRole() == role
                    && e.getWorkEmail().equals(workEmail)) {
                matched = e;
                break;
            }
        }

        if (matched != null) {
            employee = matched;
            System.out.println("Welcome back, " + employee.getName());
            System.out.println("Choose your action from the Menu:");
            if (((Employee) employee).getRole() == Role.ADMIN) {
                EmployeeSevices.adminMenu();
            } else if (((Employee) employee).getRole() == Role.MANAGER) {
                EmployeeSevices.manegerMenu();
            } else if (((Employee) employee).getRole() == Role.SALESMAN) {
                EmployeeSevices.salesmanMenu();
            } else if (((Employee) employee).getRole() == Role.CUSTOMERSERVICE) {
                EmployeeSevices.customerServiceMenu();
            }
        } else {
            System.out.println(ErrorMessages.NO_EMPLOYEES);
            return;
        }

    }

    public static void customerMenu() {
        if (customer == null) {
            String name = Validation.getValidatedInput(
                    "Enter your name to continue as Customer:",
                    Validation.nameRegex,
                    ErrorMessages.INVALID_NAME);

            String id = Validation.getValidatedInput(
                    "Enter your ID to continue as Customer:",
                    Validation.idRegex,
                    ErrorMessages.INVALID_ID);
            if (customers.stream().anyMatch(c -> c.getId().equals(id))) {
                System.out.println("Welcome back, " + name + "!");
                System.out.println("Proceeding to Customer Menu...");
                customerServices();
                return;
            }
            String phoneNumber = Validation.getValidatedInput(
                    "Enter your phone number to continue as Customer:",
                    Validation.phoneRegex,
                    ErrorMessages.INVALID_PHONE_NUMBER);
            String license = Validation.getValidatedInput(
                    "Enter your driver's license plate to continue as Customer:",
                    Validation.licensePlateRegex,
                    ErrorMessages.INVALID_LICENSE_PLATE);
            customer = new Customer(name, id, phoneNumber, license);

            customers.add((Customer) customer);
            System.out.println("Your Information, " + customer.toString() + "!");
            customerServices();
        }

    }

    public static void customerServices() {
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
                case 4 -> RentalService.customerRefund();
                case 5 -> {
                    System.out.println("Exiting Customer Menu");
                    customer = null;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
}