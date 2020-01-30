package edu.ithaca.dragon.bank;

import java.util.regex.Pattern;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * reduces the balance by amount if amount is non-negative and smaller or equal to balance
     * @param amount quantity to reduce balance by
     * @throws IllegalArgumentException if amount is negative
     * @throws InsufficientFundsException if amount is larger than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (amount < 0) {
            throw new IllegalArgumentException("Amount: " + amount + " is invalid, cannot create account");
        } else if (amount > balance) {
            throw new InsufficientFundsException("Not enough money");
        } else {
            balance -= amount;
        }
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
        return false;
    }
}
