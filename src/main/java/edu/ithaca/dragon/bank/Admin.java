package edu.ithaca.dragon.bank;

import java.util.Collection;
import java.util.Iterator;
import java.math.RoundingMode;
import java.text.DecimalFormat;



public class Admin implements AdminAPI {
    private static DecimalFormat twoDecimals = new DecimalFormat("0.00");
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
        Iterator<Account> itr = this.bank.getAccounts().values().iterator();
        while (itr.hasNext()){
            Account current = itr.next();
            total += current.getBalance();
        }
        total = Double.valueOf(twoDecimals.format(total));

        return total;
    }

    @Override
    public Collection<String> findAcctIdsWithSuspiciousActivity() {
        return null;
    }

    @Override
    public void freezeAccount(String acctId) throws IllegalArgumentException {
        setAcctFreezeStatus(acctId,true);
    }

    @Override
    public void unfreezeAcct(String acctId) throws IllegalArgumentException {
        setAcctFreezeStatus(acctId,false);
    }

    public void setAcctFreezeStatus(String acctId, boolean isFrozen) {
        boolean accountFound = (bank.getAccounts().containsKey(acctId));
        if(accountFound) {
            bank.getAccounts().get(acctId).setFrozen(isFrozen);
        } else {
            throw new IllegalArgumentException("String not in collection");
        }
    }

    public CentralBank getBank(){
        return this.bank;
    }
}
