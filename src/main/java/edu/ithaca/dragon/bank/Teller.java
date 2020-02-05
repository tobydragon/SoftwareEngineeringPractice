package edu.ithaca.dragon.bank;

public class Teller extends ATM implements AdvancedAPI {


    @Override
    public void createAccount(String acctId, double startingBalance) {

    }

    @Override
    public void closeAccount(String acctId) {

    }

    @Override
    public boolean confirmCredentials(String acctId, String password) {
        return false;
    }

    @Override
    public double checkBalance(String acctId) {
        return 0;
    }

    @Override
    public void withdraw(String acctId, double amount) throws InsufficientFundsException {

    }

    @Override
    public void deposit(String acctId, double amount) {

    }

    @Override
    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException {

    }

    @Override
    public String transactionHistory(String acctId) {
        return null;
    }
}
