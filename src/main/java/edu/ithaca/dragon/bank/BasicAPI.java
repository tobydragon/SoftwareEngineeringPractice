package edu.ithaca.dragon.bank;

//API to be used by ATMs
public interface BasicAPI {

    boolean confirmCredentials(String acctId, String password) throws AccountNotFoundException;  //Not implemented

    double checkBalance(String acctId) throws AccountNotFoundException;  //Implemented

    void withdraw(String acctId, double amount) throws InsufficientFundsException, AccountNotFoundException;  //Implemented

    void deposit(String acctId, double amount) throws AccountNotFoundException;  //Implemented

    void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException, AccountNotFoundException;  //Implemented

    String transactionHistory(String acctId) throws AccountNotFoundException;  //Not implemented

}
