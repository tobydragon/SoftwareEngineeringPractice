package edu.ithaca.dragon.bank;

import java.util.Collection;

public interface AdminAPI {

    /**
     * @returns the sum of all the balances of all the accounts in the bank
     */
    public double calcTotalAssets();  //Not implemented

    /**
     * @returns a collection of accounts deemed to be suspicious(withdrew or transferred out more than half their value at one time)
     */
    public Collection<String> findAcctIdsWithSuspiciousActivity();  //Implemented

    /**
     * @param email is a string describing an email address unique to the account to be frozen
     * @post money cannot be transferred into or out of the account until it is unfrozen
     * @throws AccountNotFoundException if the given email is not associated with an account
     */
    public void freezeAccount(String email) throws AccountNotFoundException;  //Not implemented

    /**
     * @param email is a string describing an email address unique to the account to be unfrozen
     * @post allows money to be transferred into or out of an account again after being frozen
     * @throws AccountNotFoundException if the given email is not associated with an account
     */
    public void unfreezeAcct(String email) throws AccountNotFoundException;  //Not implemented

}
