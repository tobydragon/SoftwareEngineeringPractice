package edu.ithaca.dragon.bank;

import java.util.Collection;

public class Admin implements AdminAPI {
    Collection<Account> accounts;

    /**
     * creates an Admin account that knows what accounts it controls
     * @param accounts the accounts that Admin must act on
     */
    public Admin(Collection<Account> accounts){
    }

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

    public Collection<Account> getAccounts(){
        return this.accounts;
    }
}
