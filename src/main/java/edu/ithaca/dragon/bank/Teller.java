package edu.ithaca.dragon.bank;

public class Teller extends ATM implements AdvancedAPI {

    @Override
    public void createAccount(String acctId, double startingBalance) {
        centralBank.getAccounts().put(acctId, new CheckingAccount(startingBalance, acctId));
    }

    @Override
    public void closeAccount(String acctId) {

    }
}
