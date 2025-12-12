package Classes;
// Handles dates for payment transactions
import java.time.LocalDate; 
import java.util.Scanner;
// Enum that defines available payment methods (CASH, BANK_TRANSFER, CREDIT_CARD)
import Enum.PaymentMethod; 
// Interface that enforces payment and refund methods
import Interfaces.Payable; 
// Access to shared system data (cars, customers, contracts)
import UI.Main; 
// Used for input validation 
import UI.Validation; 
// Contains predefined error messages
import UI.ErrorMessages; 

// Handles all payment and refund operations in the car rental system
public class Payment implements Payable {
    static Scanner scanner = new Scanner(System.in);
    private String paymentId; 
    private double amount; 
    private LocalDate date; 
    private PaymentMethod paymentMethod; 

    @Override
    public String toString() {
        return "Payment [paymentId: " + paymentId + ", amount: " + amount + " SAR " + ", Starting date: " + date
                + ", paymentMethod:"
                + paymentMethod + "]";
    } 

    public Payment(String paymentId, double amount, LocalDate date, PaymentMethod paymentMethod) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.date = date;
        this.paymentMethod = paymentMethod;
    } 
    
    public Payment() {
    } 
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
 * Processes a payment using the selected payment method.
 */
    public void processPayment(PaymentMethod method) {
        // Credit card payment flow
        if (method == PaymentMethod.CREDIT_CARD) {
           // Validate credit card number before processing
            Validation.getValidatedInput(
                    "Enter Credit Card Number (should be 10 digits):",
                    Validation.creditCardNumber,
                    ErrorMessages.INVALID_CARD_NUMBER);
            System.out.println("Processing credit card payment of amount: " + amount);
        } else if (method == PaymentMethod.BANK_TRANSFER) {
            System.out.println("You can transfer the amount: " + amount + " to our bank account SA10998264511100.");
        } else if (method == PaymentMethod.CASH) {
            System.out.println("Please pay the amount: " + amount + " in cash at office number A15.");
        } else {
            System.out.println("Invalid payment method.");
        }
    }
   // Refund operation handled by employees (Customer Service / Manager)
    public void refund() {
        // Display all currently active contracts
        RentalContract.viewActiveContracts();
        // Ask employee to enter contract ID
        String contractId = Validation.getValidatedInput(
                "Enter Contract ID to process refund:",
                Validation.contractId,
                ErrorMessages.INVALID_CONTRACT_ID);
        // Search for the active contract
        RentalContract contractToRefund = null;
        for (RentalContract rc : Main.rentalContracts) {
            if (rc.getId().equals(contractId) && rc.isActive()) {
                contractToRefund = rc;
                break;
            }
        }
        // If a valid active contract is found
        if (contractToRefund != null) {
            PaymentMethod method = Validation.vPaymentMethod();
            System.out.println("Processing refund for Contract ID: " + contractId + ", Amount: "
                    + contractToRefund.getTotalCost() + " via " + method);
                 // Close the contract
                 contractToRefund.setActive(false);
            // Make the car available again
            String carId = contractToRefund.getId();
            for (Car car : Main.carInventory) {
                if (car.getCarId().equals(carId)) {
                    car.setAvailable(true);
                    break;
                }
            }
            // Remove rented car from customer record
            for (Customer c : Main.customers) {
                if (carId.equals(c.getCarRented())) {
                    c.setCarRented(null);
                    break;
                }
            }

            System.out.println("Refund processed successfully. Car is now available.");
        } else {
            System.out.println("No active contract found with ID: " + contractId);
        }
    }
    // Refund operation requested directly by the logged-in customer
    public static void customerRefund() {
        Customer currentCustomer = (Customer) Main.customer;
        if (currentCustomer == null) {
            System.out.println("No customer is logged in.");
            return;
        }
        // Check if customer has rented a car
        if (currentCustomer.getCarRented() == null) {
            System.out.println("You haven't rented any car. Refund not applicable.");
            return;
        }
        // Ask for car ID
        String carId = Validation.getValidatedInput(
                "Enter Car ID for refund:",
                Validation.carID,
                ErrorMessages.INVALID_CAR_ID);
        // Ensure refund is for the customer's own rented car
        if (!carId.equals(currentCustomer.getCarRented())) {
            System.out.println("You can only request a refund for the car you rented (Car ID: "
                    + currentCustomer.getCarRented() + ").");
            return;
        }

        RentalContract contractToRefund = null;
        // Find the active rental contract
        for (RentalContract contract : Main.rentalContracts) {
            if (carId.equals(contract.getId()) && contract.isActive()) {
                contractToRefund = contract;
                break;
            }
        }
        // If contract exists
        if (contractToRefund != null) {
            PaymentMethod method = Validation.vPaymentMethod();
            System.out.println("Processing refund for Car ID: " + carId + ", Amount: " + contractToRefund.getTotalCost()
                    + " via " + method);

            // Close the contract
            contractToRefund.setActive(false);
            // Set car back to available
            for (Car car : Main.carInventory) {
                if (car.getCarId().equals(carId)) {
                    car.setAvailable(true);
                    break;
                }
            }
            // Remove car from customer account
            if (carId.equals(currentCustomer.getCarRented())) {
                currentCustomer.setCarRented(null);
            }

            System.out.println("Refund processed successfully. Car is now available.");
        } else {
            System.out.println("No active contract found with Car ID: " + carId);
        }
    }

}
