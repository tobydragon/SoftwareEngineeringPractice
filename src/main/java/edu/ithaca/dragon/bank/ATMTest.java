package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ATMTest {

    @Test
    void checkBalanceTest(){
        ATM atm = new ATM(); //TODO redo these tests later
        assertEquals(1, atm.checkBalance("a@b.com"));
    }

}
