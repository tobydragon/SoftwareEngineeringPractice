package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ATMTest {
    @Test
    void checkBalanceTest(){
        ATM testATM = new ATM();
        assertEquals(1.0, testATM.checkBalance("ekuznetsov@ithaca.edu"));
        assertFalse(3.0 == testATM.checkBalance("ekuznetsov@ithaca.edu"));
    }
}
