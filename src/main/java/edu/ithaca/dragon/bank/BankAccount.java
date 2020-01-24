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
     */
    public void withdraw(double amount) {
        balance -= amount;

    }


    public static boolean isEmailValid(String email) {
        if (email.indexOf('@') == -1) {
            return false;
        }
        else return !email.contains("-") && !email.contains("..") && !email.endsWith(".") && !email.endsWith("..")
                && email.contains(".com") && !email.startsWith(".") && !email.contains("#");

    }

}
