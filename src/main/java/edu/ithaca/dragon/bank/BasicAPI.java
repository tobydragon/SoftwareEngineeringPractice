package edu.ithaca.dragon.bank;

//API to be used by ATMs
public interface BasicAPI {

    boolean confirmCredentials(String acctId, String password) throws AcctFrozenException;

    double checkBalance(String acctId) throws AcctFrozenException;

    void withdraw(String acctId, double amount) throws InsufficientFundsException, AcctFrozenException;

    void deposit(String acctId, double amount) throws AcctFrozenException;

    void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException, AcctFrozenException;

    String transactionHistory(String acctId) throws AcctFrozenException;

}
