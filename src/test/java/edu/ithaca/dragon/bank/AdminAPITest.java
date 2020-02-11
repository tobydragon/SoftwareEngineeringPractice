package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdminAPITest {
    @Test

    void calcTotalAssetsTest() throws AccountNotFoundException, AccountAlreadyExistsException{
        CentralBank bank = new CentralBank();
        AdminAPI admin = bank;
        AdvancedAPI teller = bank;

        teller.createAccount("a@b.com", "pass", 100.0);

        assertEquals(100.0, admin.calcTotalAssets());

        teller.closeAccount("a@b.com");

        assertEquals(0.0, admin.calcTotalAssets());

        teller.createAccount("a@b.com", "pass", 100.0);
        teller.createAccount("a@c.com", "pass", 50.0);
        teller.createAccount("a@d.com", "pass", 25.0);
        assertEquals(175.0, admin.calcTotalAssets());




    }
}
