package edu.ithaca.dragon.bank;

import java.util.ArrayList;

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

    /**
     * returns true if email follows correct conventions.
     */

    public static boolean isEmailValid(String email) {
        int atIdx = email.indexOf('@');
        int lastAtIdx = email.indexOf('@');

        //Checks that there is no more than 1 @.
        if (atIdx != lastAtIdx) { return false; }

        //Checks that there is an @.
        if (atIdx == -1) { return false; }

        //Checks that there is a period in the only two spots it can be in the domain.
        //Note that there can only be 2 or 3 characters after the period in the domain.
        int len = email.length();
        int lastPeriodIdx = email.lastIndexOf('.');

        if (lastPeriodIdx == -1){ return false; }
        if (lastPeriodIdx != len - 3 && lastPeriodIdx != len - 4) { return false; }

        //Checks that the @ is before the last period.
        if (atIdx>lastPeriodIdx) { return false; }

        //Checks that all characters are valid and that the email obeys the letter after special rule.
        int i = len - 1;
        ArrayList<Character> validCharArr = getValidCharArr();

        //going through each character in the email
        while (i > -1) {

            //if invalid character return false
            if (!(validCharArr.contains(email.charAt(i)))) { return false; }

            //if special character, make sure following character is not special
            if (isSpecialChar(email.charAt(i))){
                //if leading character is special, return false
                if (i == 0){ return false; }
                //if the next character is also special return false
                if (isSpecialChar(email.charAt(i + 1))){ return false; }
            }
            i--;
        }
        //if no invalid characters found or special rules broken, return true
        return true;
    }

    private static boolean isSpecialChar(char c) { return c == '.' || c == '-' || c == '_' || c == '@'; }

    private static ArrayList<Character> getValidCharArr(){
        ArrayList<Character> validCharArr = new ArrayList<>();
        validCharArr.add('@');
        validCharArr.add('.');
        validCharArr.add('_');
        validCharArr.add('-');
        validCharArr.add('q');
        validCharArr.add('w');
        validCharArr.add('e');
        validCharArr.add('r');
        validCharArr.add('t');
        validCharArr.add('y');
        validCharArr.add('u');
        validCharArr.add('i');
        validCharArr.add('o');
        validCharArr.add('p');
        validCharArr.add('a');
        validCharArr.add('s');
        validCharArr.add('d');
        validCharArr.add('f');
        validCharArr.add('g');
        validCharArr.add('h');
        validCharArr.add('j');
        validCharArr.add('k');
        validCharArr.add('l');
        validCharArr.add('z');
        validCharArr.add('x');
        validCharArr.add('c');
        validCharArr.add('v');
        validCharArr.add('b');
        validCharArr.add('n');
        validCharArr.add('m');
        validCharArr.add('Q');
        validCharArr.add('W');
        validCharArr.add('E');
        validCharArr.add('R');
        validCharArr.add('T');
        validCharArr.add('Y');
        validCharArr.add('U');
        validCharArr.add('I');
        validCharArr.add('O');
        validCharArr.add('P');
        validCharArr.add('A');
        validCharArr.add('S');
        validCharArr.add('D');
        validCharArr.add('F');
        validCharArr.add('G');
        validCharArr.add('H');
        validCharArr.add('J');
        validCharArr.add('K');
        validCharArr.add('L');
        validCharArr.add('Z');
        validCharArr.add('X');
        validCharArr.add('C');
        validCharArr.add('V');
        validCharArr.add('B');
        validCharArr.add('N');
        validCharArr.add('M');
        validCharArr.add('1');
        validCharArr.add('2');
        validCharArr.add('3');
        validCharArr.add('4');
        validCharArr.add('5');
        validCharArr.add('6');
        validCharArr.add('7');
        validCharArr.add('8');
        validCharArr.add('9');
        validCharArr.add('0');

        return validCharArr;
    }
}
