package edu.ithaca.dragon.bank;

import java.util.Collection;

public abstract class Account {
    private double balance;
    private boolean isFrozen;
    private Collection<User> users;

    public Account(double startingBalance) {
        if (!isAmountValid(startingBalance)) {
            throw new IllegalArgumentException("Starting balance: " + startingBalance + " is invalid, cannot create account");
        } else {
            this.balance = startingBalance;
        }
    }

    public void withdraw(double amount) {

    }

    public void deposit(double amount) {

    }

    public void transfer(Account toAccount, double amount) {

    }

    /**
     * returns true if the amount is non-negative and has two decimal points or less, and false otherwise
     * @param amount quantity to check
     * @return true if the amount is non-negative and has two decimal points or less, and false otherwise
     */
    public static boolean isAmountValid(double amount) {
        String amountStr = String.valueOf(amount);
        int charsAfterDec = amountStr.length() - amountStr.indexOf('.') - 1;
        return amount >= 0 && charsAfterDec <= 2;
    }

    public String getCredentials() {
        return "";
    }

    public double getBalance() {
        return balance;
    }

    public String getHistory() {
        return "";
    }
}
