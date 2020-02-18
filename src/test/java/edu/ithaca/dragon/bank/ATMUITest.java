package edu.ithaca.dragon.bank;

import java.util.Scanner;

public class ATMUITest {
    public static void main(String[] args) throws AcctFrozenException, InsufficientFundsException {
        Admin admin = AdminTest.createTestAdmin();
        String idIn, passIn, input, amountIn, otherAcctIn, otherPassIn = "";
        Account currAcct;
        Scanner in = new Scanner(System.in);
        boolean validLogin = false;
        boolean validInput= false;
        double amount = 0.0;

        //loop until valid credentials are entered
        while(!validLogin) {
            System.out.println("\nWelcome to the 345 ATM. Please log in with your credentials.");
            System.out.print("Account ID: ");
            idIn = in.nextLine();
            System.out.print("Password: ");
            passIn = in.nextLine();

            try {
                admin.atm.confirmCredentials(idIn, passIn);
            }
            catch(IllegalArgumentException e){
                validLogin = false;
                System.out.println("--Invalid credentials. Please try again.--");
                continue;
            }
            validLogin = true;
            currAcct = admin.getAccount(idIn);
            if (validLogin){
                if(currAcct.getFrozenStatus()){
                    System.out.println("Your account is frozen. You are unable to carry out any transactions." +
                            "\nPlease contact customer service for more details at 1-888-555-1212.");
                }
                else{
                    while(validLogin){
                        System.out.println("\nHello, " + currAcct.getName());
                        System.out.println("Your current balance is $" + currAcct.checkBalance(idIn));
                        System.out.println("What would you like to do today? \nPress w to withdraw, d to deposit, t to transfer or x to log out.");
                        input = in.nextLine();
                        validInput = false;

                        if(input.equals("w")){
                            while(!validInput){
                                System.out.print("Enter the amount for withdrawal: ");
                                amountIn = in.nextLine();
                                amount = Double.parseDouble(amountIn);
                                try{
                                    currAcct.withdraw(idIn, amount);
                                }
                                catch(IllegalArgumentException e){
                                    System.out.println("--Not a valid amount--");
                                    continue;
                                }
                                catch(InsufficientFundsException e){
                                    System.out.println("--Cannot withdraw amount greater than balance--");
                                    continue;
                                }
                                System.out.println("$" + amountIn + " was withdrawn from your account.");
                                validInput = true;
                            }
                        }
                        else if (input.equals("d")){
                            while(!validInput){
                                System.out.print("Enter amount to deposit: ");
                                amountIn = in.nextLine();
                                amount = Double.parseDouble(amountIn);

                                try{
                                    currAcct.deposit(idIn, amount);
                                }
                                catch(IllegalArgumentException e){
                                    System.out.println("--Not a valid amount--");
                                    continue;
                                }
                                System.out.println("$" + amountIn + " was deposited into your account.");
                                validInput = true;
                            }
                        }
                        else if (input.equals("t")){
                            while(!validInput){
                                System.out.print("Enter account ID of the account you would like to transfer to: ");
                                otherAcctIn = in.nextLine();

                                System.out.print("Enter password of the account you would like to transfer to: ");
                                otherPassIn = in.nextLine();

                                try{
                                    admin.atm.confirmCredentials(otherAcctIn, otherPassIn);
                                }
                                catch(IllegalArgumentException e){
                                    System.out.println("--The account does not exist.--");
                                    continue;
                                }
                                if (!admin.atm.confirmCredentials(otherAcctIn, otherPassIn)) {
                                    System.out.println("--The credentials for the other account are invalid.--");
                                    continue;
                                }

                                System.out.print("Enter amount to transfer: ");
                                amountIn = in.nextLine();
                                amount = Double.parseDouble(amountIn);

                                try{
                                    //System.out.println(idIn + otherAcctIn + passIn + otherPassIn + amount);
                                    admin.atm.transfer(idIn, otherAcctIn, passIn, otherPassIn, amount);
                                }
                                catch(IllegalArgumentException e){
                                    System.out.println("--Not a valid amount--");
                                    continue;
                                }
                                catch(InsufficientFundsException e){
                                    System.out.println("--Your account does not have enough funds.--");
                                    continue;
                                }
                                catch(AcctFrozenException e){
                                    System.out.println("--The account you're attempting to transfer to appears to be frozen.--");
                                    validInput = true;
                                    continue;
                                }
                                System.out.println("$" + amountIn + " was transferred into " + otherAcctIn + ".");
                                validInput = true;
                            }

                        }
                        else if (input.equals("x")){
                            validLogin = false;
                        }
                        else{
                            System.out.println("Please enter a valid key.");
                            continue;
                        }
                    }
                }

            }
        }

        in.close();
    }
}
