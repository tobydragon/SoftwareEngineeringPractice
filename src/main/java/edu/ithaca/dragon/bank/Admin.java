package edu.ithaca.dragon.bank;

import java.util.Collection;

public class Admin implements AdminAPI {


    @Override
    public double calcTotalAssets() {
        return 0;
    }

    @Override
    public Collection<String> findAcctIdsWithSuspiciousActivity() {
        return null;
    }

    @Override
    public void freezeAccount(String acctId) {

    }

    @Override
    public void unfreezeAcct(String acctId) {

    }
}
