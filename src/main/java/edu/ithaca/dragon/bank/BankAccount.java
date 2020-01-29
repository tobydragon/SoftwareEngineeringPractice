package edu.ithaca.dragon.bank;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance) {
        if (isEmailValid(email) && isAmountValid(startingBalance)){
            this.email = email;
            this.balance = startingBalance;
        }

        else {
            throw new IllegalArgumentException("Email address or starting balance is invalid");
        }
    }

    public double getBalance(){ return balance; }

    public String getEmail(){
        return email;
    }

    /**
     * @throws InsufficientFundsException if withdraw amount is greater than balance
     * @throws IllegalArgumentException if withdraw amount is negative or has more than 2 decimals
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException, IllegalArgumentException {
        if (amount <= balance && isAmountValid(amount)){

            balance -= amount;
        }

        else if(!isAmountValid(amount)){
            throw new IllegalArgumentException("invalid amount to withdraw");
        }

        else {
            throw new InsufficientFundsException("Not enough money");
        }


    }


    /**
     * @throws InsufficientFundsException if amount is more than is available
     * @param amount
     * @post subtracts from account1 to account2
     */

    public void transfer(double amount, BankAccount fromAccount, BankAccount toAccount) throws IllegalArgumentException, InsufficientFundsException {
        if(isAmountValid(amount)){

            fromAccount.withdraw(amount);


            toAccount.deposit(amount);

            DecimalFormat newFormat = new DecimalFormat("#. ##");


            fromAccount.balance = Double.valueOf(newFormat.format(fromAccount.balance));

            toAccount.balance = Double.valueOf(newFormat.format(toAccount.balance));

        }

        else if(!isAmountValid(amount)){
            throw new IllegalArgumentException("Not a valid amount to transfer");
        }

        else {
            throw new InsufficientFundsException("Not enough money to transfer");
        }
    }





    /**
     * @throws IllegalArgumentException if amount is negative or contains more than two decimals
     * @param amount
     * @post adds the amount to the balance of the bankAccount, should increase
     */

    public void deposit(double amount){
        if(isAmountValid(amount)){
            balance+=amount;

            DecimalFormat newFormat = new DecimalFormat("#. ##");

            balance = Double.valueOf(newFormat.format(balance));



        }
        else{
            throw new IllegalArgumentException("Invalid deposit amount");
        }
    }

    /**
     * @param amount
     * @return true if amount is not negative and two decimals or less
     */

    public static boolean isAmountValid(double amount) {
        if(amount < 0) {
            return false;
        }

        String s = ""+amount;

        int i = s.lastIndexOf('.');

        if(s.length()-i-1 > 2){
            return false;
        }


        return true;
    }






    public static boolean isEmailValid(String email){
        if (email.indexOf('@') == -1 || email.indexOf('@') == 0) {
            return false;
        }
        if (email.indexOf('.') == -1) {
            return false;
        }
        int count = 0;
        for (int i = email.indexOf('@'); i < email.length(); i++) {
            if (email.charAt(i) == ('.')) {
                count++;
            }
        }
        if (count != 1) {
            return false;
        }

        for (int i = 0; i < email.indexOf('@'); i++) {
            if (email.charAt(i) == ('.')) {
                if (email.charAt(i + 1) == ('.')) {
                    return false;
                }
            }
        }

        int count3 = 0;
        for (int j = email.indexOf('.'); j < email.length(); j++) {
            if (email.charAt(j) != (' ')) {
                count3++;
            }
        }


        if (count3 <= 1) {
            return false;
        }


        int count2=0;
        for(int i=0; i<email.length(); i++){
            if(email.indexOf('-') ==  email.indexOf('@')-1){
                count2++;
            }
        }

        if(count2 != 0){
            return false;
        }


        int indx = email.lastIndexOf('.');

        int length = email.substring(indx + 1).length();
        return length >= 2;

    }





}
