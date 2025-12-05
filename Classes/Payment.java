package Classes;

import java.time.LocalDate;
import java.util.Scanner;
import Enum.PaymentMethod;
import Interfaces.Payable;
import UI.EmployeeSevices;
import UI.Main;
import UI.Validation;
import UI.ErrorMessages;

// we need to implement different payment methods using enum
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

    public void processPayment(PaymentMethod method) {
        if (method == PaymentMethod.CREDIT_CARD) {
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

    public void refund() {
        EmployeeSevices.viewActiveContracts();
        String contractId = Validation.getValidatedInput(
                "Enter Contract ID to process refund:",
                Validation.contractId,
                ErrorMessages.INVALID_CONTRACT_ID);
        for (RentalContract rc : Main.rentalContracts) {
            if (rc.getId() == contractId) {
                System.out
                        .println("Processing refund for Contract ID: " + contractId + ", Amount: " + rc.getTotalCost());
                // SUBSTRACT REFUND LOGIC HERE
                // the calculate amount should decrease from total payments
                
                return;
            }
        }
    }
}
