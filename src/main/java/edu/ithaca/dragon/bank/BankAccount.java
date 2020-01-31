package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            if(isAmountValid(startingBalance)){
                this.email = email;
                this.balance = startingBalance;
            }
            else{
                throw new IllegalArgumentException("starting Balance of " + startingBalance + "is an invalid amount to add");
            }
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
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * throws InsufficientFundsException if the amount is larger than the balance
     * If balance is negative or has more than 2 decimal places, throws IllegalArgumentException
     */
    public void withdraw (double amount) throws InsufficientFundsException, IllegalArgumentException  {
        if(!isAmountValid(amount)){
            throw new IllegalArgumentException("Amount must have 2 decimal places and must be positive ");
        }
        if (balance >= amount && amount >= 0) {
            balance -= amount;
        }
        else{
            throw new InsufficientFundsException("Amount requested is more than in your account by " + (amount - balance));
        }

    }

    /**
     * @post adds to the balance by amount if amount is non-negative and has 2 or less decimal places
     * @throws IllegalArgumentException if amount is negative or has more than 2 decimal places
     */
    public void deposit(double amount) throws IllegalArgumentException{

    }

    /**
     * @post transfers funds from one bank account to the one passed in, as long as amount is non-negative and has 2 or less decimal places
     * @throws IllegalArgumentException if amount is negative or has more than 2 decimal places, or if bankAccount to transfer to is the current one
     * @throws InsufficientFundsException if amount is larger than current bank account balance
     */
    public void transfer(double amount, BankAccount toTransfer) throws InsufficientFundsException, IllegalArgumentException{

    }

    /**
     * @post checks to see if a double is a valid input to be withdrawn
     * Returns false if double has more than 2 decimal places, or is negative
     */
    public static boolean isAmountValid(double amount){
        String doubleCheck = Double.toString(Math.abs(amount));
        int integerPlaces = doubleCheck.indexOf('.');
        int decimalPlaces = doubleCheck.length() - integerPlaces - 1;
        return (decimalPlaces <= 2 || doubleCheck.indexOf('E') != -1) && !(amount < 0);
    }

    public static boolean isEmailValid(String email){
        return email.matches("(\\w)+((_|\\.|-)+\\w+)*@(\\w)+((-)?\\w+)*\\.\\w{2,}$");
    }
}
