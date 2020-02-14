package edu.ithaca.dragon.bank;

import javax.swing.plaf.basic.BasicLookAndFeel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CentralBank {

    public Collection<BankAccount> accounts;
    public Collection<User> users;

    public CentralBank(){
        Collection<BankAccount> accounts = new ArrayList<BankAccount>();
        Collection<User> users = new ArrayList<User>();
        this.accounts=accounts;
        this.users=users;
    }

    public Collection<BankAccount> getAccounts() {
        return accounts;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public BankAccount getBankAccount(String acctID){
        boolean accountpresent = false;
        Iterator<BankAccount> itr = this.accounts.iterator();
        while (itr.hasNext()){
            BankAccount current = itr.next();
            if (current.getAcctId()== acctID){
                return current;
            }
        }
        if (accountpresent == false){
            throw new IllegalArgumentException("invalid account id");
        }

        throw new IllegalArgumentException("invalid account id");

    }
}