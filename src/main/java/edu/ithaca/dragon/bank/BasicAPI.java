package edu.ithaca.dragon.bank;

//API to be used by ATMs
public interface BasicAPI {

    boolean confirmCredentials(int acctId, String password);

    double checkBalance(int userID) throws NonExistentAccountException;

    void withdraw(int acctId, double amount) throws InsufficientFundsException, NonExistentAccountException;

    void deposit(int acctId, double amount);

    void transfer(int userIDFrom, int acctIdToWithdrawFrom, int userIDTo, int acctIdToDepositTo, double amount) throws InsufficientFundsException;

    String transactionHistory(int acctId);

}
