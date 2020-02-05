package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CentralBankTest {

    @Test
    void checkBalanceTest(){
        CentralBank testAccount = new CentralBank();
        assertEquals(800, testAccount.checkBalance("1234"));

        CentralBank testAccount2 = new CentralBank();
        assertEquals(50, testAccount2.checkBalance("4321"));
    }

}
