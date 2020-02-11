package edu.ithaca.dragon.bank;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BankAccount {

    protected String acctId;
    protected String email;
    protected double balance;
    protected String type;

    /**
     * @param email same as user email
     * @param startingBalance starting balance in account
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email) && isAmountValid(startingBalance)){
            this.email = email;
            this.balance = startingBalance;
            this.acctId = "testAccount";
        }
        else if(!isEmailValid(email)) {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
        else if(!isAmountValid(startingBalance)){
            throw new IllegalArgumentException("Starting balance: " + startingBalance + " is invalid, cannot create account");
        }
    }

    /**
     * Constructor with acctId
     * @param email same as user email
     * @param startingBalance starting balance in account
     * @param acctId Associated account ID
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance, String acctId){
        if (isEmailValid(email) && isAmountValid(startingBalance)){
            this.email = email;
            this.balance = startingBalance;
            this.acctId = acctId;
        }
        else if(!isEmailValid(email)) {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
        else if(!isAmountValid(startingBalance)){
            throw new IllegalArgumentException("Starting balance: " + startingBalance + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * @param amount the amount to withdraw
     * @throws IllegalArgumentException if value to withdraw is negative or value contains precision more than 0.01 (ex. 0.001, 0.0001, ...)
     * @throws InsufficientFundsException if value to withdraw is larger than the bank account balance
     */
    public void withdraw (double amount) throws InsufficientFundsException {
        if (!isAmountValid(amount)){
            throw new IllegalArgumentException("Cannot withdraw a negative amount or an amount with more than 0.01 precision");
        }
        if (amount > balance){
            throw new InsufficientFundsException("Too small a balance");
        }
        balance -= amount;
        balance = (double) Math.round(balance * 100.0) / 100.0;
    }

    /**
     * @post increases the balance by amount if amount is non-negative
     * @param amount the amount to deposit
     * @throws IllegalArgumentException if value to withdraw is negative or value contains precision more than 0.01  (ex. 0.001, 0.0001, ...)
     */
    public void deposit (double amount){
        if (!isAmountValid(amount)){
            throw new IllegalArgumentException("Amount is invalid");
        }
        balance += amount;
    }

    /**
     * transfers an amount from this bank account to another bank account
     * @param account the account to transfer the amount to
     * @param amount the amount being transferred
     * @throws IllegalArgumentException if value to transfer is negative or value contains precision more than 0.01  (ex. 0.001, 0.0001, ...)
     * @throws IllegalArgumentException if the bank account being transferred to is the same
     * @throws InsufficientFundsException if value to transfer is larger than this account's current balance
     */
    public void transfer(BankAccount account, double amount) throws InsufficientFundsException{
        if (account == this){
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }
        if (amount > balance){
            throw new InsufficientFundsException("Insufficient funds to transfer");
        }
        if (!isAmountValid(amount)){
            throw new IllegalArgumentException("Invalid amount");
        }
        this.withdraw(amount);
        account.deposit(amount);
    }

    /**
     * checks if an email is valid based on a few rules and using a regular expression
     * @param email the email (as a string) to check if it is a valid email
     * @return true if the email is a valid email address or false if the email is not a valid email address
     */
    public static boolean isEmailValid(String email){
        String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern regex_pattern = Pattern.compile(pattern);
        Matcher matcher = regex_pattern.matcher(email);
        int indexOfAt = email.indexOf("@");
        if(indexOfAt > 0){
            if(email.charAt(indexOfAt-1) == '-'){
                return false;
            }
        }
        return matcher.matches();
    }

    /**
     * determines whether an input amount is non-negative and does not have more than two decimal places
     * @param amount the amount to validate
     * @return true if the amount to be checked is a valid amount or false if not valid
     */
    public static boolean isAmountValid(double amount){
        if (amount < 0){
            return false;
        }
        String amountString = "" + amount;
        if (amountString.indexOf(".") != -1){
            String decimalPlaces = amountString.substring(amountString.indexOf(".") + 1);
            if (decimalPlaces.length() > 2){
                return false;
            }
        }
        return true;
    }
}
