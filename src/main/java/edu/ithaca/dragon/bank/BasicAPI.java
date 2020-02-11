package edu.ithaca.dragon.bank;

//API to be used by ATMs
public interface BasicAPI {

    boolean confirmCredentials(String acctId, String password) throws AccountNotFoundException;  //Not implemented

    double checkBalance(String acctId) throws AccountNotFoundException;  //Not implemented

    void withdraw(String acctId, double amount) throws InsufficientFundsException, AccountNotFoundException;  //Not implemented

    void deposit(String acctId, double amount) throws AccountNotFoundException;  //Not implemented

    void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException, AccountNotFoundException;  //Not implemented

    String transactionHistory(String acctId) throws AccountNotFoundException;  //Not implemented

}
