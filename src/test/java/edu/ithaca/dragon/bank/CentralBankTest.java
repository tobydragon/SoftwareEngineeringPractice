package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CentralBankTest {

    @Test
    void checkBalanceTest() {
        CentralBank centralBank  = new CentralBank("1", 200.0);
        assertEquals(200.0, centralBank.checkBalance("1"));

        CentralBank centralBank1 = new CentralBank("2", 500.0);
        assertEquals(500.0, centralBank1.checkBalance("2"));

    }


}
