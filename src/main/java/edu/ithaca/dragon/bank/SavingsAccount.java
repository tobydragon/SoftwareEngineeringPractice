package edu.ithaca.dragon.bank;

public class SavingsAccount extends Account {
    private double interestRate;
    private double maximumWithdrawal;


    public SavingsAccount(double startingBalance, double interestRate, double maximumWithdrawal, String id) {
        super(startingBalance, id);
        this.interestRate = interestRate;
        this.maximumWithdrawal = maximumWithdrawal;
    }

    public void calculateInterest() {

    }
}
