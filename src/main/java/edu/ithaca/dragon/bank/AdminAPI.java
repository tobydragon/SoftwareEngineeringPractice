package edu.ithaca.dragon.bank;

import java.util.Collection;

public interface AdminAPI {

    public double calcTotalAssets() throws AccountDoesNotExistException;

    public Collection<String> findAcctIdsWithSuspiciousActivity();

    public void freezeAccount(String acctId) throws AccountDoesNotExistException;

    public void unfreezeAcct(String acctId) throws AccountDoesNotExistException;

}
