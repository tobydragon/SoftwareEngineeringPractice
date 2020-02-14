package edu.ithaca.dragon.bank;

import java.util.ArrayList;

public class BankAccount {

    protected String email, password;
    protected double balance;
    protected ArrayList<AccountEvent> history;

    /**
     * @throws IllegalArgumentException if email or starting balance is invalid
     */
    public BankAccount(String email, String password, double startingBalance) {
        if (isEmailValid(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }

        this.password = password;

        if(isAmountValid(startingBalance)){
            this.balance = startingBalance;
        } else {
            throw new IllegalArgumentException("Starting balance: " + startingBalance + " is invalid, cannot create account");
        }

        this.history = new ArrayList<AccountEvent>();
    }

    public double getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    /**
     * @returns a textual representation of the history of the account
     */
    public String getHistory(){
        String stringHistory = "";
        for(int i = 0; i < history.size(); i++){
            stringHistory += history.get(i);
            if(i != history.size() - 1)
                stringHistory += "\n";
        }
        return stringHistory;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and has 2 or less significant decimals and smaller than balance
     * @throws IllegalArgumentException if amount is not valid (see isAmountValid() )
     * @throws InsufficientFundsException if the account doesn't have enough to withdraw the given amount
     */

    public void withdraw(double amount) throws InsufficientFundsException {
        withdraw(amount, "Withdraw");
    }

    public void withdraw(double amount, String name) throws InsufficientFundsException {
        if(!isAmountValid(amount)){
            throw new IllegalArgumentException("Can't withdraw a negative amount or one with more than 2 significant decimals");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Cant withdraw more than you have in your account");
        } else {
            history.add(new AccountEvent(name, amount, this.balance));
            balance -= amount;
        }
    }

    /**
     * @post increases the balance by amount if amount is non-negative and has 2 or less significant decimals
     * @throws IllegalArgumentException if amount is not valid (see isAmountValid() )
     */

    public void deposit(double amount) {
        deposit(amount, "Deposit");
    }

    public void deposit(double amount, String name) {
        if(!isAmountValid(amount)){
            throw new IllegalArgumentException("Can't withdraw a negative amount or one with more than 2 significant decimals");
        } else {
            history.add(new AccountEvent(name, amount, this.balance));
            balance += amount;
        }
    }

    /**
     * @post decreases the balance by amount and increases the balance of targetAccount by amount
     * @throws IllegalArgumentException if amount is not valid (see isAmountValid() )
     * @throws InsufficientFundsException if the account doesn't have enough to transfer the given amount out
     */
    public void transfer(double amount, BankAccount targetAccount) throws InsufficientFundsException {
        this.withdraw(amount, "Transfer out");
        targetAccount.deposit(amount, "Transfer in");
    }

    /**
     * @param amount is a double representing a monetary value
     * @return true if non-negative and 2 or less decimal points, else false
     */
    public static boolean isAmountValid(double amount){
        if(amount < 0) {
            return false;
        }
        else return (amount * 100) % 1 == 0;
    }

    /**
     * @return true if any account events in history are suspicious
     */
    public boolean checkSuspicious(){
        for(int i = 0; i < history.size(); i++){
            if(history.get(i).suspicious)
                return true;
        }
        return false;
    }


    public static boolean isEmailValid(String email) {
        String validDigits = "-_@.";

        //number of @ symbols
        int atCount = 0;

        //iterates each character of email
        for (int i = 0; i < email.length() - 1; i++) {
            Character c = email.charAt(i);

            //checks number of @s
            if (c == validDigits.charAt(2)) {
                atCount++;
            }
            //checks that each character is a valid digit
            if (!(Character.isLetter(c) || Character.isDigit(c) || c == validDigits.charAt(0) || c == validDigits.charAt(1) || c == validDigits.charAt(2) || c == validDigits.charAt(3))) {
                return false;

                //if current character is an allowed symbol, it checks that it is followed by a letter or number
            } else if (c == validDigits.charAt(0) || c == validDigits.charAt(1) || c == validDigits.charAt(2) || c == validDigits.charAt(3)) {
                Character nextChar = email.charAt(i + 1);
                if (!(Character.isLetter(nextChar) || Character.isDigit(nextChar))) {
                    return false;
                }
            }
        }
        //checks if email is empty
        if (email.isEmpty()) {
            return false;
            //checks if that there is exactly one @ symbol
        }  else if (atCount != 1) {
            return false;
        }

        //split the email at the @ so we can only look at domain
        String[] emailSplit = email.split("@");
        String domain = emailSplit[1];
        int periodIndex = 1000;
        String period = ".";

        //iterates domain to find where the period is
        for (int x = 0; x < domain.length() - 1; x++) {
            Character c2 = domain.charAt(x);
            if (c2 == period.charAt(0)) {
                periodIndex = x;
            }
        }
        //make sure domain contains a .
        if (!domain.contains(".")) {
            return false;

            //makes there is atleast two characters after period in domain
        } else if (domain.length() - 1 - periodIndex < 2) {
            return false;

            //checks that first character is a letter
        } else if (!Character.isLetter(email.charAt(0))) {
            return false;
            //checks if last character is letter or number
        } else return Character.isLetter(email.charAt(email.length() - 1)) || Character.isDigit(email.charAt(email.length() - 1));

        }
}

