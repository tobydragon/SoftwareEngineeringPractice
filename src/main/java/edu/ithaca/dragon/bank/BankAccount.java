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
    public void withdraw (double amount) throws InsufficientFundsException {
        if (amount < 0){
            balance = balance;
        }
        else if (balance < amount) {
            throw new InsufficientFundsException("Cannot withdraw an amount larger than you balance");
        }
        else{
            balance -= amount;
        }

    }


    public static boolean isEmailValid(String email) {
        int atsym = email.indexOf('@');
        if (atsym == -1)
            return false;


        if (email.charAt(0) == '-' || email.charAt(atsym - 1) == '-')
            return false;

        if (email.charAt(0) == '.' || email.charAt(atsym - 1) == '.')
            return false;

        String domain = email.substring(atsym + 1);
        int period = domain.indexOf('.');

        if (period == -1)
            return false;

        int pdcount = 0;
        for (int i = 0; i < domain.length(); i++) {
            if (domain.charAt(i) == '@')
                return false;
            else if (domain.charAt(i) == '.')
                pdcount++;
        }


        if (pdcount != 1)
            return false;
        if (domain.substring(period).length() < 2)
            return false;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '#')
                return false;
            else if (email.charAt(i) == ' ')
                return false;
            else if (email.charAt(i) == '.') {
                if (email.charAt(i + 1) == '.')
                    return false;
            } else if (email.charAt(i) == '-') {
                if (email.charAt(i + 1) == '-')
                    return false;
            }
        }

        return true;

    }

}
