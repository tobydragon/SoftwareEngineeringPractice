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
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }

        if (isAmountValid(startingBalance)){
            this.balance = startingBalance;
        }

        else {
            throw new IllegalArgumentException("Dollar Amount: " + startingBalance + " is invalid, cannot create account");
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }

    /**
     * @post checks if inputted dollar amount is a valid dollar amount
     *
     *
     */
    public static boolean isAmountValid(double amount) {

        if (amount < 0) {
            return false;
        }

        String amtstring = String.valueOf(amount);
        int decimalplace = amtstring.indexOf(".");
        String postdecimal = amtstring.substring(decimalplace+1);

        if (postdecimal.length() > 2) {
            return false;
        }

        return true;

    }




    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * @throws InsufficientFundsException if trying to withdraw more than whats in account.
     *
     */

    public void withdraw (double amount) throws InsufficientFundsException, IllegalArgumentException {

        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("Invalid Input");
        }

        if (balance < amount) {
            throw new InsufficientFundsException("Cannot withdraw an amount larger than you balance");
        }

        else{
            balance -= amount;
        }


    }


    /**
     * @post checks if inputted email is a valid email.
     *
     *
     */
    public static boolean isEmailValid(String email) {
        //get index of @
        int atsym = email.indexOf('@');

        //empty string
        if (atsym == -1)
            return false;

        //invalid characters at start and before @ symbol
        if (email.charAt(0) == '-' || email.charAt(atsym - 1) == '-')
            return false;

        if (email.charAt(0) == '.' || email.charAt(atsym - 1) == '.')
            return false;

        //isolate domain
        String domain = email.substring(atsym + 1);

        //get index of period
        int period = domain.indexOf('.');

        //empty string
        if (period == -1)
            return false;

        //keeps track of periods in domain
        int pdcount = 0;


        for (int i = 0; i < domain.length(); i++) {
            //checks for 2 @'s next to each other
            if (domain.charAt(i) == '@')
                return false;

            else if (domain.charAt(i) == '.')
                pdcount++;
        }

        //can only be one period in domain
        if (pdcount != 1)
            return false;

        //must be at least 2 characters after period
        if (domain.substring(period).length() < 2)
            return false;


        for (int i = 0; i < email.length(); i++) {
            //invalid character check
            if (email.charAt(i) == '#')
                return false;
            //no spaces allowed
            else if (email.charAt(i) == ' ')
                return false;
            //2 periods in a row check
            else if (email.charAt(i) == '.') {
                if (email.charAt(i + 1) == '.')
                    return false;
            }

            //2 dashes in a row check
            else if (email.charAt(i) == '-') {
                if (email.charAt(i + 1) == '-')
                    return false;
            }
        }

        return true;

    }
    }

