package UI;

import java.util.Scanner;

import Enum.Role;
import Enum.PaymentMethod;

public class Validation {
    static Scanner scanner = new Scanner(System.in);
    static public String nameRegex = "^[A-Za-z ]{2,}$";
    static public String idRegex = "^[0-9]{4,}$";
    static public String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    static public String phoneRegex = "^[0-9]{10}$";
    static public String licensePlateRegex = "^[0-9]{10}$";
    static public String carID = "^[0-9]{4,}$";
    static public String contractId = "^[0-9]{4,}$";
    static public String creditCardNumber = "^[0-9]{10}$";

    // implementing method overloading
    public static Role getValidatedInput() {
        while (true) {
            System.out.println("Enter your role (Admin, Manager, Salesman, CustomerService):");
            String roleInput = scanner.nextLine();

            try {
                return Role.valueOf(roleInput.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid role. Please choose one of: Admin, Manager, Salesman, CustomerService.");
            }
        }
    }

    public static PaymentMethod vPaymentMethod() {
        while (true) {
            System.out.println("Enter your payment method (credit_card, bank_transfer, cash):");
            String paymentInput = scanner.nextLine();

            try {
                return PaymentMethod.valueOf(paymentInput.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid payment method, please choose from the following above.");
            }
        }
    }

    public static String getValidatedInput(String message, String regex, String errorMessage) {
        String input;

        while (true) {
            System.out.println(message);
            input = scanner.nextLine();

            if (input.matches(regex)) {
                return input;
            }

            System.out.println(errorMessage);
        }
    }

}
