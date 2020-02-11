package edu.ithaca.dragon.bank;

import java.util.ArrayList;

public class BankAccount {

    private String acctId;
    private double balance;
    private boolean frozen = false;
    private ArrayList<Double> transactionHistory = new ArrayList<Double>();

    /**
     * @post creates a bank account object
     * @throws IllegalArgumentException if email is invalid
     *
     */
    public BankAccount(double startingBalance, String acctId) {
        if (isAmountValid(startingBalance)){
        this.balance = startingBalance;
        this.acctId = acctId;
        }

        else {
        throw new IllegalArgumentException("Dollar Amount: " + startingBalance + " is invalid, cannot create account");
         }
    }


    public double getBalance() {
        return balance;
    }


    /**
     * @post checks if inputted dollar amount is a valid dollar amount
     *
     */
    public static boolean isAmountValid(double amount) {

        if (amount < 0) {
            return false;
        }

        String amtstring = String.valueOf(amount);
        int decimalplace = amtstring.indexOf(".");
        String postdecimal = amtstring.substring(decimalplace+1);

        if (postdecimal.length() > 2) {
            return false;
        }

        return true;

    }



    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * @throws InsufficientFundsException if trying to withdraw more than whats in account.
     * @throws IllegalArgumentException if inputted amount is invalid.
     *
     */

    public void withdraw (double amount) throws InsufficientFundsException, IllegalArgumentException {

        //checks for valid input
        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("Invalid Input");
        }
        //checks for valid withdraw amount
        if (balance < amount) {
            throw new InsufficientFundsException("Cannot withdraw an amount larger than your balance");
        }

        else{
            balance -= amount;
        }

        transactionHistory.add(-amount);
    }




    /**
     * @post Adds the amount to the balance if amount is non-negative and smaller than balance
     * @throws IllegalArgumentException if inputted amount is invalid.
     *
     */
    public void Deposit(double amount) throws IllegalArgumentException, InsufficientFundsException {
        //checks for valid input
        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("Invalid Input");
        }

        else {
            balance += amount;
        }
        transactionHistory.add(amount);
    }


    /**
     * @post removes the amount from the balance and adds the amount to the other bank account if amount is non-negative and smaller than balance
     * @throws InsufficientFundsException if trying to withdraw more than whats in account.
     * @throws IllegalArgumentException if inputted amount is invalid.
     *
     */
    public void Transfer(BankAccount transferacct, double amount) throws IllegalArgumentException, InsufficientFundsException{
        //checks for valid input
        if (!isAmountValid(amount)){
            throw new IllegalArgumentException("Invalid Input");
        }

        //checks for valid transfer amount
        if (balance < amount){
            throw new InsufficientFundsException("Cannot transfer an amount larger than your balance");
        }

        else{
            balance-= amount;
            transferacct.balance+=amount;
        }
    }

}






