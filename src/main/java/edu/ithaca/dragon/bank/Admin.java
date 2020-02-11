package edu.ithaca.dragon.bank;

import java.util.Collection;
import java.util.HashMap;

public class Admin implements AdminAPI {
    private HashMap<String, Account> accounts;

    /**
     * Calculates total balance of all accounts held by accounts HashMap
     * @return double of balance in the bank
     */
    @Override
    public double calcTotalAssets() {
        return 0;
    }

    /**
     * Finds accounts with suspicious activity
     * @return list of account IDs with suspicious activity
     */
    @Override
    public Collection<String> findAcctIdsWithSuspiciousActivity() {
        return null;
    }

    /**
     * Freezes account so its methods cannot be used
     * @param acctId string ID of account that needs to be frozen
     * @throws IllegalArgumentException if acctId is not in the list
     */
    @Override
    public void freezeAccount(String acctId) throws IllegalArgumentException {

    }

    /**
     * Unfreezes account so its methods can be used
     * @param acctId string ID of account that needs to be unfrozen
     * @throws IllegalArgumentException if acctId is in the list
     */
    @Override
    public void unfreezeAcct(String acctId) throws IllegalArgumentException {

    }
}
