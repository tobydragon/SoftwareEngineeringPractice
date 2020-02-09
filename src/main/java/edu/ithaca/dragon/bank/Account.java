package edu.ithaca.dragon.bank;

import java.util.Collection;
import java.util.regex.Pattern;

public abstract class Account {
    protected double balance;
    protected boolean isFrozen;
    protected Collection<User> users;
    protected String id;


    public Account(double startingBalance, String id) {
        if (!isAmountValid(startingBalance)) {
            throw new IllegalArgumentException("Starting balance: " + startingBalance + " is invalid, cannot create account");
        }
        else if (id.equals("")){
            throw new IllegalArgumentException("ID cannot be an empty string");
        }
        else {
            this.balance = startingBalance;
            this.isFrozen = false;
            this.id = id;
        }
    }

    /**
     * reduces the balance by amount if amount is non-negative, has 2 or fewer decimals and is smaller or equal to balance
     * @param amount quantity to reduce balance by
     * @throws IllegalArgumentException if amount is negative or has more than 2 decimals
     * @throws InsufficientFundsException if amount is larger than balance
     */
    public void withdraw(double amount) throws InsufficientFundsException{
        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("Amount: " + amount + " is invalid, cannot withdraw");
        } else if (amount > balance) {
            throw new InsufficientFundsException("Not enough money");
        } else {
            balance -= amount;
        }
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

    /**
     * withdraws amount from balance and deposits it into to's balance
     * @param toAccount BankAccount who's balance will be deposited into
     * @param amount quantity to withdraw from balance and deposit into to's balance
     * @throws IllegalArgumentException if amount is negative or has more than 2 decimals
     * @throws InsufficientFundsException if amount is larger than balance
     */
    public void transfer(Account toAccount, double amount) throws InsufficientFundsException {
        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("Amount: " + amount + " is invalid, cannot transfer");
        } else if (amount > balance) {
            throw new InsufficientFundsException("Not enough money");
        } else {
            withdraw(amount);
            toAccount.deposit(amount);
        }
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

    public  void setFrozen(boolean frozenStatus){
        isFrozen = frozenStatus;
    }

    public String getID(){
        return id;
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
