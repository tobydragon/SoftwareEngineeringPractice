package edu.ithaca.dragon.bank;

import java.util.Collection;

public class ATM extends CentralBank implements BasicAPI{


    public ATM(Collection<BankAccount> accounts, Collection<User> users) {
        super(accounts, users);
    }

    public boolean confirmCredentials(String username, String password) {
        return false;
    }

    public double checkBalance(String acctId) {
        return 0;
    }


    public void withdraw(String acctId, double amount) throws InsufficientFundsException {

    }

    public void deposit(String acctId, double amount) {

    }

    public void transfer(String acctIdToWithdrawFrom, String acctIdToDepositTo, double amount) throws InsufficientFundsException {

    }

    public String transactionHistory(String acctId) {
        return null;
    }

}
