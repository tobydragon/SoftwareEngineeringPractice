package edu.ithaca.dragon.bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AtmUI {

    private BasicAPI atm;
    private String currentId;

    public AtmUI(BasicAPI atm) {
        this.atm = atm;
        this.currentId = null;
    }

    private void exit() {
        System.out.println("Goodbye");
        System.exit(0);
    }

    private void login(BufferedReader reader) {
        boolean loggedIn = false;
        while (!loggedIn) {
            try {
                System.out.println("Please enter your account Id");
                String acctId = reader.readLine();
                if (acctId.equals("exit")) exit();

                System.out.println("Please enter your password");
                String password = reader.readLine();
                if (password.equals("exit")) exit();

                loggedIn = atm.confirmCredentials(acctId, password);
                if (loggedIn) currentId = acctId;
            }
            catch (IOException e) {
                System.out.println("Input error. Exiting.");
                System.exit(1);
            }
            catch (AccountDoesNotExistException e) {
                // Added Josue
                //System.out.println("Account error, bad credentials and try again");
                // this will give duplicate error messages with the finally
            }
            finally {
                if (!loggedIn) {
                    System.out.println("Login information incorrect. Please try again.");
                }
            }
        }
    }

    private void deposit(BufferedReader reader) {
        boolean transactionComplete = false;
        while (!transactionComplete) {
            try {
                System.out.println("Enter the amount to be deposited.");
                String amtStr = reader.readLine();
                if (amtStr.equals("exit")) exit();

                Double amt = Double.parseDouble(amtStr);
                atm.deposit(currentId, amt);
                transactionComplete = true;
            }
            catch (IOException e) {
                System.out.println("Input error. Exiting.");
                System.exit(1);
            }
            catch (AccountDoesNotExistException e) {
                // Added Josue
                System.out.println("Account error, bad credentials and try again");
            }
            catch (AccountFrozenException e) {
                System.out.println("You cannot deposit, account is frozen.");
                transactionComplete = true;
            }
            catch (IllegalArgumentException e){}
            finally {
                if (!transactionComplete) {
                    System.out.println("Invalid amount. Please try again.");
                }
            }
        }
    }

    private void withdraw(BufferedReader reader) {
        boolean transactionComplete = false;
        while (!transactionComplete) {
            try {
                System.out.println("Enter the amount to be withdrawn.");
                String amtStr = reader.readLine();
                if (amtStr.equals("exit")) exit();

                Double amt = Double.parseDouble(amtStr);
                atm.withdraw(currentId, amt);
                transactionComplete = true;
            }
            catch (IOException e) {
                System.out.println("Input error. Exiting.");
                System.exit(1);
            }
            catch (AccountDoesNotExistException e) {
                // Added Josue
                System.out.println("Account error, bad credentials and try again");
            }
            catch (AccountFrozenException e) {
                System.out.println("You cannot withdraw, account is frozen.");
                transactionComplete = true;
            }
            catch (InsufficientFundsException e) {
                System.out.println("Insufficient funds. Transaction cancelled.");
                transactionComplete = true;
            }
            catch (ExceedsMaxWithdrawalException e) {
                System.out.println("Exceeds maximum withdrawal. Transaction cancelled.");
                transactionComplete = true;
            }
            catch (IllegalArgumentException e){}
            finally {
                if (!transactionComplete) {
                    System.out.println("Invalid amount. Please try again.");
                }
            }
        }
    }

    private void transfer(BufferedReader reader) {
        boolean transactionComplete = false;
        while (!transactionComplete) {
            try {
                System.out.println("Enter the account id to transfer to.");
                String acctId = reader.readLine();
                if (acctId.equals("exit")) exit();
                if (acctId.equals(currentId)) {
                    System.out.println("You cannot transfer funds to yourself");
                    transactionComplete = true;
                    break;
                }

                System.out.println("Enter the amount to be transfered.");
                String amtStr = reader.readLine();
                if (amtStr.equals("exit")) exit();

                Double amt = Double.parseDouble(amtStr);
                atm.transfer(currentId, acctId, amt);
                transactionComplete = true;
            }
            catch (IOException e) {
                System.out.println("Input error. Exiting.");
                System.exit(1);
            }
            catch (AccountDoesNotExistException e) {
                // Added Josue
                System.out.println("Account error, bad credentials and try again");
            }
            catch (AccountFrozenException e) {
                System.out.println("You cannot transfer, account is frozen.");
                transactionComplete = true;
            }
            catch (InsufficientFundsException e) {
                System.out.println("Insufficient funds. Transaction cancelled.");
                transactionComplete = true;
            }
            catch (ExceedsMaxWithdrawalException e) {
                System.out.println("Exceeds maximum withdrawal. Transaction cancelled.");
                transactionComplete = true;
            } catch (IllegalArgumentException e){}
            finally {
                if (!transactionComplete) {
                    System.out.println("Invalid amount. Please try again.");
                }
            }
        }
    }

    private void logout() {
        currentId = null;
    }

    public void run() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the ATM. Type \"exit\" at any point to shut down.");

        boolean running = true;

        while (running) {
            login(reader);

            while (currentId != null) {
                try {
                    if (atm.isFrozen(currentId)) {
                        System.out.println("This account has been frozen and cannot be accessed" +
                                " For assistance, call customer service at 1-888-555-1212");
                    }
                    System.out.println("Your balance is " + String.format("%.2f", atm.checkBalance(currentId)));
                    System.out.println("Enter a command: deposit, withdraw, transfer or logout");
                    String command = reader.readLine();

                    if (command.equals("deposit")) deposit(reader);
                    else if (command.equals("withdraw")) withdraw(reader);
                    else if (command.equals("transfer")) transfer(reader);
                    else if (command.equals("logout")) logout();
                    else if (command.equals("exit")) exit();
                    else System.out.println("Invalid command. Try again.");
                }
                catch (IOException e) {
                    System.out.println("Input error. Exiting.");
                    System.exit(1);
                }
                catch (AccountDoesNotExistException e) {
                    // Added Josue
                    System.out.println("Account error, bad credentials and try again");
                }
            }
        }
    }

}
