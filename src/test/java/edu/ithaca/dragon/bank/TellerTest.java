package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import java.rmi.NoSuchObjectException;

import static org.junit.jupiter.api.Assertions.*;

public class TellerTest {

    @Test
    void createAccountTest() throws NoSuchObjectException {
        CentralBank centralBank = new CentralBank();
        Teller teller = new Teller(centralBank);
        teller.createAccount("0", 0);
        for (Account account : centralBank.getAccounts().values()) {
            if (account.getID().equals("0")) {
                assertEquals(account, new CheckingAccount(0, "0"));
            }
        }
    }
}
