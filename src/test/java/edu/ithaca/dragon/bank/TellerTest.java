package edu.ithaca.dragon.bank;
import java.io.*;
import java.util.*;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class TellerTest {

    @Test
    void createAccountTest() {
        Collection<BankAccount> accounts = new ArrayList<BankAccount>();
        CentralBank myBank = new CentralBank();

        Collection<User> users = new ArrayList<User>();
        Teller acct= new Teller(myBank);
        acct.createAccount("Charles", 100, true);
        assertTrue(accounts.contains("Charles"));

    }

    @Test
    void CloseAccountTest() {
        Collection<BankAccount> accounts = new ArrayList<BankAccount>();
        CentralBank myBank = new CentralBank();
        Collection<User> users = new ArrayList<User>();
        Teller acct= new Teller(myBank);
        acct.createAccount("Charles", 100, true);
        acct.closeAccount("Charles");
        assertFalse(accounts.contains("Charles"));

    }
}
