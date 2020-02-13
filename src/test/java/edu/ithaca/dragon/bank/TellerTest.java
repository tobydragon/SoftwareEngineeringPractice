package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class TellerTest {

    @Test
    void createAccountTest() {
        CentralBank centralBank = new CentralBank();
        Teller teller = new Teller(centralBank);

        teller.createAccount("0", 0);

        assertNotNull(teller.centralBank.getAccounts().get("0"));

        teller.createAccount("1", 10);
        teller.createAccount("2", 20);
        teller.createAccount("3", 150);

        assertNotNull(teller.centralBank.getAccounts().get("2"));
    }

    @Test
    void closeAccountTest() {
        CentralBank centralBank = new CentralBank();
        Teller teller = new Teller(centralBank);

        assertThrows(NoSuchElementException.class, () -> teller.closeAccount("0"));

        teller.createAccount("0", 0);

        assertThrows(NoSuchElementException.class, () -> teller.closeAccount("1"));

        teller.createAccount("1", 123);
        teller.createAccount("2", 967);

        teller.closeAccount("0");
        assertNull(teller.centralBank.getAccounts().get("0"));

        teller.closeAccount("1");
        assertNull(teller.centralBank.getAccounts().get("1"));

        teller.closeAccount("2");
        assertNull(teller.centralBank.getAccounts().get("2"));
    }
}
