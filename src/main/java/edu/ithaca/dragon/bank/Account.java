package edu.ithaca.dragon.bank;

public interface Account {

    double checkBalance(String acctId);

    void withdraw(String acctId, double amount) throws InsufficientFundsException, AcctFrozenException;

    void deposit(String acctId, double amount) throws AcctFrozenException;

    String transactionHistory(String acctId);

    void setFrozen(boolean value);

    String getAcctId();

    boolean getFrozenStatus();
    }
