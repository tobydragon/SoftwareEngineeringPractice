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

    public double checkBalance(String acctId){
        if (acctId != this.acctId){
            throw new IllegalArgumentException("This is not the correct account");
        }
        return balance;
    }

    public void withdraw(String acctId, double amount) throws InsufficientFundsException{
        if (acctId != this.acctId){
            throw new IllegalArgumentException("This is not the correct account");
        }
        if (amount > balance){
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        }
        if (amount <= 0){
            throw new IllegalArgumentException("Cannot withdraw 0 or less");
        }
        if (amount > maxWithdrawal){
            throw new IllegalArgumentException("Amount exceeds daily maximum withdrawal amount");
        }
        balance -= amount;
        history.add("withdrawal of " + amount);
    }

    public void deposit(String acctId, double amount){
        if (acctId != this.acctId){
            throw new IllegalArgumentException("This is not the correct account");
        }
        if (amount <= 0){
            throw new IllegalArgumentException("Cannot deposit 0 or less");
        }        
        balance += amount;
        history.add("deposit of " + amount);
    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException{
        //todo
    }

    public String transactionHistory(String acctId){
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

    public void compoundInterest(String acctId){
        if (acctId != this.acctId){
            throw new IllegalArgumentException("This is not the correct account");
        }
        balance += (balance * (interestRate/100));
    }

    public void freezeOrUnfreezeAccount(String acctId){
        //todo
    }

}
