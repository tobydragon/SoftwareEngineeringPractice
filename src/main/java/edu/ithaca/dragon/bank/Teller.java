package edu.ithaca.dragon.bank;

import java.rmi.NoSuchObjectException;
import java.util.NoSuchElementException;

public class Teller extends ATM implements AdvancedAPI {

    public Teller(CentralBank bank) {
        super(bank);
    }

    @Override
    public void createAccount(String acctId, double startingBalance) {
        centralBank.getAccounts().put(acctId, new CheckingAccount(startingBalance, acctId, "password")); //default password for account created by teller is password, user can change that if they want
    }

    @Override
    public void closeAccount(String acctId) {
        Account account = centralBank.getAccounts().remove(acctId);
        if (account == null) {
            throw new NoSuchElementException("That account does not exist");
        }
    }
}
