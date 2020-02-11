package edu.ithaca.dragon.bank;

import java.util.ArrayList;
import java.util.List;

public class Savings implements Account{

    private String acctId;
    private String name;
    private String password;
    private double balance;
    private boolean frozen;
    private List<String []> history;
    private double interestRate;
    private double maxWithdrawal;

    public Savings(String acctIdIn, String nameIn, String passwordIn, double startingBalance, double interestRateIn, double maxWithdrawalIn){
        if (acctIdIn.length() != 10){
            throw new IllegalArgumentException("Account ID must be 10 digits");
        }
        if (startingBalance < 0){
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        if (interestRateIn <= 0){
            throw new IllegalArgumentException("Interest rate cannot be less than or equal to 0");
        }
        if (maxWithdrawalIn <= 0){
            throw new IllegalArgumentException("Max withdrawal cannot be less than or equal to 0");
        }
        acctId = acctIdIn;
        name = nameIn;
        password = passwordIn;
        balance = startingBalance;
        interestRate = interestRateIn;
        maxWithdrawal = maxWithdrawalIn;
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
        return balance;
    }

    public void withdraw(String acctId, double amount) throws InsufficientFundsException{
        //todo
    }

    public void deposit(String acctId, double amount){
        balance += amount;
    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException{
        //todo
    }

    public String transactionHistory(String acctId){
        return null;
    }

    public void compoundInterest(String acctId){
        //todo
    }

}
