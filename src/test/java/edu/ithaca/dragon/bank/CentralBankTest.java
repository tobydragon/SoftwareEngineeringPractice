package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CentralBankTest {

    @Test
    void checkBalanceTest(){
        CentralBank testAccount = new CentralBank("1234", 500);
        assertEquals(500, testAccount.checkBalance("1234"));

        CentralBank testAccount2 = new CentralBank("4321", 50);
        assertEquals(50, testAccount2.checkBalance("4321"));
    }

}
