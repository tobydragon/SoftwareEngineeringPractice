package edu.ithaca.dragon.bank;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid, or startingBalance is negative or has more than 2 decimal places
     */
    public BankAccount(String email, double startingBalance) {
        if (!isEmailValid(email)) {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        } else if (!isAmountValid(startingBalance)) {
            throw new IllegalArgumentException("Starting balance: " + startingBalance + " is invalid, cannot create account");
        } else {
            this.email = email;
            this.balance = startingBalance;
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * reduces the balance by amount if amount is non-negative, has 2 or fewer decimals and is smaller or equal to balance
     * @param amount quantity to reduce balance by
     * @throws IllegalArgumentException if amount is negative or has more than 2 decimals
     * @throws InsufficientFundsException if amount is larger than balance
     */
    public void withdraw(double amount) throws InsufficientFundsException {
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
     * @param to BankAccount who's balance will be deposited into
     * @param amount quantity to withdraw from balance and deposit into to's balance
     * @throws IllegalArgumentException if amount is negative or has more than 2 decimals
     * @throws InsufficientFundsException if amount is larger than balance
     */
    public void transfer(BankAccount to, double amount) throws InsufficientFundsException {

    }

    public static boolean isEmailValid(String email) {
        String regex = "[\\w-]+(\\.[\\w]+)*(?<!-)@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})";
        return Pattern.compile(regex).matcher(email).matches();
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
