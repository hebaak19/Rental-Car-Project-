package Classes;

import java.time.LocalDate;

import UI.Main;

public class RentalContract {
    private String contractId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalCost;

    public RentalContract(String contractId, LocalDate startDate, LocalDate endDate, double totalCost) {
        this.contractId = contractId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
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

    @Override
    public String toString() {
        return "Contract ID: " + contractId + ", Start Date: " + startDate + ", End Date: " + endDate
                + ", Total Cost: " + totalCost + " SAR";
    }

}