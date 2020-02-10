package edu.ithaca.dragon.bank;

public class SavingsAccount extends BankAccount {

    private double maxWithdraw = 500;
    private double interestRate = 0.015;

    public SavingsAccount(String email, String password, double startingBalance, boolean frozen) {
        super(email, password, startingBalance, frozen);
    }

    public double getMaxWithdrawal() {return maxWithdraw;}
    public double getInterestRate() {return interestRate;}
    public void setMaxWithdrawal(double maxWithdraw) {this.maxWithdraw = maxWithdraw;}
    public void setInterestRate(double interestRate) {this.interestRate = interestRate;}

    @Override
    public void withdraw(double amount) throws IllegalArgumentException, InsufficientFundsException, ExceedsMaxWithdrawalException {
        if (!isAmountValid(amount)) throw new IllegalArgumentException("Amount is not valid");
        if (amount > maxWithdraw) throw new ExceedsMaxWithdrawalException("Amount exceeds withdrawal allowance");
        if (amount > balance) throw new InsufficientFundsException("Not enough money in account");

        balance -= amount;
    }

    public void compoundInterest() {
        double interest = balance * interestRate;
        balance += interest;
    }

}
