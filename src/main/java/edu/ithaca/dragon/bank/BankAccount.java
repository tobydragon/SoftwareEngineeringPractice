package edu.ithaca.dragon.bank;

import java.util.ArrayList;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)) this.email = email;
        else throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        if (isAmountValid(startingBalance)) this.balance = startingBalance;
        else throw new IllegalArgumentException(Double.toString(startingBalance)+" is an invalid starting Balance");
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
    if the amount is less than 0
    print out "Amount wanted is less than 0" error
    Don't change the original balance

    if the amount is 0
    return the balance as is/do nothing and have the subtraction be 0

    if the amount is greater than the current balance
    print out "Amount wanted is greater than the current balance" error
    Don't change the original balance

    if all past if statements failed/was not used
    subtract the amount from the current balance regularly
     */
    public void withdraw (double amount) throws InsufficientFundsException, IllegalArgumentException{
        if (Double.compare(amount, 0.0)==0) throw new IllegalArgumentException("Cannot withdraw zero dollars");
        if (!isAmountValid(amount)) throw new IllegalArgumentException(Double.toString(amount)+" is not a valid withdraw amount");
        if(amount > balance) throw new InsufficientFundsException("Not enough Money");
        else balance -= amount;
    }

    /**
     * Returns true if amountIn is positive and has 2 or less decimal places. Returns false otherwise.
     * @param amountIn
     * @return
     */
    public static boolean isAmountValid(double amountIn){
        if (amountIn < 0) return false;
        double scale = Math.pow(10, 9);
        amountIn = Math.round(amountIn*scale)/scale;
        if(Double.compare(amountIn, Math.round(amountIn*100)/100.0)!= 0) return false;
        else return true;
    }

    /**
     * returns true if email is valid and false if not
     * @param email
     * @return if email is valid
     */
    public static boolean isEmailValid(String email){
        ArrayList<Character> validChar = validCharsList();


        int atId = email.indexOf('@'); //gets the index of the first @
        if(atId== -1) return false; //if no @ invalid email
        if(email.lastIndexOf('@')!=atId) return false; // if last @ is not the first @ invalid email
        if(atId == 0||atId == email.length()-1) return false; // if the @ is the first or the last char the email is invalid

        int lastPeriod = email.lastIndexOf('.');
        if(lastPeriod==-1)return false;//if there is no period there is no extension
        if(lastPeriod<atId) return false; // if the @ is after the last . the email is invalid
        if(lastPeriod!= email.length()-3&&lastPeriod!= email.length()-4)return false;//the extension can only be 2 or 3 chars.

        if(isSpecialChar(email.charAt(0))||isSpecialChar(email.charAt(email.length()-1))) return false; // if the first or last char are special the email is invalid

        // loops through the email from front to back to check for multiple special chars in a row and invalid chars
        for(int x = 0; x < email.length(); x++){
            //if current char not in list it is invalid
            if(!validChar.contains(email.charAt(x)))return false;
            //if current char not the first or last char, and is special, if the one after it is special the email is invalid
            if( (x!=0&&x!=email.length()-1) && (isSpecialChar(email.charAt(x))) && (isSpecialChar(email.charAt(x+1))) ) return false;
        }
        return true;
    }

    private static boolean isSpecialChar(char e){
        return e=='-'||e=='_'||e=='@'||e=='.';
    }

    private static ArrayList<Character> validCharsList (){
        ArrayList<Character> validChar = new ArrayList<Character>();
        for(int x = 0; x < 26; x++){
            validChar.add((char)(x+65));
            validChar.add((char)(x+97));
        }
        validChar.add('-');
        validChar.add('_');
        validChar.add('@');
        validChar.add('.');
        return validChar;
    }
}
