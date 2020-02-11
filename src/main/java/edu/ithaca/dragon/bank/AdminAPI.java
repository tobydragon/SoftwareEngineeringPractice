package edu.ithaca.dragon.bank;

import java.util.Collection;

public interface AdminAPI {

    public double calcTotalAssets();

    public Collection<Integer> findAcctIdsWithSuspiciousActivity();

    public void freezeAccount(int acctId);

    public void unfreezeAcct(int acctId);

}
