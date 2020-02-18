package edu.ithaca.dragon.bank;

import java.util.Scanner;

enum ATMUIState {Login, Logout, Frozen, Account, Menu, Withdraw, Deposit, Transfer}

public class ATMUI {

    private static CentralBank bank;
    private static BasicAPI atm;
    private static ATMUIState currentState;
    private static BankAccount currentAccount;
    private static User currentUser;
    private static Scanner in;

    public static void main(String[] args) throws IllegalArgumentException,InsufficientFundsException {
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
            } else if(currentState == ATMUIState.Logout) {
                Logout();
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
        System.out.print("Username: ");
        String username = in.nextLine();
        System.out.print("\nPassword: ");
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

    }

    static void Frozen() {

    }


    static void Logout() {

    }

    static void Withdraw()  throws InsufficientFundsException{

    }

    static void Deposit()  {


    }

    static void Transfer() {

    }
}
