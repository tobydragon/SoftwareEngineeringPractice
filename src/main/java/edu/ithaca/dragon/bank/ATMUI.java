package edu.ithaca.dragon.bank;

import java.util.Scanner;

public class ATMUI {
    private CentralBank bank;
    private Scanner sc;

    public ATMUI(CentralBank bankIn){
        bank = bankIn;
        sc = new Scanner(System.in);
    }

    public void logIn(){
        String acctId;
        String password;
        System.out.println("Hi! Please type in your Account ID below:");
        acctId = sc.nextLine();
        System.out.println("Please type your password:");
        password = sc.nextLine();
        if (!bank.confirmCredentials(acctId,password)){
            while (!bank.confirmCredentials(acctId, password)){
                System.out.println("Please type in your Account ID below:");
                acctId = sc.nextLine();
                System.out.println("Please type your password:");
                password = sc.nextLine();
            }
        } else if (bank.checkFrozenAccountExists(acctId)){
            frozenAcctMessage();
        } else if (bank.confirmCredentials(acctId,password)){
            displayBalance(acctId);
        }
    }
    
    public void frozenAcctMessage(){
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
    
    public void displayBalance(String acctId){
        double balance = bank.checkBalance(acctId);
        System.out.println("Balance: $" + balance);
        System.out.println("withdraw(w), deposit(d), transfer(t), or quit(q)");
        String input = sc.nextLine();
        if (input.compareToIgnoreCase("w") == 0){
            withdraw(acctId);
        } else if (input.compareToIgnoreCase("d") == 0){
            deposit(acctId);
        } else if (input.compareToIgnoreCase("t") == 0){
            transfer(acctId);
        } else if (input.compareToIgnoreCase("q") == 0){
            logIn();
        } else {
            displayBalance(acctId);
        }
    }
    
    public void withdraw(String acctId){
        //TODO
    }

    public void deposit(String acctId){
        //TODO
    }

    public void transfer(String acctId){
        //TODO
    }

}
