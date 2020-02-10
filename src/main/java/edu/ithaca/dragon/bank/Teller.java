package edu.ithaca.dragon.bank;

import java.util.Collection;

public class Teller extends ATM implements AdvancedAPI {

    public Teller(Collection<BankAccount> accounts, Collection<User> users) {
        super(accounts, users);
    }

    public void createAccount(String acctId, double startingBalance) {

    }


    public void closeAccount(String acctId) {

    }
}
