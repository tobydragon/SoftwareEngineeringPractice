package edu.ithaca.dragon.bank;
import java.io.*;
import java.util.*;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class TellerTest {

    @Test
    void createAccountTest() {
        CentralBank myBank = new CentralBank();
        Collection<User> users = new ArrayList<User>();
        User charles = new User("Charles","123");
        Teller acct= new Teller(myBank);
        acct.createAccount(charles,"CharlesSaves", 100, true);
        assertTrue(myBank.accounts.contains(myBank.getBankAccount("CharlesSaves")));
        acct.createAccount(charles,"CharlesChecking", 100, false);
        assertTrue(myBank.accounts.contains(myBank.getBankAccount("CharlesChecking")));

    }

    @Test
    void CloseAccountTest() {
        CentralBank myBank = new CentralBank();
        User charles = new User("Charles","123");
        Collection<User> users = new ArrayList<User>();
        Teller acct= new Teller(myBank);
        acct.createAccount(charles,"CharlesSaves", 100, true);
        acct.closeAccount(charles,"CharlesSaves");
        assertThrows(IllegalArgumentException.class, ()-> myBank.accounts.contains(myBank.getBankAccount("CharlesSaves")));
        acct.createAccount(charles,"CharlesChecking", 100, false);
        acct.closeAccount(charles,"CharlesChecking");
        assertThrows(IllegalArgumentException.class, ()-> myBank.accounts.contains(myBank.getBankAccount("CharlesChecking")));

    }
}
