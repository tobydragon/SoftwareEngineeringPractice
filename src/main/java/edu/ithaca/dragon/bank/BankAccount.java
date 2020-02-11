package edu.ithaca.dragon.bank;

import java.util.HashSet;

public class BankAccount {

    public String email;
    public double balance;
    private String password;
    private int depositCount;
    private int withdrawCount;
    private int transferCount;


    /**
     * @throws IllegalArgumentException if amount to be withdrawn is invalid
     * Returns a boolean
     */
    private boolean isAmountValid(double amount) throws IllegalArgumentException{
        String numString = Double.toString(amount);
        int length = numString.length();

        if (length > 3){ //only runs this test if amount has more than 3 chars
            int period = numString.lastIndexOf(".");
            if (length > (period + 3)) throw new IllegalArgumentException("The amount you entered " + amount + " is invalid because it has more than three decimal places.");
        }

        else if (amount < 0){  //checks ifd the number is negative
            throw new IllegalArgumentException("The amount you entered " + amount + " is invalid because it is negative");
        }

        return true; // returns true if amount is valid
    }

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance, String password){

        if (isAmountValid(startingBalance) == false){
            throw new IllegalArgumentException("Starting balance is an invalid balance because it is negative or has too many decimal places");
        }
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
            this.password = password;
            this.depositCount = 0;
            this.withdrawCount = 0;
            this.transferCount = 0;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }

    }

    public double getBalance(){
        return balance;
    }

    public String getPassword(){ return password; }

    public String getEmail(){
        return email;
    }
    public int getDepositCount(){
        return depositCount;
    }
    public int getWithdrawCount(){
        return withdrawCount;
    }
    public int getTransferCount(){
        return transferCount;
    }

    /**
     * @post transfers an amount from one account to the other.
     * parameters: bankaccount1, bankaccount2, transfer amount
     * @throws IllegalArgumentException if deposit amount is invalid
     *
     */
    public void transfer ( BankAccount bankAccountTranferringTo, double amount) throws InsufficientFundsException, IllegalArgumentException{
        if (isAmountValid(amount) != true){
            throw new IllegalArgumentException ("The amount you entered: "+ amount + " is not a valid amount");
        }

        else if (balance < amount){
            throw new InsufficientFundsException("You do not have enough money in your account to make this transfer");
        }
        else if (amount == 0 || amount < 0){
            throw new IllegalArgumentException("You cannot transfer $0 or less");
        }

        else {
            balance -= amount;
            transferCount ++;
            bankAccountTranferringTo.balance += amount;
            bankAccountTranferringTo.transferCount++;
        }
    }

    /**
     * @post increases the balance by the amount deposited
     * parameters: deposit amount
     * @throws IllegalArgumentException if deposit amount is invalid
     *
     */

    public void deposit (double amount) throws IllegalArgumentException {
        if (isAmountValid(amount) == false){
            throw new IllegalArgumentException("The amount you entered " + amount + " is invalid");
        }
        else if (amount == 0){
            throw new IllegalArgumentException("You cannot deposit $0");
        }
        else {
            balance += amount;
            depositCount ++;
        }
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * throws IllegalArgument if the amount is more than the balance
     * for negative numbers return the unchanged balance
     * returns the balance if the withdraw amount is less than your balance and a positive number.
     */
    public void withdraw (double amount) throws IllegalArgumentException, InsufficientFundsException {
        if (isAmountValid(amount) == false){
            throw new IllegalArgumentException("The amount you entered " + amount + " is invalid");
        }

        if (amount < .01) {
            throw new IllegalArgumentException("Cannot withdraw $0 or less");
        }
        else if (balance >= amount) {
            balance -= amount;
            withdrawCount++;
        }
        else if (balance < amount) {
            throw new InsufficientFundsException("Cannot draw more than account balance.");
        }
    }

    /**
     * returns true if email follows correct conventions.
     */

    public static boolean isEmailValid(String email) {

        //declares a variable with the locations of the @ signs in the email.
        int atSign = email.indexOf('@');
        int lastAtSign = email.lastIndexOf('@');

        //makes sure that an @ sign is in the email.
        if (atSign == -1)
            return false;

        //makes sure that there is only one @ sign.
        if (atSign != lastAtSign)
            return false;

        //Makes sure that a special character is not followed by a "@"
        if (email.charAt(lastAtSign-1) == '.')
            return false;

        if (email.charAt(lastAtSign-1) == '_')
            return false;

        if (email.charAt(lastAtSign-1) == '-')
            return false;

        //declares a variable with the location of the last "." in the email
        int lastPeriodSign = email.lastIndexOf('.');


        //makes sure that there is a '.' in the email.
        if (lastPeriodSign == -1)
            return false;

        //makes sure that there is a period after @
        if (email.charAt(lastAtSign) < email.charAt(lastPeriodSign))
            return false;

        //makes sure that there is not two periods, dashes, or underscores back to back in the email
        int x = 0;
        int lengthOfEmail = email.length();

        int locationOfLastPeriod = -1000;
        int locationOfCurrentPeriod;

        int locationOfLastDash = -2;
        int locationOfCurrentDash;

        int locationOfLastUnderscore = -2;
        int locationOfCurrentUnderscore;

        //makes sure the period follows a .com or .cc or .org or .edu etc...
        if (lastPeriodSign != lengthOfEmail - 3 && lastPeriodSign != lengthOfEmail - 4)
            return false;

        while (x < (email.length()-1)){

            //checks to make sure the email does not start with a period
            if(email.charAt(x) == '.' && x == 0)
                return false;
            //checks for double periods
            if (email.charAt(x) =='.' && x == (locationOfLastPeriod+1))
                return false;
            if(email.charAt(x) =='.')
                locationOfLastPeriod = x;

            //checks for double underscores
            if(email.charAt(x) == '_' && x == 0)
                return false;
            if (email.charAt(x) == locationOfLastUnderscore)
                return false;
            else if(email.charAt(x) =='_')
                locationOfLastUnderscore = x;

            //checks for double dashes
            if(email.charAt(x) == '-' && x == 0)
                return false;
            if (email.charAt(x) == locationOfLastDash)
                return false;
            else if(email.charAt(x) =='-')
                locationOfLastDash = x;

            x++;
        }



        //makes sure there is not an invalid character
        int j = 0;
        HashSet<Character> validCharSet = getValidCharSet();
        while (j < lengthOfEmail) {


            if (!validCharSet.contains(email.charAt(j))) return false;


            if (isSpecialChar(email.charAt(j))){
                //if leading or last character is special, return false
                if (j == 0 || j == lengthOfEmail - 1) return false;
                //makes sure theres no double special characters
                if (isSpecialChar(email.charAt(j + 1))) return false;
            }
            //go back one letter
            j++;
        }

        return true;

    }
    private static boolean isSpecialChar(char c) { return c == '.' || c == '-' || c == '_' || c == '@'; }

    private static HashSet<Character> getValidCharSet(){
        HashSet<Character> validCharSet = new HashSet<>();
        validCharSet.add('@');
        validCharSet.add('.');
        validCharSet.add('_');
        validCharSet.add('-');
        validCharSet.add('q');
        validCharSet.add('w');
        validCharSet.add('e');
        validCharSet.add('r');
        validCharSet.add('t');
        validCharSet.add('y');
        validCharSet.add('u');
        validCharSet.add('i');
        validCharSet.add('o');
        validCharSet.add('p');
        validCharSet.add('a');
        validCharSet.add('s');
        validCharSet.add('d');
        validCharSet.add('f');
        validCharSet.add('g');
        validCharSet.add('h');
        validCharSet.add('j');
        validCharSet.add('k');
        validCharSet.add('l');
        validCharSet.add('z');
        validCharSet.add('x');
        validCharSet.add('c');
        validCharSet.add('v');
        validCharSet.add('b');
        validCharSet.add('n');
        validCharSet.add('m');
        validCharSet.add('Q');
        validCharSet.add('W');
        validCharSet.add('E');
        validCharSet.add('R');
        validCharSet.add('T');
        validCharSet.add('Y');
        validCharSet.add('U');
        validCharSet.add('I');
        validCharSet.add('O');
        validCharSet.add('P');
        validCharSet.add('A');
        validCharSet.add('S');
        validCharSet.add('D');
        validCharSet.add('F');
        validCharSet.add('G');
        validCharSet.add('H');
        validCharSet.add('J');
        validCharSet.add('K');
        validCharSet.add('L');
        validCharSet.add('Z');
        validCharSet.add('X');
        validCharSet.add('C');
        validCharSet.add('V');
        validCharSet.add('B');
        validCharSet.add('N');
        validCharSet.add('M');
        validCharSet.add('1');
        validCharSet.add('2');
        validCharSet.add('3');
        validCharSet.add('4');
        validCharSet.add('5');
        validCharSet.add('6');
        validCharSet.add('7');
        validCharSet.add('8');
        validCharSet.add('9');
        validCharSet.add('0');

        return validCharSet;
    }

}