package edu.ithaca.dragon.bank;
import java.io.*;
x
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class TellerTest {

    @Test
    void createAccountTest() {
        Collection<BankAccount> accounts = new ArrayList<BankAccount>();

        Collection<User> users = new ArrayList<User>();
        Teller acct= new Teller(accounts, users);
        acct.createAccount("Charles", 100, true);
        if( accounts.size()>0) {
            assertEquals(accounts.
        }




    }
}
