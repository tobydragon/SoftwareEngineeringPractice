package edu.ithaca.dragon.bank;
import java.util.regex.Matcher;
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
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * @throws IllegalArgumentException if value to withdraw is negative
     * @throws IllegalArgumentException if value to withdraw is larger than the bank account balance
     */
    public void withdraw (double amount)  {
        if (amount > balance){
            throw new IllegalArgumentException("Too small a balance");
        }
        if (amount < 0){
            throw new IllegalArgumentException("Cannot withdraw a negative amount");
        }
        balance -= amount;
    }


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
}
