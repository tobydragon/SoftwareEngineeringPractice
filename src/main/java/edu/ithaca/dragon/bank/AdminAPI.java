package edu.ithaca.dragon.bank;

import java.util.Collection;
import java.util.Map;

public interface AdminAPI {

    public double calcTotalAssets() throws AccountDoesNotExistException;

    public Collection<String> findAcctIdsWithSuspiciousActivity();

    public Collection<String> confirmActivity(Map<String, String> accountResponses) throws IllegalArgumentException; //updates suspicious account list based on account owner check

    public void freezeAccount(String acctId) throws AccountDoesNotExistException;

    public void unfreezeAcct(String acctId) throws AccountDoesNotExistException;

}
