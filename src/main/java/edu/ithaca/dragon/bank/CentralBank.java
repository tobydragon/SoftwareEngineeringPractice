package edu.ithaca.dragon.bank;

import java.util.Collection;

public class CentralBank {

    Collection<ATM> atms;
    Collection<Account> accounts;
    Collection<Admin> admins;
    Collection<User> users;

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

}
