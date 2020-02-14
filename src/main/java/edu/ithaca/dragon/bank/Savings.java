package edu.ithaca.dragon.bank;

import java.util.ArrayList;
import java.util.List;

public class Savings implements Account{

    private String acctId;
    private String name;
    private String password;
    private double balance;
    private boolean frozen;
    private List<String> history;
    private double interestRate;
    private double maxWithdrawal;

    public Savings(String acctIdIn, String nameIn, String passwordIn, double startingBalance, double interestRateIn, double maxWithdrawalIn){
        if (acctIdIn.length() != 10){
            throw new IllegalArgumentException("Account ID must be 10 digits");
        }
        if (startingBalance < 0){
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        String checkDouble = Double.toString(startingBalance);
        int indexDecimal = checkDouble.indexOf('.');
        if (!(checkDouble.length() - indexDecimal <= 3)){
            throw new IllegalArgumentException("Balance cannot have more than 2 decimal places");
        }
        if (!isAmountValid(interestRateIn)){
            throw new IllegalArgumentException("Interest rate cannot be less than or equal to 0");
        }
        if (!isAmountValid(maxWithdrawalIn)){
            throw new IllegalArgumentException("Max withdrawal cannot be less than or equal to 0");
        }
        if (!isNameValid(nameIn)){
            throw new IllegalArgumentException("Invalid name");
        }
        acctId = acctIdIn;
        name = nameIn;
        password = passwordIn;
        balance = startingBalance;
        interestRate = interestRateIn;
        maxWithdrawal = maxWithdrawalIn;
        frozen = false;
        history = new ArrayList<String>();
    }

    public String getAcctId(){
        return acctId;
    }

    public String getName(){
        return name;
    }

    public boolean getFrozenStatus(){
        return frozen;
    }

    public String getAcctPassword(){
        return password;
    }

    public double checkBalance(String acctId) throws AcctFrozenException {
        if (frozen){
            throw new AcctFrozenException("Account is frozen");
        }
        if (acctId != this.acctId){
            throw new IllegalArgumentException("This is not the correct account");
        }
        return balance;
    }

    public void withdraw(String acctId, double amount) throws InsufficientFundsException, AcctFrozenException {
        if (frozen){
            throw new AcctFrozenException("Account is frozen");
        }
        if (acctId != this.acctId){
            throw new IllegalArgumentException("This is not the correct account");
        }
        if (amount > balance){
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        }
        if (!isAmountValid(amount)){
            throw new IllegalArgumentException("Cannot withdraw 0 or less");
        }
        if (amount > maxWithdrawal){
            throw new IllegalArgumentException("Amount exceeds daily maximum withdrawal amount");
        }
        balance -= amount;
        history.add("withdrawal of " + amount);
    }

    public void deposit(String acctId, double amount) throws AcctFrozenException {
        if (frozen){
            throw new AcctFrozenException("Account is frozen");
        }
        if (acctId != this.acctId){
            throw new IllegalArgumentException("This is not the correct account");
        }
        if (!isAmountValid(amount)){
            throw new IllegalArgumentException("Cannot deposit 0 or less");
        }        
        balance += amount;
        history.add("deposit of " + amount);
    }


    public String transactionHistory(String acctId) throws AcctFrozenException {
        if (frozen){
            throw new AcctFrozenException("Account is frozen");
        }
        if (acctId != this.acctId){
            throw new IllegalArgumentException("This is not the correct account");
        }
        String acctHistory = "";
        for (int i = 0; i < history.size()-1; i++){
            acctHistory += history.get(i) + "; ";
        }
        acctHistory += history.get(history.size()-1);
        return acctHistory;
    }

    public void compoundInterest(String acctId) throws AcctFrozenException {
        if (frozen){
            throw new AcctFrozenException("Account is frozen");
        }
        if (acctId != this.acctId){
            throw new IllegalArgumentException("This is not the correct account");
        }
        balance += (balance * (interestRate/100));
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
        if (amount <= 0){
            return false;
        }

        // check number of decimal places
        String checkDouble = Double.toString(amount);
        int indexDecimal = checkDouble.indexOf('.');
        return checkDouble.length() - indexDecimal <= 3;
    }

    public void setFrozen(boolean value){
        this.frozen = value;
    }

}
