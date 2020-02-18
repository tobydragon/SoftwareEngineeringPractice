package edu.ithaca.dragon.bank;

import com.sun.javaws.exceptions.InvalidArgumentException;

public class ATMUI {
    protected CentralBank bank;
    protected String account;

    ATMUI(CentralBank bank){
        this.bank = bank;
        this.account = "";
    }

    public String parse(String input){
        String output = "";
        String[] inputArray = input.split(" ");
        inputArray[0] = inputArray[0].toLowerCase();

        if(inputArray[0].equals("login") && account.equals("")) {
            try {
                if(bank.confirmCredentials(inputArray[1], inputArray[2])) {
                    output = "Login successful.  ";
                    account = inputArray[1];
                }
                else output = "Could not log in to the given account!  ";
            } catch(AccountNotFoundException e){
                output = "Could not log in to the given account!  ";
            }
        }
        else if(inputArray[0].equals("quit") && account.equals("")) {
            return "quit";
        }
        else if(inputArray[0].equals("withdraw") && !account.equals("")) {
            try {
                bank.withdraw(account, Double.parseDouble(inputArray[1]));
                output = "Your new balance is: " + bank.checkBalance(account) + "  ";
            } catch(Exception e){
                if(e instanceof IllegalArgumentException)
                    output = "Cannot withdraw a negative number!  ";
                else if(e instanceof InsufficientFundsException)
                    output = "Cannot withdraw more than is in your account!  ";
                else output = "Error: " + e.getMessage() + "  ";
            }
        }
        else if(inputArray[0].equals("deposit") && !account.equals("")) {
            try {
                bank.deposit(account, Double.parseDouble(inputArray[1]));
                output = "Your new balance is: " + bank.checkBalance(account) + "  ";
            } catch(Exception e){
                if(e instanceof IllegalArgumentException)
                    output = "Cannot deposit a negative number!  ";
                else output = "Error: " + e.getMessage() + "  ";
            }
        }
        else if(inputArray[0].equals("transfer") && !account.equals("")) {
            try {
                bank.transfer(account, inputArray[2], Double.parseDouble(inputArray[1]));
                output = "Your new balance is: " + bank.checkBalance(account) + "  ";
            } catch(Exception e){
                if(e instanceof IllegalArgumentException)
                    output = "Cannot transfer a negative number!  ";
                else if(e instanceof InsufficientFundsException)
                    output = "Cannot transfer more than you have in your account!  ";
                else if(e instanceof AccountNotFoundException)
                    output = "Cannot find the account to transfer to!  ";
                else output = "Error: " + e.getMessage() + "  ";
            }
        }
        else if(inputArray[0].equals("logout") && !account.equals("")) {
            account = "";
            output = "Logout successful  ";
        }
        else output = "Command not recognized, please try again!  ";

        output += "Please enter any additional commands or ";
        if(account != "")
            return output + "Logout to log out";
        else return output + "Quit to quit";
    }
}
