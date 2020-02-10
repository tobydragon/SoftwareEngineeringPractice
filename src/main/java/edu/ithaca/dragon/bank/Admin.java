package edu.ithaca.dragon.bank;

import java.util.Collection;
import java.util.Iterator;

public class Admin implements AdminAPI {
    CentralBank bank;

    /**
     * creates an Admin account that knows what accounts it controls
     * @param bank the CentralBank that Admin must act on
     */
    public Admin(CentralBank bank){
        this.bank = bank;
    }

    @Override
    public double calcTotalAssets() {
        double total = 0;
        Iterator<Account> itr = this.bank.accounts.iterator();
        while (itr.hasNext()){
            Account current = itr.next();
            total += current.getBalance();
        }
        return total;
    }

    @Override
    public Collection<String> findAcctIdsWithSuspiciousActivity() {
        return null;
    }

    @Override
    public void freezeAccount(String acctId) throws IllegalArgumentException {
        boolean accountFound = false;
        Iterator<Account> itr = this.bank.accounts.iterator();
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
        Iterator<Account> itr = this.bank.accounts.iterator();
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

    public CentralBank getBank(){
        return this.bank;
    }
}
