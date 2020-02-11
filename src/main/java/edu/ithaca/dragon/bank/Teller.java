package edu.ithaca.dragon.bank;

public class Teller extends ATM implements AdvancedAPI {

    @Override
    public void createAccount(String acctId, double startingBalance) {
        centralBank.accounts.add(new CheckingAccount(startingBalance, acctId));
    }

    @Override
    public void closeAccount(String acctId) {

    }
}
