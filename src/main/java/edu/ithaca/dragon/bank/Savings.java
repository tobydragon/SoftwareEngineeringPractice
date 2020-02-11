package edu.ithaca.dragon.bank;

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
        //todo
    }

    public double checkBalance(String acctId){
        return 0;
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
