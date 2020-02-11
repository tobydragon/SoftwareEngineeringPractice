package edu.ithaca.dragon.bank;

import java.rmi.NoSuchObjectException;
import java.util.NoSuchElementException;

public class Teller extends ATM implements AdvancedAPI {

    @Override
    public void createAccount(String acctId, double startingBalance) {
        centralBank.getAccounts().put(acctId, new CheckingAccount(startingBalance, acctId));
    }

    @Override
    public void closeAccount(String acctId) {
        Account account = centralBank.getAccounts().remove(acctId);
        if (account == null) {
            throw new NoSuchElementException("That account does not exist");
        }
    }
}
