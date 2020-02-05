package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CentralBankTest {

    @Test
    void withdrawTest() {

    }

    @Test
    void depositTest(){
        CentralBank newBank = new CentralBank();
        newBank.deposit("8495739673", 50);
        assertEquals(newBank.checkBalance("8495739673"), 50, 0.0001);
        assertThrows(IllegalArgumentException.class, ()-> newBank.deposit("8495739673", 0));
        assertThrows(IllegalArgumentException.class, ()-> newBank.deposit("8495739673", -5));
        assertThrows(IllegalArgumentException.class, ()-> newBank.deposit("849573967e", 80));
        newBank.deposit("8495739673", 80.5);
        assertEquals(newBank.checkBalance("8495739673"), 130.50, 0.0001);

    }
}
