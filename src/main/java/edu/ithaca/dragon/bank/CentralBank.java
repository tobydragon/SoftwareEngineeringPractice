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


    public void withdraw(String account, double amount) throws InsufficientFundsException, AccountFrozenException{
        if(!accounts.containsKey(account)) throw new IllegalArgumentException("No Account with ID: " + account);
        accounts.get(account).withdraw(amount);
    }

    public void deposit(String account, double amount) throws AccountFrozenException{
        if(!accounts.containsKey(account)) throw new IllegalArgumentException("No Account with ID: " + account);
        accounts.get(account).deposit(amount);
    }

    public void transfer(String fromAccount, String toAccount, double amount) throws InsufficientFundsException, AccountFrozenException{
        if(!accounts.containsKey(fromAccount)) throw new IllegalArgumentException("No Account with ID: " + fromAccount);
        if(!accounts.containsKey(toAccount)) throw new IllegalArgumentException("No Account with ID: " + toAccount);

        accounts.get(fromAccount).transfer(accounts.get(toAccount), amount);
    }

    public String getCredentials(String account) {
        return accounts.get(account).getCredentials();
    }

    public double checkBalance(String account) {
        return accounts.get(account).getBalance();
    }

    public String getHistory(String account) {
        return accounts.get(account).getHistory();
    }

    public void setAccounts(Map<String, Account> newAccounts) {
        accounts = newAccounts;
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

}
