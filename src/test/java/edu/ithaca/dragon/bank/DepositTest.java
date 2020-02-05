package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DepositTest {

    @Test
    void depositTest(){
        CentralBank bank = new CentralBank();

        bank.deposit("test@email.com",100);
        assertEquals(200, bank.checkBalance("test@email.com"));

        bank.deposit("test2@email.com",100);
        assertEquals(0, bank.checkBalance("test2@email.com"));
        assertEquals(100, bank.checkBalance("test@email.com"));

    }

}
