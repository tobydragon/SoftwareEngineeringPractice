package edu.ithaca.dragon.bank;

//API to be used by ATMs
public interface BasicAPI {

    boolean confirmCredentials(String acctId, String password) throws AcctFrozenException;

    double checkBalance(String acctId, String password) throws AcctFrozenException;

    void withdraw(String acctId, String password, double amount) throws InsufficientFundsException, AcctFrozenException;

    void deposit(String acctId, String password, double amount) throws AcctFrozenException;

    void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, String passToWithdrawFrom, String passToDepositTo,double amount) throws InsufficientFundsException, AcctFrozenException;

    String transactionHistory(String acctId, String password) throws AcctFrozenException;

}
