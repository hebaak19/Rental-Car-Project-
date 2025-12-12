package Classes;

import java.time.LocalDate;

import UI.Main;

public class RentalContract {
    private String contractId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalCost;
    private boolean isActive;
    public static double totalSales = 0;

    public RentalContract(String contractId, LocalDate startDate, LocalDate endDate, double totalCost) {
        this.contractId = contractId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
        this.isActive = true;
        calculateTotalCost(totalCost);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public static void sampleRentalContracts() {

    }

    public String getId() {
        return contractId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int calculatePeriod(LocalDate startDate, LocalDate endDate) {
        return endDate.getDayOfYear() - startDate.getDayOfYear();
    }

    public static double calculateTotalCost(double cost) {
        totalSales += cost;
        return totalSales;

    }

    @Override
    public String toString() {
        return "Contract ID: " + contractId + ", Start Date: " + startDate + ", End Date: " + endDate
                + ", Total Cost: " + totalCost + " SAR";
    }

    public static void viewActiveContracts() {
        boolean found = false;
        System.out.println("Active Rental Contracts:");
        for (RentalContract rc : Main.rentalContracts) {
            if (!rc.getEndDate().isBefore(LocalDate.now()) && rc.isActive()) {
                System.out.println(rc.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No active rental contracts found.");
        }
    }

}