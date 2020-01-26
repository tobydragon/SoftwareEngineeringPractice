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
     */
    public void withdraw (double amount)  {
        balance -= amount;

    }


    public static boolean isEmailValid(String email){
        int count=0;
        for(int i = 0, n = email.length() ; i < n ; i++) {
            char c = email.charAt(i);
            count += 1;

            if (count == 1) {
                if (c == '@' || c=='.')
                    return false;
            }
            else {
                if (c == '#')
                    return false;

                if (c == '@') {
                    if (email.charAt(count - 1) == ('.') || email.charAt(count - 1) == ('-'))
                        return false;

                    if (c == '.') {
                        if (email.charAt(count + 1) == '.')
                            return false;
                        String domain=email.substring(count+1);
                        if (domain=="cc" || domain=="com" || domain=="org")
                            return true;
                        else
                            return false;
                    }
                }
            }
        }


        if (email.indexOf('@') == -1){
            return false;
        }
        else {
            return true;
        }
    }

}
