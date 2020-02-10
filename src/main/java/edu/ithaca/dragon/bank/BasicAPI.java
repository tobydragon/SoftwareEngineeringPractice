package edu.ithaca.dragon.bank;

//API to be used by ATMs
public interface BasicAPI {

    boolean confirmCredentials(String acctId, String password) throws AccountDoesNotExistException;

    double checkBalance(String acctId) throws AccountDoesNotExistException;

    void withdraw(String acctId, double amount) throws InsufficientFundsException, AccountDoesNotExistException, ExceedsMaxWithdrawalException, AccountFrozenException;

    void deposit(String acctId, double amount) throws AccountDoesNotExistException, AccountFrozenException;

    void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException, AccountDoesNotExistException, ExceedsMaxWithdrawalException, AccountFrozenException;

    String transactionHistory(String acctId) throws AccountDoesNotExistException, AccountAlreadyExistsException, InsufficientFundsException, ExceedsMaxWithdrawalException;

}
