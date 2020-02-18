package edu.ithaca.dragon.bank;


import java.util.Scanner;

public class ATMUI {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String cmd;
        String userName = null;
        ATM atm = ATMTest();
        BasicAPI testATM = atm;
        do {
            System.out.println("Please Enter User Name: ");
            cmd = input.next();
            if (!cmd.toLowerCase().equals("exit")) {
                System.out.println("Please Enter Password: ");
                String pass = input.next();
                try {
                    if (testATM.confirmCredentials(cmd, pass)) userName = cmd;
                    else System.out.println("User name and password do not match.");
                } catch (IllegalArgumentException e) {
                    userName = null;
                    System.out.println("Invalid User Name: " + cmd);
                }
            }
            if (userName != null) {
                do {
                    if (testATM.isFrozen(userName)) {
                        System.out.println("Account Frozen. Call Customer support at 1-888-555-1212");
                        break;
                    }
                    System.out.printf("Balance: %.2f\n", testATM.checkBalance(userName));
                    System.out.println("Please Choose a following action:");
                    System.out.println("1. Deposit");
                    System.out.println("2. Withdraw");
                    System.out.println("3. Transfer");
                    System.out.println("4. Logout");
                    cmd = input.next().toLowerCase();
                    switch (cmd) {
                        case "deposit":
                        case "1":
                            try {
                                testATM.deposit(userName, getAmount());
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "withdraw":
                        case "2":
                            try {
                                testATM.withdraw(userName, getAmount());
                            } catch (InsufficientFundsException e) {
                                System.out.println(e.getMessage());
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "transfer":
                        case "3":
                            System.out.println("Enter Account ID to transfer To: ");
                            String transferTo = input.next();
                            try {
                                testATM.transfer(userName, transferTo, getAmount());
                            } catch (InsufficientFundsException e) {
                                System.out.println(e.getMessage());
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                    }
                } while (!cmd.toLowerCase().equals("logout") && !cmd.toLowerCase().equals("4"));
            }
            userName = null;
        } while (!cmd.toLowerCase().equals("exit"));
    }

    private static double getAmount() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please Enter Amount: ");
        String amountStr = in.next();
        double amount;
        while (true) {
            try {
                amount = Double.parseDouble(amountStr);
                return amount;
            } catch (NumberFormatException e) {
                System.out.println("Please Enter Numerical Value: ");
                amountStr = in.next();
            }
        }
    }
    public static ATM ATMTest() {
        CustomerCollection c1 = new CustomerCollection();
        c1.addCustomer("mdiallo", "password");
        c1.createAccount("mdiallo", 100);
        c1.addCustomer("def", "xyz");
        c1.createAccount("def", 300);
        c1.toggleFreeze("def");
        c1.addCustomer("abc", "xyz");
        c1.createAccount("abc", 20000);

        return new ATM(c1);
    }
}
