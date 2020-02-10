package edu.ithaca.dragon.bank;

import java.math.BigDecimal;

public class CheckingAccount extends Account {


    public CheckingAccount(String ID, double balance){
        super(ID, balance);
    }
    public void deposit(double amount) throws IllegalArgumentException{
        if(!isAmountValid(amount)) throw new IllegalArgumentException("invalid amount");
        if(amount <= 0) throw new IllegalArgumentException("amount cannot be negitive or zero");
        balance+=amount;
    }
    public void withdraw(double amount) throws InsufficientFundsException, IllegalArgumentException{
        if(!CheckingAccount.isAmountValid(amount))throw new IllegalArgumentException("Not a valid Amount");
        if(Double.compare(amount, 0.0)==0)throw new IllegalArgumentException("Cannot Withdraw zero dollars");
        if(Double.compare(this.balance-amount, 0.0)==-1)throw new InsufficientFundsException("Not enough Funds");
        else this.balance -= amount;


    }
    public void transfer(Account transferTo, double amount) throws InsufficientFundsException, IllegalArgumentException{
        this.withdraw(amount);
        transferTo.deposit(amount);
    }
}
