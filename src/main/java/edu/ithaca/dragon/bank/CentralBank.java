package edu.ithaca.dragon.bank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CentralBank {

    private Collection<ATM> atms;
    private Map<String, Account> accounts;
    private Collection<Admin> admins;
    private Collection<User> users;

    public CentralBank(){
        atms = new ArrayList<ATM>();
        accounts = new HashMap<String, Account>();
        admins = new ArrayList<Admin>();
        users = new ArrayList<User>();
    }


    public void withdraw(Account account, double amount) {

    }

    public void deposit(Account account, double amount) {

    }

    public void transfer(Account fromAccount, Account toAccount, double amount) {

    }

    public String getCredentials(Account account) {
        return account.getCredentials();
    }

    public double getBalance(Account account) {
        return account.getBalance();
    }

    public String getHistory(Account account) {
        return account.getHistory();
    }

    public void setAccounts(Map<String, Account> newAccounts) {
        accounts = newAccounts;
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

}
