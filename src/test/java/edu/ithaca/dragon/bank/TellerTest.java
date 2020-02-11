package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TellerTest {

    @Test
    void createAccountTest()  {
        Teller teller = new Teller();
        teller.createAccount("0", 0);

        assertNotNull(teller.centralBank.getAccounts().get("0"));

        teller.createAccount("1", 10);
        teller.createAccount("2", 20);
        teller.createAccount("3", 150);

        assertNotNull(teller.centralBank.getAccounts().get("2"));

    }
}
