package edu.ithaca.dragon.bank;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class User {
    private Map<String, Account> accounts;

    public User() {
        accounts = new HashMap<String, Account>();
    }

    public void addAccount(Account accountToAdd) {
        accounts.put(accountToAdd.getID(), accountToAdd);
    }

    public void removeAccount(String id) {
        if(accounts.containsKey(id)) {
            accounts.remove(id);
        } else {
            throw new IllegalArgumentException("User does not contain account with id: " + id);
        }
    }

    public void withdraw(Account account, double amount) {

    }

    public void deposit(Account account, double amount) {

    }

    public void transfer(Account accountTo, Account accountFrom, double amount) {

    }

    public Account getAccount(String id) {
        if(accounts.containsKey(id)) {
            return accounts.get(id);
        } else {
            throw new IllegalArgumentException("User does not contain account with id: " + id);
        }
    }
}
