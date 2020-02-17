package edu.ithaca.dragon.bank;

import java.util.HashMap;
import java.util.Iterator;

public class ATM implements BasicAPI {

    protected CentralBank centralBank;

    public ATM(CentralBank bank) {
        centralBank = bank;
    }

    @Override
    public boolean confirmCredentials(String acctId, String password) {
        HashMap<String, Account> accounts = (HashMap<String, Account>) centralBank.getAccounts();
        if (!accounts.containsKey(acctId)){
            return false;
        }
        else{
            Account account = accounts.get(acctId);
            if (account.getPassword().equals(password)){
                return true;
            }
            else{
                return false;
            }
        }


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
    public Account getAccount(String acctID) {
        if(centralBank.getAccounts().containsKey(acctID)) {
            return centralBank.getAccounts().get(acctID);
        } else {
            throw new IllegalArgumentException("Account does not exist");
        }
    }

    @Override
    public String transactionHistory(String acctId) {
        return null;
    }

    public CentralBank getCentralBank() {
        return centralBank;
    }
}
