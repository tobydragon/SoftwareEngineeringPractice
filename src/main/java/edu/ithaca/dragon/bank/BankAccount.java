package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email or starting balance is invalid
     */
    public BankAccount(String email, double startingBalance) {
        if (isEmailValid(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
        if(isAmountValid(startingBalance)){
            this.balance = startingBalance;
        } else {
            throw new IllegalArgumentException("Starting balance: " + startingBalance + " is invalid, cannot create account");
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and has 2 or less significant decimals and smaller than balance
     */
    public void withdraw(double amount) throws InsufficientFundsException, IllegalArgumentException {
        if(!isAmountValid(amount)){
            throw new IllegalArgumentException("Can't withdraw a negative amount or one with more than 2 significant decimals");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Cant withdraw more than you have in your account");
        } else {
            balance -= amount;
        }
    }

    /**
     * @post increases the balance by amount if amount is non-negative and has 2 or less significant decimals
     */
    public void deposit(double amount) throws IllegalArgumentException {
        if(!isAmountValid(amount)){
            throw new IllegalArgumentException("Can't withdraw a negative amount or one with more than 2 significant decimals");
        } else {
            balance += amount;
        }
    }

    /**
     * @post decreases the balance by amount and increases the balance of targetAccount by amount if amount is non-negative and has 2 or less significant decimals
     */
    public void transfer(double amount, BankAccount targetAccount) throws IllegalArgumentException, IllegalArgumentException {

    }

    /**
     * @param amount is a double representing a monetary value
     * @return true if non-negative and 2 or less decimal points, else false
     */
    public static boolean isAmountValid(double amount){
        if(amount < 0) {
            return false;
        }
        else if((amount * 100) % 1 == 0){
            return true;
        }
        else return false;
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
        } else if (!(Character.isLetter(email.charAt(email.length()-1)) || Character.isDigit(email.charAt(email.length()-1)))) {
            return false;
        } else {
            return true;
        }

        }
    }

