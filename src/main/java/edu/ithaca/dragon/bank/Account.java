package edu.ithaca.dragon.bank;

import java.util.Collection;
import java.util.regex.Pattern;

public abstract class Account {
    private double balance;
    private boolean isFrozen;
    private Collection<User> users;


    public Account(double startingBalance) {
        if (!isAmountValid(startingBalance)) {
            throw new IllegalArgumentException("Starting balance: " + startingBalance + " is invalid, cannot create account");
        } else {
            this.balance = startingBalance;
            this.isFrozen = false;
        }
    }


    public void withdraw(double amount) {

    }

    /**
     * increases the balance by amount if non-negative and has 2 or fewer decimals
     * @param amount quantity to increase balance by
     * @throws IllegalArgumentException if amount is negative or has more than 2 decimal places
     */
    public void deposit(double amount) {

        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("Amount: " + amount + " is invalid, cannot deposit");
        } else {
            balance += amount;
        }
    }

    public void transfer(Account toAccount, double amount) {

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

    public boolean getFrozenStatus(){
        return isFrozen;
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
}
