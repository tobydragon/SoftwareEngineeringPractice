package edu.ithaca.dragon.bank;

import java.util.Collection;

public class Teller extends ATM implements AdvancedAPI {
    Collection<BankAccount> accounts;

    public Teller(Collection<User>users,Collection<BankAccount> accounts){
        super(users);
        this.accounts=accounts;

    }


    public void createAccount(String acctId, double startingBalance) {

    }


    public void closeAccount(String acctId) {

    }
}
