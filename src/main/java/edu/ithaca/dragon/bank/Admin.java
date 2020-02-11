package edu.ithaca.dragon.bank;

import java.util.Collection;
import java.util.HashMap;

public class Admin implements AdminAPI {
    private HashMap<String, Account> accounts;

    @Override
    public double calcTotalAssets() {
        return 0;
    }

    @Override
    public Collection<String> findAcctIdsWithSuspiciousActivity() {
        return null;
    }

    @Override
    public void freezeAccount(String acctId) throws IllegalArgumentException {

    }

    @Override
    public void unfreezeAcct(String acctId) throws IllegalArgumentException {

    }
}
