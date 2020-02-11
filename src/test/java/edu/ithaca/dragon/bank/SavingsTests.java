package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class SavingsTests {

    @Test
    void checkBalanceTests() throws InsufficientFundsException {
        Savings newSavings = new Savings("1234567890", "Mike", "jdsakjh23u329", 100, 3, 50);
        assertEquals(100, newSavings.checkBalance("1234567890"), 0.0001);
        newSavings.withdraw("1234567890", 20);
        assertEquals(80, newSavings.checkBalance("1234567890"),0.0001);
        newSavings.deposit("1234567890", 40.05);
        assertEquals(120.05, newSavings.checkBalance("1234567890"),0.0001);
    }

    @Test
    void withdrawTests(){

    }

    @Test
    void depositTests(){

    }

    @Test
    void transferTests(){

    }

    @Test
    void transactionHistoryTests(){

    }

    @Test
    void compoundInterestTests(){

    }


}
