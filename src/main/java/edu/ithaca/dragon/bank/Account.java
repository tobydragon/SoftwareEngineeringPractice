package edu.ithaca.dragon.bank;

import java.util.Collection;

public abstract class Account {
    private double balance;
    private boolean isFrozen;
    private Collection<User> users;


    public void withdraw(double amount) {

    }

    public void deposit(double amount) {

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
