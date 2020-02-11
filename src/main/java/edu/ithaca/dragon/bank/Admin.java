package edu.ithaca.dragon.bank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
public class Admin implements AdminAPI {
    CentralBank bank;

    public Admin(CentralBank bank){
        this.bank=bank;
    }

    public double calcTotalAssets() {
        double totalAssets = 0;
        Iterator<BankAccount> itr = this.bank.accounts.iterator();
        while(itr.hasNext()) {
            BankAccount curr = itr.next();
            totalAssets += curr.getBalance();
        }
        return totalAssets;
    }

    public Collection<String> findAcctIdsWithSuspiciousActivity() {
        Collection<String> ids = new ArrayList<String>();
        double depositThreshold = 2;
        double withdrawThreshold = .5;

        Iterator<BankAccount> itr = this.bank.accounts.iterator();
        while(itr.hasNext()) {
            BankAccount curr = itr.next();
            Iterator<Transaction> itr2 = curr.transactionHistory.iterator();
            while(itr2.hasNext()) {
                Transaction curr2 = itr2.next();
                if(curr2.getFlagSuspicious()) {
                    ids.add(curr.getAcctId());
                    break;
                }
            }
        }
        return ids;
    }

    //freeze accounts
    public void freezeAccount(String acctId) {
        boolean accountpresent = false;
        Iterator<BankAccount> itr = this.bank.accounts.iterator();
        while (itr.hasNext()){
            BankAccount current = itr.next();
            if (current.getAcctId()== acctId){
                accountpresent = true;
                current.setFrozen(true);
            }
        }
        if (accountpresent ==false){
            throw new IllegalArgumentException("invalid account id");
        }


    }

    public void unfreezeAcct(String acctId) {
        boolean accountpresent = false;
        Iterator<BankAccount> itr = this.bank.accounts.iterator();
        while (itr.hasNext()){
            BankAccount current = itr.next();
            if (current.getAcctId()== acctId){
                accountpresent = true;
                current.setFrozen(false);
            }
        }
        if (accountpresent == false){
            throw new IllegalArgumentException("invalid account id");
        }

    }
}
