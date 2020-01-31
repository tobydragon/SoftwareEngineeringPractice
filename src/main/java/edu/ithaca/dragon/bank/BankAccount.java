package edu.ithaca.dragon.bank;


public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     * @throws IllegalArgumentException if startingBalance is invalid
     */
    public BankAccount(String email, double startingBalance) {
        if (isEmailValid(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }

        if(isAmountValid(startingBalance)) {
            this.balance = startingBalance;
        } else {
            throw new IllegalArgumentException("Starting balance: " + startingBalance + " is invalid, cannot create account");
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * if the amount is non-negative, check if the balance is larger than the amount
     * if amount is smaller than the balance, withdraw the funds
     * otherwise, throw Insufficient Funds Exception
     */
    public void withdraw (double amount) throws InsufficientFundsException, IllegalArgumentException {

        if(!isAmountValid(amount)){
            throw new IllegalArgumentException("Amount must be non-negative");
        }
        else if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }

    /**
     * deposits amount into bank account
     * @param amount double containing desired amount to deposit
     * @throws IllegalArgumentException if amount is invalid
     */
    public void deposit (double amount) throws IllegalArgumentException {

    }

    public static boolean isEmailValid(String email) {
        if (email.indexOf('@') == -1) {
            return false;
        }
        else return !email.contains("-") && !email.contains("..") && !email.endsWith(".") && !email.endsWith("..")
                && email.contains(".com") && !email.startsWith(".") && !email.contains("#");

    }

    public static boolean isAmountValid(double amount) {
        if (amount < 0){
            return false;
        }

        // check number of decimal places
        String checkDouble = Double.toString(amount);
        int indexDecimal = checkDouble.indexOf('.');
        return checkDouble.length() - indexDecimal <= 3;
    }

}
