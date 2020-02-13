package edu.ithaca.dragon.bank;

public class SavingsAccount extends Account {
    private double interestRate;
    private double maximumWithdrawal;


    public SavingsAccount(double startingBalance, double interestRate, double maximumWithdrawal, String id) {
        super(startingBalance, id);
        this.interestRate = interestRate;
        this.maximumWithdrawal = maximumWithdrawal;
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException, AccountFrozenException {
        if(amount <= maximumWithdrawal) {
            super.withdraw(amount);
        } else {
            throw new IllegalArgumentException("Amount is greater than maximum withdrawal amount");
        }
    }

    public void calculateInterest() {
        balance += balance * interestRate;
    }
}
