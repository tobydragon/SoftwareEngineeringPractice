package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CentralBankTest {

    @Test
    void checkBalanceTest(){
        CentralBank bank = new CentralBank();
        assertEquals(10, bank.checkBalance("11111111"));
    }

    @Test
    void constructorTest(){

    }

    @Test
    void withdrawTest() {
    }

    @Test
    void depositTest() throws IllegalArgumentException{
        //negative value
        assertThrows(IllegalArgumentException.class, () -> new CentralBank().deposit("1111111111", -100));

        //zero value
        assertThrows(IllegalArgumentException.class, () -> new CentralBank().deposit("1111111111", 0));

        //positive value
        CentralBank centralBank = new CentralBank();
        centralBank.deposit("1111111111", 50);
        assertEquals(50, centralBank.checkBalance("1111111111"), 0.00001);

        //two positives in a row
    }
}
