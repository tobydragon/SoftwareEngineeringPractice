package edu.ithaca.dragon.bank;

//API to be used by ATMs
public interface BasicAPI {

    boolean confirmCredentials(String email, String password);

    double checkBalance(String email, String type);

    void withdraw(String email, String type, double amount) throws InsufficientFundsException;

    void deposit(String email, String type, double amount);

    void transfer(String emailToWithdrawFrom, String type1, String emailToDepositTo, String type2, double amount) throws InsufficientFundsException;

    String transactionHistory(String email, String type);

    double checkBalance(String acctId);

    void withdraw(String acctId, double amount) throws InsufficientFundsException;

    void deposit(String acctId, double amount);

    void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException;

    String transactionHistory(String acctId);

    String getAccountId(String email, String type);

    BankAccount findAcct(String AcctId);
}
