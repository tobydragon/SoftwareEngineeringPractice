package edu.ithaca.dragon.bank;

import java.util.Collection;
import java.util.Iterator;

public class Admin implements AdminAPI {
    Collection<Account> accounts;

    /**
     * creates an Admin account that knows what accounts it controls
     * @param accounts the accounts that Admin must act on
     */
    public Admin(Collection<Account> accounts){
        this.accounts = accounts;
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
    public void freezeAccount(String acctId) throws IllegalArgumentException {
        boolean accountFound = false;
        Iterator<Account> itr = this.accounts.iterator();
        while (itr.hasNext()){
            Account current = itr.next();
            if (current.getID()== acctId){
                accountFound = true;
                current.setFrozen(true);
            }
        }
        if (accountFound == false){
            throw new IllegalArgumentException("String not in collection");
        }

    }

    @Override
    public void unfreezeAcct(String acctId) throws IllegalArgumentException {
        boolean accountFound = false;
        Iterator<Account> itr = this.accounts.iterator();
        while (itr.hasNext()){
            Account current = itr.next();
            if (current.getID()== acctId){
                accountFound = true;
                current.setFrozen(false);
            }
        }
        if (accountFound == false){
            throw new IllegalArgumentException("String not in collection");
        }

    }

    public Collection<Account> getAccounts(){
        return this.accounts;
    }
}
