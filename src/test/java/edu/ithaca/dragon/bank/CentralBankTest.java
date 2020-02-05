package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CentralBankTest {

    @Test
    void checkBalanceTest() {
        CentralBank newBank = new CentralBank("3428909999", 50.05, "mike");
        assertEquals(10, newBank.checkBalance("3428909999"));

    }

    @Test
    void depositTest(){

    }
}
