package edu.ithaca.dragon.bank;

import java.util.Collection;


public class User {

    private Collection<BankAccount> AccountList;
    private String username;
    private String password;

    public User(String username, String password){
        this.username=username;
        this.password=password;
    }


}
