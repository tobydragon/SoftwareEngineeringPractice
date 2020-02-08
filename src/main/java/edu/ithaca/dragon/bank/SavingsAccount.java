package edu.ithaca.dragon.bank;

public class SavingsAccount extends BankAccount {

    private double maxWithdraw = 500;
    private double interestRate = 0.015;

    public SavingsAccount(String email, String password, double startingBalance) {
        super(email, password, startingBalance);
    }

    public double getMaxWithdrawal() {return maxWithdraw;}
    public double getInterestRate() {return interestRate;}
    public void setMaxWithdrawal(double maxWithdraw) {this.maxWithdraw = maxWithdraw;}
    public void setInterestRate(double interestRate) {this.interestRate = interestRate;}

    @Override
    public void withdraw(double amount) throws IllegalArgumentException, InsufficientFundsException, ExceedsMaxWithdrawalException {

    }

    public void compoundInterest() {

    }

}
