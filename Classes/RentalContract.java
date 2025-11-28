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
        Main.rentalContracts.add(new RentalContract("1122", LocalDate.of(2025, 12, 1), LocalDate.of(2025, 12, 22), 500.0));
        Main.rentalContracts.add(new RentalContract("3344", LocalDate.of(2025, 12, 1), LocalDate.of(2025, 12, 23), 270.0));
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

    public double calculateTotalCost(double cost) {
        return 0.0;
    }

    @Override
    public String toString() {
        return "RentalContract [contractId=" + contractId + ", startDate=" + startDate + ", endDate=" + endDate
                + ", totalCost=" + totalCost + "]";
    }
    
}