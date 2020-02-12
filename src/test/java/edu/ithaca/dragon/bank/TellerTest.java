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
        Teller acct= new Teller(myBank);
        acct.createAccount("Charles", 100, true);
        assertTrue(myBank.accounts.contains(myBank.getBankAccount("Charles")));

    }

    @Test
    void CloseAccountTest() {
        CentralBank myBank = new CentralBank();

        Collection<User> users = new ArrayList<User>();
        Teller acct= new Teller(myBank);
         acct.createAccount("Charles", 100, true);
        acct.closeAccount("Charles");
        assertThrows(IllegalArgumentException.class, ()-> myBank.accounts.contains(myBank.getBankAccount("Charles")));

    }
}
