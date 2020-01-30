package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email) && isAmountValid(startingBalance)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            if (!isEmailValid(email)){
                throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
            }
            else if(!isAmountValid(startingBalance)){
                throw new IllegalArgumentException("Starting Balance: " + startingBalance + " is invalid, cannot create account");
            }
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
     */
    public void withdraw (double amount)  {
        if (isAmountValid(amount)){
            if (amount <= balance){
                balance-=amount;
            }
        }
        else{
            throw new IllegalArgumentException("Withdraw amount: " + amount + " is invalid, cannot withdraw money");
        }
    }

    /**
     * Checks if the amount is in proper format
     * @param amount
     * @return boolean (true if amount is positive and has two decimal points or less, otherwise false)
     */
    public static boolean isAmountValid(double amount){
        String[] splitter = Double.toString(amount).split("\\.");
        splitter[0].length();   // Before Decimal Count
        int decimalLength = splitter[1].length();
        if(amount > -1 && decimalLength < 3){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean isEmailValid(String email) {
        if (email == "") {
            return false;
        }
        if (email.indexOf('@') == -1) {
            return false;
        } else if (email.indexOf(".") == 0) {
            return false;
        } else if (email.indexOf("-") == 0) {
            return false;
        }else if (email.indexOf("_") == 0) {
            return false;
        }else if (email.indexOf("..") != -1) {
            return false;
        }
        else if (email.indexOf("--") != -1){
            return false;
        }
        else if (email.indexOf("__") != -1){
            return false;
        }
        else if (email.indexOf("_@") != -1){
            return false;
        }
        else if (email.indexOf(".@") != -1){
            return false;
        }
        else if (email.indexOf("-@") != -1) {
            return false;
        } else if (email.indexOf("#") != -1) {
            return false;
        } else {
            String eDomain = email.substring(email.indexOf("@"));

            if (eDomain.indexOf(".") == -1) {
                return false;
            }

            else if (eDomain.indexOf("#") != -1){
                return false;
            }

            else{
                String emailEnd = eDomain.substring((eDomain.indexOf(".")));

                if (emailEnd.length() < 3 && emailEnd.length() < 4){
                    return false;
                }

                else if (eDomain.indexOf(".") == -1){
                    return false;
                }
                int periodCount = 0;
                int hyCount = 0;
                int unCount = 0;

                char perChar = '.';
                char hyChar = '-';
                char unChar = '_';

                for (int i = 0; i < emailEnd.length(); i++){
                    if(emailEnd.charAt(i) == perChar){
                        periodCount++;
                    }
                    if(emailEnd.charAt(i) == hyChar){
                        hyCount++;
                    }
                    if(emailEnd.charAt(i) == unChar){
                        unCount++;
                    }
                }
                if(periodCount > 1){
                    return false;
                }
                if(hyCount > 0){
                    return false;
                }
                if(unCount > 0){
                    return false;
                }
            }
        }
        return true;
    }
}

