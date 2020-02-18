package edu.ithaca.dragon.bank;

import java.util.Scanner;

enum ATMUIState {Login, Logout, Frozen, Menu, Withdraw, Deposit, Transfer}

public class ATMUI {

    private static CentralBank bank;
    private static BasicAPI atm;
    private static ATMUIState currentState;
    private static BankAccount currentAccount;
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
            }
        }
    }



    static void Login() {

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
