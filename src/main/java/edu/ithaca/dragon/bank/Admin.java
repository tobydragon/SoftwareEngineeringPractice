package edu.ithaca.dragon.bank;

import java.util.Collection;

public class Admin extends CentralBank implements AdminAPI {

    public Admin(){
        super();
    }

    public double calcTotalAssets() {
        return 0;
    }

    public Collection<String> findAcctIdsWithSuspiciousActivity() {
        return null;
    }

    public void freezeAccount(String acctId) {

    }

    public void unfreezeAcct(String acctId) {

    }
}
