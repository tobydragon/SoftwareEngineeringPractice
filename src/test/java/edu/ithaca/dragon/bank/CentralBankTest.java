package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CentralBankTest {

    @Test
    void checkBalanceTest() {
        CentralBank centralBank  = new CentralBank();
        assertEquals(200.0, centralBank.checkBalance("1"));

        CentralBank centralBank1 = new CentralBank();
        assertEquals(500.0, centralBank.checkBalance("1"));

    }


}
