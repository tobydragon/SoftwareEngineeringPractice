package edu.ithaca.dragon.bank;

import java.util.Scanner;

public class AtmUI {
    private BasicAPI myAtm;
    private boolean login;
    private String email;
    private String password;
    private String currAcctType;

    public AtmUI(CentralBank centralBank) {
        myAtm = centralBank;
    }

    public void loginUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome! Please enter your email (you can always type q to quit)");
        email = scanner.nextLine();
        while (!BankAccount.isEmailValid(email) || email.equals("q")) {
            if (email.equals("q")) {
                System.out.println("Thanks for using this ATM");
                return;
            }
            System.out.println("That was not a valid email. Try again please");
            email = scanner.nextLine();
        }
        System.out.println("Enter your Password");
        password = scanner.nextLine();
        if (myAtm.confirmCredentials(email, password)) {
            chooseAcct();
        } else {
            email = "";
            password = "";
            System.out.println("Sorry, your email or password were not recognized. \nTry again or see a teller to start a new account");
            loginUser();
        }
        return;
    }

    public void chooseAcct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the type of account you wish to access");
        currAcctType = scanner.nextLine();
        while (!myAtm.isAccount(email, currAcctType) || currAcctType.equals("q")) {
            if (currAcctType.equals("q")) {
                System.out.println("Thanks for using this ATM");
                loginUser();
                return;
            }
            System.out.println("Account not found, try again or q to quit");
            currAcctType = scanner.nextLine();
        }
        System.out.println("Account Found! Your balance is: " + myAtm.checkBalance(email, currAcctType));
        acctChosen();
        return;
    }

    public void acctChosen(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type W to withdraw, T to transfer, or D to deposit");
        String choice = scanner.nextLine();
        while (!choice.equals("W") || !choice.equals("T") ||!choice.equals("D") ||!choice.equals("q")) {
            if (choice.equals("W")) {
                withdraw();
                return;
            }
            if (choice.equals("T")) {
                transferAmt();
                return;
            }
            if (choice.equals("D")) {
                deposit();
                return;
            }
            else if (choice.equals("q")) {
                System.out.println("Thanks for using this ATM. Your balance is: " + myAtm.checkBalance(email, currAcctType));
                loginUser();
                return;
            }
            System.out.println("Type W to withdraw, T to transfer, or D to deposit");
            choice = scanner.nextLine();
        }
        return;
    }

    public void withdraw(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("How much would you like to withdraw? ('-1' to choose different option)");
        boolean good = false;
        double amt = -1;
        while (!good) {
            try {
                amt = scanner.nextDouble();
                good = true;
            } catch (Exception e) {
                System.out.println("Please enter a number");
                withdraw();
                return;
            }
        }
        if(amt == -1){
            acctChosen();
            return;
        }
        try{
            myAtm.withdraw(email, currAcctType, amt);
        }
        catch (Exception e){
            System.out.println(e + "\nTry again");
            withdraw();
            return;
        }
        System.out.println("Withdrawal successful! Taking you back to the options menu");
        System.out.println("Your balance is: " + myAtm.checkBalance(email, currAcctType));
        acctChosen();
        return;
    }
    public void transferAmt(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Can't do this yet... Back to menu");
        acctChosen();
    }
    public void deposit(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("How much would you like to deposit? ('-1' to choose different option)");
        boolean good = false;
        double amt = -1;
        while (!good) {
            try {
                amt = scanner.nextDouble();
                good = true;
            } catch (Exception e) {
                System.out.println("Please enter a number");
                deposit();
                return;
            }
        }
        if(amt == -1){
            acctChosen();
            return;
        }
        try{
            myAtm.deposit(email, currAcctType, amt);
        }
        catch (Exception e){
            System.out.println(e + "\nTry again");
            deposit();
            return;
        }
        System.out.println("Deposit successful! Taking you back to the options menu");
        System.out.println("Your balance is: " + myAtm.checkBalance(email, currAcctType));
        acctChosen();
        return;
    }
}

class Main{
    public static void main(String[] args) throws InsufficientFundsException{
        CentralBank myBank = new CentralBank();
        AdvancedAPI teller = myBank;
        teller.createNewUserAccount("a@b.com", 1000, "Checking", "abc");
        teller.createNewUserAccount("a@b.com", 1000, "Savings", "abc");
        teller.setDailyMax("a@b.com", "Savings", 200);
        teller.createNewUserAccount("b@c.com", 1000, "Savings", "qwe");
        teller.setDailyMax("b@c.com", "Savings", 100);
        AtmUI atm = new AtmUI(myBank);
        atm.loginUser();
    }
}


