package edu.ithaca.dragon.bank;

import java.util.Collection;

public abstract class Account {
    private double balance;
    private boolean isFrozen;
    private Collection<User> users;


    public void withdraw(double amount) {

    }

    public void deposit(double amount) {
        String amountStr = String.valueOf(amount);
        int charsAfterDec = amountStr.length() - amountStr.indexOf('.') - 1;
        if (amount < 0){
            throw new IllegalArgumentException("Cannot deposit negative amount");
        }
        else if (charsAfterDec > 2){
            throw new IllegalArgumentException("Too many decimal places");
        }
        else{
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
}
