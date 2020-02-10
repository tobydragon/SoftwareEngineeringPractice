package edu.ithaca.dragon.bank;

import java.util.Collection;

public class Admin extends CentralBank implements AdminAPI {

    public Admin(Collection<BankAccount> accounts, Collection<User>users){
        super(accounts,users);
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
