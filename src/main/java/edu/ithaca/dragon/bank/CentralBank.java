package edu.ithaca.dragon.bank;

import javax.swing.plaf.basic.BasicLookAndFeel;
import java.util.ArrayList;
import java.util.Collection;

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
}