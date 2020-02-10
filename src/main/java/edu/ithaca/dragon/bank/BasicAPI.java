package edu.ithaca.dragon.bank;

//API to be used by ATMs
public interface BasicAPI {

    boolean confirmCredentials(String acctId, String password);

    double checkBalance(String acctId,  BankAccount[] customerCollection);

    void withdraw(String acctId, double amount, BankAccount[] customerCollection) throws InsufficientFundsException;

    void deposit(String acctId, double amount, BankAccount[] customerCollection) throws IllegalArgumentException;

    void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException;

    String transactionHistory(String acctId);

}
