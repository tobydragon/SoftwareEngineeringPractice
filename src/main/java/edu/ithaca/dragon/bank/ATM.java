package edu.ithaca.dragon.bank;

public class ATM implements BasicAPI {

    CentralBank centralBank = new CentralBank();

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
