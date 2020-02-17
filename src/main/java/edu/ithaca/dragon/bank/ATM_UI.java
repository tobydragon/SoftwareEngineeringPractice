package edu.ithaca.dragon.bank;

import java.util.Scanner;

enum ATMUIState {Login, Frozen, Logout, MainLoggedIn, Withdraw, Deposit, Transfer}

public class ATM_UI {

    private static BasicAPI atm;
    private static  CentralBank bank;
    private static ATMUIState currentUIState;
    private static Scanner in;
    private static Account currentAccount;

    public static void main(String[] args) {
        initializeATM();

        while (true) {
            if(currentUIState == ATMUIState.Login) {
                handleLoginUI();
            } else if(currentUIState == ATMUIState.Frozen) {
                handleFrozenUI();
            } else if(currentUIState == ATMUIState.MainLoggedIn) {
                handleLoggedInUI();
            } else if(currentUIState == ATMUIState.Logout) {
                handleLoggedOutUI();
            } else if(currentUIState == ATMUIState.Deposit) {
                handleDepositUI();
            } else if(currentUIState == ATMUIState.Withdraw) {
                handleWithdrawUI();
            } else if(currentUIState == ATMUIState.Transfer) {
                handleTransferUI();
            }
        }
    }

    static void initializeATM() {
        //Populate bank
        setupBank();

        //Create ATM
        atm = new ATM(bank);
        currentUIState = ATMUIState.Login;
        in = new Scanner(System.in);
    }

    static void setupBank() {
        bank = new CentralBank();

        Account acc = new CheckingAccount(100, "a@b.com", "1234");
        bank.getAccounts().put(acc.getID(), acc);
        acc = new CheckingAccount(100, "b@b.com", "1234");
        acc.setFrozen(true);
        bank.getAccounts().put(acc.getID(), acc);
        acc = new CheckingAccount(100, "c@b.com", "1234");
        bank.getAccounts().put(acc.getID(), acc);
        acc = new CheckingAccount(100, "d@b.com", "1234");
        bank.getAccounts().put(acc.getID(), acc);
    }

    static void handleLoginUI() {
        boolean hasLoggedin = false;

        do {
            System.out.println( "*************************************************\n" +
                                "               Welcome to ATM                    \n" +
                                "     Please input account ID and Password        \n");

            System.out.print("ID: ");
            String id = in.next();
            System.out.print("\nPassword: ");
            String pass = in.next();
            if(atm.confirmCredentials(id, pass)) {
                currentAccount = atm.getAccount(id);
                if(atm.getAccount(id).isFrozen) {
                    currentUIState = ATMUIState.Frozen;
                    return;
                } else {
                    currentUIState = ATMUIState.MainLoggedIn;
                    return;
                }
            } else {
                System.out.println("Incorrect ID and/or Password");
            }
        } while(!hasLoggedin);
    }

    static void handleFrozenUI() {
        boolean hasLoggedOut = false;

        do {
            System.out.println( "*************************************************\n" +
                                "               Your account is Frozen            \n" +
                                "     You are not allowed to take any action      \n" +
                                "        Please contact Customer Service at       \n" +
                                "                  1-888-555-1212                 \n");

            System.out.print("Enter 'Logout' to logout: ");
            String entry = in.next();
            if(entry.toLowerCase().equals("logout")) {
                currentUIState = ATMUIState.Logout;
                return;
            }
        } while(!hasLoggedOut);
    }

    static void handleLoggedInUI() {
        boolean hasLoggedOut = false;

        do {
            System.out.println( "*************************************************\n" +
                    "               Welcome " + currentAccount.getID() + "            \n" +
                    "        You may now Withdraw, Deposit, Transfer, or Logout      \n" +
                    "        Please enter the number corresponding to your choice       \n" +
                    "                                  \n");

            System.out.print("Enter your choice (1 = Withdraw, 2 = Deposit, 3 = Transfer, 4 = Logout): ");
            int entry = in.nextInt();
            if(entry == 1) {
                currentUIState = ATMUIState.Withdraw;
                return;
            } else if(entry == 2) {
                currentUIState = ATMUIState.Deposit;
                return;
            } else if(entry == 3) {
                currentUIState = ATMUIState.Transfer;
                return;
            } else if(entry == 4) {
                currentUIState = ATMUIState.Logout;
                return;
            } else {
                System.out.println("Incorrect Input");
            }
        } while(!hasLoggedOut);
    }

    static void handleLoggedOutUI() {
        boolean hasLoggedOut = false;

        do {
            System.out.println( "*************************************************\n" +
                    "               Thank you for your service            \n" +
                    "                  Enter 'Done' to exit                 \n");

            System.out.print("Enter 'Done' to exit: ");
            String entry = in.next();
            if(entry.toLowerCase().equals("done")) {
                currentAccount = null;
                currentUIState = ATMUIState.Login;
                return;
            }
        } while(!hasLoggedOut);
    }

    static void handleWithdrawUI() {
        System.out.println("Withdraw");
        currentUIState = ATMUIState.MainLoggedIn;
    }

    static void handleDepositUI() {
        System.out.println("Deposit");
        currentUIState = ATMUIState.MainLoggedIn;
    }

    static void handleTransferUI() {
        System.out.println("Transfer");
        currentUIState = ATMUIState.MainLoggedIn;
    }
}
