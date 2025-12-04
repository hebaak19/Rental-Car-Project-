package Classes;
import java.time.LocalDate;
import java.util.Scanner;

import Interfaces.Payable;
import UI.EmployeeSevices;
import UI.Main;
import UI.Validation;

public class Payment implements Payable {
    static Scanner scanner = new Scanner(System.in);
    private String paymentId;
    private double amount;
    private LocalDate date;
    private String paymentMethod;

    @Override
    public String toString() {
        return "Payment [paymentId: " + paymentId + ", amount: " + amount + " SAR " + ", Starting date: " + date + ", paymentMethod:"
                + paymentMethod + "]";
    }

    public Payment(String paymentId, double amount, LocalDate date, String paymentMethod) {
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void processPayment(double amount) {

    }

    public void refund() {
        EmployeeSevices.viewActiveContracts();
        String contractId = Validation.getValidatedInput(
                "Enter Contract ID to process refund:",
                Validation.contractId,
                "Invalid Contract ID format. ID should contain only digits and be at least 4 characters long.");
        for (RentalContract rc : Main.rentalContracts) {
            if (rc.getId() == contractId) {
                System.out
                        .println("Processing refund for Contract ID: " + contractId + ", Amount: " + rc.getTotalCost());
                return;
            }
        }
    }
}
