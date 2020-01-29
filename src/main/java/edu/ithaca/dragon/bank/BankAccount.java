package edu.ithaca.dragon.bank;

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
        char atChar = '@'; //The "@" as a string has to be char to work
        int atCount = 0; //To count the amount of @ symbols
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == atChar) {
                atCount++;
            }
        }
        if(atCount >= 2){
            return false;
        }

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

                if (emEnd.length() < 3 && emEnd.length() < 4) {
                    return false;
                } else if (emDomain.indexOf(".") == -1) {
                    return false;
                }
            }
        }
        return true;
    }
}
