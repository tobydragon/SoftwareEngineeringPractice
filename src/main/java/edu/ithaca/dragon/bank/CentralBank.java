package edu.ithaca.dragon.bank;

import java.util.Collection;

public class CentralBank {

    Collection<BankAccount> accounts;
    Collection<Admin> admins;
    Collection<ATM> atms;
    Collection<User> users;

    public void withdraw(BankAccount account, double amount) {

    }

    public void deposit(BankAccount account, double amount) {

    }

    public void transfer(BankAccount fromAccount, BankAccount toAccount, double amount) {

    }

    public double getBalance(BankAccount account) {
        return account.getBalance();
    }



}