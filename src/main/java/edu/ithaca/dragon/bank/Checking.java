package edu.ithaca.dragon.bank;

import java.util.ArrayList;
import java.util.List;

public class Checking implements Account {
    private String acctId;
    private String name;
    private String password;
    private double balance;
    private boolean frozen;
    private List<String[]> history;

    public Checking(String acctIdIn, String nameIn, String passwordIn, double startingBalance) throws IllegalArgumentException {
        if (acctIdIn.length() == 10) {
            this.acctId = acctIdIn;
        } else throw new IllegalArgumentException("Account ID must be 10 digits");

        if (isNameValid(nameIn)) {
            this.name = nameIn;
        } else throw new IllegalArgumentException("Name is not valid");

        if (isAmountValid(startingBalance)) {
            this.balance = startingBalance;
        } else throw new IllegalArgumentException("Starting balance is invalid");

        this.password = passwordIn;
        frozen = false;
        history = new ArrayList<String[]>();
    }

    public String getAcctId(){
        return acctId;
    }

    public String getName(){
        return name;
    }

    public double checkBalance(String acctId){
        return this.balance;
    }

    public void withdraw(String acctId, double amount) throws InsufficientFundsException{
        if(amount > balance){
            throw new InsufficientFundsException("Cannot withdraw amount greater than balance");
        }
        if(!isAmountValid(amount)){
            throw new IllegalArgumentException("Amount is not valid");
        }
        else{
            balance -= amount;
        }
    }

    public void deposit(String acctId, double amount){
        if(isAmountValid(amount)){
            balance += amount;
        }
        else{
            throw new IllegalArgumentException("Amount is not valid");
        }
    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException{
        /*if(amount > balance){
            throw new InsufficientFundsException("Amount cannot be greater than account balance");
        }
        else{
            withdraw(acctIdToWithdrawFrom, amount);
            deposit(acctIdToDepositTo, amount);
        }*/
    }

    public String transactionHistory(String acctId){
        return "";
    }

    public static boolean isNameValid(String name){
        if (name.length() < 3) return false;

        int spaceCount = 0;
        //check there's at least one space
        for (int i = 0; i < name.length()-1; i++){
            if(name.charAt(i) == ' '){
                spaceCount++;
            }
        }
        if(spaceCount < 1) return false;

        for (int i = 0; i < name.length(); i++){
            if(Character.isLetter(name.charAt(i)) || name.charAt(i) == ' '){
                continue;
            }
            return false;
        }
        return true;
    }

    public static boolean isAmountValid(double amount){
        if (amount < 0){
            return false;
        } else if (amount == 0) {
            return true;
        }

        // check number of decimal places
        String checkDouble = Double.toString(amount);
        int indexDecimal = checkDouble.indexOf('.');
        return checkDouble.length() - indexDecimal <= 3;
    }
}
