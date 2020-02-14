package edu.ithaca.dragon.bank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class User {

    Collection<BankAccount> accounts;
    private String username;
    private String password;

    public User(String username, String password){
        this.username=username;
        this.password=password;
        Collection<BankAccount> accounts = new ArrayList<BankAccount>();
        this.accounts=accounts;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
