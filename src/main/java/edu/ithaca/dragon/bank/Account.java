package edu.ithaca.dragon.bank;

public interface Account {

    double checkBalance(String acctId);

    void withdraw(String acctId, double amount) throws InsufficientFundsException;

    void deposit(String acctId, double amount);

    void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException;

    String transactionHistory(String acctId);

}
