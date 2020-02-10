package edu.ithaca.dragon.bank;

import java.util.Collection;

public interface AdminAPI { //sure

    public double calcTotalAssets();

    public Collection<String> findAcctIdsWithSuspiciousActivity();

    public void freezeAccount(String acctId);

    public void unfreezeAcct(String acctId);

    public boolean checkAccountExists(String acctID);

    public boolean checkFrozenAccountExists(String acctID);

}
