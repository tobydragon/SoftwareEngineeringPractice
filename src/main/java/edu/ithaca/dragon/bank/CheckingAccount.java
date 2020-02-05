package edu.ithaca.dragon.bank;

import java.util.Collection;

public class CheckingAccount extends Account {
    private double balance;
    private boolean isFrozen;
    private Collection<User> users;

    public CheckingAccount(double balance, Collection<User> users){
        this.balance = balance;
        this.users = users;

    }
}
