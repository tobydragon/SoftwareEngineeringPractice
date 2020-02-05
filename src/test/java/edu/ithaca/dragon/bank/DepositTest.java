package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DepositTest {

    @Test
    void depositTest() {
        CentralBank account = new CentralBank();

        // Positive, One to Two Decimals
        account.deposit("a@a.com", 0);
        assertEquals(200, account.checkBalance("a@a.com")); //border case
        account.deposit("a@a.com", 100);
        assertEquals(300, account.checkBalance("a@a.com"));
        account.deposit("a@a.com", 999);
        assertEquals(1299, account.checkBalance("a@a.com")); //border case

    }

}
