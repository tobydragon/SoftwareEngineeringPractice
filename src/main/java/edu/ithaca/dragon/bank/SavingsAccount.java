package edu.ithaca.dragon.bank;

public class SavingsAccount extends BankAccount {
    public SavingsAccount(double startingBalance, String acctId){
        super(startingBalance, acctId);
    }

    private double interestRate;
    private double maximumWithdrawal;


    public SavingsAccount(double startingBalance, String acctId, double interestRate, double maximumWithdrawal) {
        super(startingBalance,acctId);
        this.interestRate = interestRate;
        this.maximumWithdrawal = maximumWithdrawal;
    }
}
