package edu.ithaca.dragon.bank;

import java.util.Scanner;

enum ATMUIState {Login, Frozen, Account, Menu, Withdraw, Deposit, Transfer}

public class ATMUI {

    private static CentralBank bank;
    private static BasicAPI atm;
    private static ATMUIState currentState;
    private static BankAccount currentAccount;
    private static User currentUser;
    private static Scanner in;

    public static void main(String[] args) throws IllegalArgumentException,InsufficientFundsException {
        User christian = new User("christian","123");
        User aidan = new User("aidan","123");
        User ioan = new User("ioan","123");
        BankAccount christianacct = new CheckingAccount(1000, "christian");
        BankAccount aidanacct = new CheckingAccount(1000, "aidan");
        BankAccount ioanacct = new CheckingAccount(1000, "ioan");
        CentralBank myBank = new CentralBank();
        myBank.accounts.add(christianacct);
        myBank.accounts.add(aidanacct);
        myBank.accounts.add(ioanacct);
        myBank.users.add(christian);
        myBank.users.add(aidan);
        myBank.users.add(ioan);
        bank = myBank;
        atm = new ATM(bank);
        currentState = ATMUIState.Login;
        in = new Scanner(System.in);

        while (true) {
            if(currentState == ATMUIState.Login) {
                Login();
            } else if(currentState == ATMUIState.Menu) {
                Menu();
            } else if(currentState == ATMUIState.Frozen) {
                Frozen();
            } else if(currentState == ATMUIState.Deposit) {
                Deposit();
            } else if(currentState == ATMUIState.Withdraw) {
                Withdraw();
            } else if(currentState == ATMUIState.Transfer) {
                Transfer();
            } else if(currentState == ATMUIState.Account) {
                Account();
            }
        }
    }



    static void Login() {
        System.out.println("Please enter your username and password: ");
        System.out.print("Username:");
        String username = in.nextLine();
        System.out.print("\nPassword:");
        String password = in.nextLine();

        if(atm.confirmCredentials(username, password)) {
            currentUser = atm.getUser(username, password);
            currentState = ATMUIState.Account;
        }
        else {
            System.out.println("Error: invalid credentials, please try again.");
            currentState = ATMUIState.Login;
        }
    }

    static void Account() {
        System.out.println("Enter account ID: ");
        String accID = in.nextLine();
        try {
            currentAccount = currentUser.getBankAccount(accID);
            if(currentAccount.isFrozen()) {
                currentState = ATMUIState.Frozen;
            }
            else {
                currentState = ATMUIState.Menu;
            }
        } catch (Exception e){
            System.out.println("Error: invalid account ID, please try again.");
        }
    }

    static void Menu() {
        System.out.println("Current balance: " + currentAccount.getBalance() + ". Would you like to withdraw, deposit, transfer, or logout?");
        String action = in.nextLine();
        if(action.equalsIgnoreCase("withdraw")) {
            currentState = ATMUIState.Withdraw;
        } else if (action.equalsIgnoreCase("deposit")) {
            currentState = ATMUIState.Deposit;
        } else if (action.equalsIgnoreCase("transfer")) {
            currentState = ATMUIState.Transfer;
        } else if (action.equalsIgnoreCase("logout")) {
            System.out.println("Thank you for banking with us today.");
            currentState = ATMUIState.Login;
        } else {
            System.out.println("Error: invalid command, please try again.");
            currentState = ATMUIState.Menu;
        }
    }

    static void Frozen() {
        System.out.println("This account is currently frozen due to suspicious activity. " +
                "For more information, please contact customer service at 1-888-555-1212.");
        currentState = ATMUIState.Login;
    }

    static void Withdraw()  throws InsufficientFundsException{
        System.out.println("Enter amount to withdraw: ");
        double amount = in.nextDouble();
        atm.withdraw(currentAccount.getAcctId(),amount);
        System.out.println("Withdraw successful. New balance: " + currentAccount.getBalance());
        currentState = ATMUIState.Menu;
    }

    static void Deposit() throws InsufficientFundsException {
        System.out.println("Enter amount to deposit: ");
        double amount = in.nextDouble();
        atm.deposit(currentAccount.getAcctId(),amount);
        System.out.println("Deposit successful. New balance: " + currentAccount.getBalance());
        currentState = ATMUIState.Menu;
    }

    static void Transfer() {
        System.out.println("Enter accountID to transfer to: ");
        String accIDTo = in.nextLine();
        try {
            BankAccount accTo = bank.getBankAccount(accIDTo);
            System.out.println("Enter amount to transfer: ");
            double amount = in.nextDouble();
            atm.transfer(currentAccount.getAcctId(),accIDTo,amount);
            System.out.println("Transfer successful. New balance: " + currentAccount.getBalance());
            currentState = ATMUIState.Menu;
        }
        catch (Exception e){
            System.out.println("Error: invalid account ID, please try again.");
            currentState = ATMUIState.Transfer;
        }
    }
}
