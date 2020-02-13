package edu.ithaca.dragon.bank;

public class ATM implements BasicAPI {

    protected CentralBank centralBank;

    public ATM(CentralBank bank) {
        centralBank = bank;
    }

    @Override
    public boolean confirmCredentials(String acctId, String password) {
        return false;
    }

    @Override
    public double checkBalance(String acctId) {

        return centralBank.checkBalance(acctId);
    }

    @Override
    public void withdraw(String acctId, double amount) throws AccountFrozenException, InsufficientFundsException {
        centralBank.withdraw(acctId, amount);
    }

    @Override
    public void deposit(String acctId, double amount) throws AccountFrozenException {
        centralBank.deposit(acctId, amount);
    }

    @Override
    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException, AccountFrozenException {
        centralBank.transfer(acctIdToWithdrawFrom, acctIdToDepositTo, amount);
    }

    @Override
    public String transactionHistory(String acctId) {
        return null;
    }

    public CentralBank getCentralBank() {
        return centralBank;
    }
}
