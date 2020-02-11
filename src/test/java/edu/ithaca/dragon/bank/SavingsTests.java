package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class SavingsTests {

    @Test
    void constructorTests(){
        assertThrows(IllegalArgumentException.class, ()-> new Savings("123456780", "Mike", "hjdsj328", 500, 4, 125));
        assertThrows(IllegalArgumentException.class, ()-> new Savings("1234567890", "Mike", "hjdsj328", -500, 4, 125));
        assertThrows(IllegalArgumentException.class, ()-> new Savings("1234567890", "Mike", "hjdsj328", 500, -4, 125));
        assertThrows(IllegalArgumentException.class, ()-> new Savings("1234567890", "Mike", "hjdsj328", 500, 4, -125));
        Savings newSavings = new Savings("1234567890", "Mike", "dheg", 100, 2, 80);
        assertEquals("1234567890", newSavings.getAcctId());
        assertEquals("Mike", newSavings.getName());
    }

    @Test
    void checkBalanceTests() throws InsufficientFundsException {


    }

    @Test
    void withdrawTests(){

    }

    @Test
    void depositTests(){
        Savings newSavings = new Savings("1234567890", "Mike", "jdsakjh23u329", 100, 3, 50);
        assertEquals(100, newSavings.checkBalance("1234567890"), 0.0001);
        newSavings.deposit("1234567890", 20);
        assertEquals(120, newSavings.checkBalance("1234567890"),0.0001);
        newSavings.deposit("1234567890", 40.05);
        assertEquals(160.05, newSavings.checkBalance("1234567890"),0.0001);

    }


    @Test
    void transactionHistoryTests(){

    }

    @Test
    void compoundInterestTests(){

    }


}
