package edu.ithaca.dragon.bank;

//API to be used by ATMs
public interface BasicAPI {

    boolean confirmCredentials(int acctId, String password);

    double checkBalance(int acctId);

    void withdraw(int acctId, double amount) throws InsufficientFundsException;

    void deposit(int acctId, double amount);

    void transfer(int acctIdToWithdrawFrom, int acctIdToDepositTo, double amount) throws InsufficientFundsException;

    String transactionHistory(int acctId);

}
