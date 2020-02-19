package edu.ithaca.dragon.bank;

import java.util.Scanner;

public class ATMUI {
    private CentralBank bank;
    private Scanner sc;

    public ATMUI(CentralBank bankIn){
        bank = bankIn;
        sc = new Scanner(System.in);
    }

    public void logIn() throws InsufficientFundsException {
        String acctId;
        String password;
        System.out.println("Hi! Please type in your Account ID below:");
        acctId = sc.nextLine();
        System.out.println("Please type your password:");
        password = sc.nextLine();
        while (!bank.confirmCredentials(acctId, password)){
            System.out.println("Invalid ID or password");
            System.out.println("Please type in your Account ID below:");
            acctId = sc.nextLine();
            System.out.println("Please type your password:");
            password = sc.nextLine();
        } if (bank.checkFrozenAccountExists(acctId)){
            frozenAcctMessage();
        } else {
            displayBalance(acctId);
        }
    }
    
    public void frozenAcctMessage() throws InsufficientFundsException {
        System.out.println("Your account is frozen.");
        System.out.println("Please contact customer service: 1-888-555-1212.");
        System.out.println("Logout (l): ");
        String logout = sc.nextLine();
        if (logout.compareToIgnoreCase("l") == 0){
            logIn();
        }else{
            System.exit(0);
        }
    }
    
    public void displayBalance(String acctId) throws InsufficientFundsException {
        double balance = bank.checkBalance(acctId);
        System.out.println("Balance: $" + balance);
        System.out.println("withdraw(w), deposit(d), transfer(t), or quit(q)");
        String input = sc.nextLine();
        while (input.compareToIgnoreCase("w") != 0 && input.compareToIgnoreCase("d") != 0 && input.compareToIgnoreCase("t") != 0 && input.compareToIgnoreCase("q") != 0){
            System.out.println("Not a valid action");
            System.out.println("withdraw(w), deposit(d), transfer(t), or quit(q)");
            input = sc.nextLine();
        }
        if (input.compareToIgnoreCase("w") == 0){
            boolean success = false;
            while(!success) {
                try {
                    withdraw(acctId);
                } catch (InsufficientFundsException ife) {
                    System.out.println("Not enough funds to withdraw");
                } catch (IllegalArgumentException iae) {
                    System.out.println("Not valid amount");
                }
            }


        } else if (input.compareToIgnoreCase("d") == 0){
            boolean success1 = false;
            while(!success1) {
                try {
                    deposit(acctId);
                } catch (IllegalArgumentException iae) {
                    System.out.println("Not valid amount");
                }
            }

        } else if (input.compareToIgnoreCase("t") == 0){
            boolean success2 = false;
            while(!success2) {
                try {
                    transfer(acctId);
                } catch (InsufficientFundsException ife) {
                    System.out.println("Not enough funds to withdraw");
                } catch (IllegalArgumentException iae) {
                    System.out.println("Not valid amount or invalid account to send to");
                }
            }
        } else {
            input = "112";
            logIn();
        }
    }
    
    public void withdraw(String acctId) throws InsufficientFundsException {
        System.out.println("How much would you like to withdraw?");
        double amount = sc.nextDouble();
        bank.withdraw(acctId,amount);
        displayBalance(acctId);

    }

    public void deposit(String acctId) throws InsufficientFundsException{
        System.out.println("How much would you like to deposit?");
        double amount = sc.nextDouble();
        bank.deposit(acctId,amount);
        displayBalance(acctId);

    }

    public void transfer(String acctId) throws InsufficientFundsException {
        double amount;
        String transferTo;
        String strAmount;
        System.out.println("How much would you like to transfer?");
        strAmount = sc.nextLine();
        amount = Double.parseDouble(strAmount);
        System.out.println("What is the account ID of the account you are transferring to?");
        transferTo = sc.nextLine();
        bank.transfer(acctId, transferTo, amount);
        displayBalance(acctId);
    }

}
