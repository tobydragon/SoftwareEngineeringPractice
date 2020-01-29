package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance) {
        if (isEmailValid(email)) {
            this.email = email;
            this.balance = startingBalance;
        } else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
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
     * if amount is negative or larger than balance, balance stays the same
     */
    public void withdraw(double amount) {
        if (!(balance < amount) && amount > 0){
            balance -= amount;
        }
    }


    public static boolean isEmailValid(String email) {
        if (email.indexOf('@') == -1) {
            return false;
        }
        if (email.startsWith(".")) {
            return false;
        }
        if (!email.endsWith(".com")) {
            return false;
        }
        if(email.contains("-@")){
            return false;
        }
        if(email.contains("..")){
            return false;
        }
        if(email.contains("--")){
            return false;
        }
        if(email.contains("__")){
            return false;
        }
        if(email.contains("#")){
            return false;
        }
        if(email.contains("##")){
            return false;
        }
        if(!email.substring(email.length()-4,email.length()-1).contains("c")){
            return false;
        }

        else {
            return true;
        }
    }

    /**
     * @param amount
     * @return function returns a boolean telling whether an amount is a valid input
     * valid inputs are positive and 2 decimal points or less
     */
    public static boolean isAmountValid(double amount){
        boolean isValid = true;

        if (amount <= 0){
            isValid = false;
        }
        else {
            String amountString = Double.toString(amount);
            if (amountString.indexOf('.')+2 < amountString.length()-1){
                isValid=false;
            }
        }

        return isValid;
    }
}
