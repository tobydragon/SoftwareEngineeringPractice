package edu.ithaca.dragon.bank;


import java.util.HashSet;

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
     * throws IllegalArgument if the amount is more than the balance
     * for negative numbers return the unchanged balance
     * returns the balance if the withdraw amount is less than your balance and a positive number.
     */
    public void withdraw (double amount) throws InsufficientFundsException  {

        if (amount < .01)
            throw new IllegalArgumentException("Cannot withdraw $0 or less");

        else if (balance >= amount)
            balance -= amount;

        else if (balance < amount)
            throw new InsufficientFundsException("Cannot draw more than account balance.");

    }

    /**
     * returns true if email follows correct conventions.
     */

    public static boolean isEmailValid(String email) {

        //Checks that there is only 1 @.
        int atIdx = email.indexOf('@');
        int lastAtIdx = email.lastIndexOf('@');
        if (atIdx != lastAtIdx || atIdx == -1) return false;

        //Checks that there is a period in the one of two spots it must be in the domain.
        //Note that there can only be 2 or 3 characters after the last period in the domain.
        int len = email.length();
        int lastPeriodIdx = email.lastIndexOf('.');
        if (lastPeriodIdx == -1) return false;
        if (lastPeriodIdx != len - 3 && lastPeriodIdx != len - 4) return false;

        //Checks that the @ is before the last period.
        if (atIdx>lastPeriodIdx) return false;

        //Checks that all characters are valid and that the email obeys the letter after special rule.
        int i = len - 1;
        HashSet<Character> validCharSet = getValidCharSet();
        while (i > -1) { //going through each character in the email

            //if invalid character return false
            if (!validCharSet.contains(email.charAt(i))) return false;

            //if special character, make sure following character is not special
            if (isSpecialChar(email.charAt(i))){
                //if leading or last character is special, return false
                if (i == 0 || i == len - 1) return false;
                //if the next character is also special return false
                if (isSpecialChar(email.charAt(i + 1))) return false;
            }
            //go back one letter
            i--;
        }
        //if no invalid characters found or special rules broken, return true
        return true;
    }

    private static boolean isSpecialChar(char c) { return c == '.' || c == '-' || c == '_' || c == '@'; }

    private static HashSet<Character> getValidCharSet(){
        HashSet<Character> validCharSet = new HashSet<>();
        validCharSet.add('@');
        validCharSet.add('.');
        validCharSet.add('_');
        validCharSet.add('-');
        validCharSet.add('q');
        validCharSet.add('w');
        validCharSet.add('e');
        validCharSet.add('r');
        validCharSet.add('t');
        validCharSet.add('y');
        validCharSet.add('u');
        validCharSet.add('i');
        validCharSet.add('o');
        validCharSet.add('p');
        validCharSet.add('a');
        validCharSet.add('s');
        validCharSet.add('d');
        validCharSet.add('f');
        validCharSet.add('g');
        validCharSet.add('h');
        validCharSet.add('j');
        validCharSet.add('k');
        validCharSet.add('l');
        validCharSet.add('z');
        validCharSet.add('x');
        validCharSet.add('c');
        validCharSet.add('v');
        validCharSet.add('b');
        validCharSet.add('n');
        validCharSet.add('m');
        validCharSet.add('Q');
        validCharSet.add('W');
        validCharSet.add('E');
        validCharSet.add('R');
        validCharSet.add('T');
        validCharSet.add('Y');
        validCharSet.add('U');
        validCharSet.add('I');
        validCharSet.add('O');
        validCharSet.add('P');
        validCharSet.add('A');
        validCharSet.add('S');
        validCharSet.add('D');
        validCharSet.add('F');
        validCharSet.add('G');
        validCharSet.add('H');
        validCharSet.add('J');
        validCharSet.add('K');
        validCharSet.add('L');
        validCharSet.add('Z');
        validCharSet.add('X');
        validCharSet.add('C');
        validCharSet.add('V');
        validCharSet.add('B');
        validCharSet.add('N');
        validCharSet.add('M');
        validCharSet.add('1');
        validCharSet.add('2');
        validCharSet.add('3');
        validCharSet.add('4');
        validCharSet.add('5');
        validCharSet.add('6');
        validCharSet.add('7');
        validCharSet.add('8');
        validCharSet.add('9');
        validCharSet.add('0');

        return validCharSet;
    }
}
