package edu.ithaca.dragon.bank;

import java.util.Collection;

public class Admin implements AdminAPI {
    Collection<BankAccount> accounts;

    public Admin(Collection<BankAccount> accounts){
        this.accounts=accounts;
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
