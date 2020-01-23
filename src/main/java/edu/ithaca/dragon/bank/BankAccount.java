package edu.ithaca.dragon.bank;

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
    if the amount is less than 0
    print out "Amount wanted is less than 0" error
    Don't change the original balance

    if the amount is 0
    return the balance as is/do nothing and have the subtraction be 0

    if the amount is less than the current balance
    print out "Amount wanted is less than the current balance" error
    Don't change the original balance

    if all past if statements failed/was not used
    subtract the amount from the current balance regularly
     */
    public void withdraw (double amount){
        balance -= amount;
    }


    public static boolean isEmailValid(String email){
        if(email == ""){
            return false;
        }
        if (email.indexOf('@') == -1){
            return false;
        }

        else if(email.indexOf(".") == 0){
            return false;
        }

        else if(email.indexOf("..") != -1){
            return false;
        }


        else if(email.indexOf("-@") != -1){
            return false;
        }

        else if(email.indexOf("#") != -1){
            return false;
        }

        else{
            String emDomain = email.substring(email.indexOf("@"));

            if(emDomain.indexOf(".") == -1){
                return false;
            }

            else if(emDomain.indexOf("#") != -1){
                return false;
            }
            else {
                String emEnd = emDomain.substring(emDomain.indexOf("."));

                /**
                 * Check if end portion length is less than 2 and 3 but not checking
                 * All other tests passed
                 */
                if (emEnd.length() < 2 && emEnd.length() < 3) {
                    return false;
                } else if (emDomain.indexOf(".") == -1) {
                    return false;
                }
            }
        }
        return true;
    }
}
