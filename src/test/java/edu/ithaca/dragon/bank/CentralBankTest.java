package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CentralBankTest {

    @Test
    void checkBalanceTest() {
        CentralBank bank = new CentralBank();
        assertEquals(10, bank.checkBalance("1234567890"));
    }

    @Test
    void depositTest(){

    }
}
