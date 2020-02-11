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
    void checkBalanceTests() {
        Savings newSavings = new Savings("1234567890", "Mike", "jdsakjh23u329", 100, 3, 50);
        assertEquals(100, newSavings.checkBalance("1234567890"), 0.001);
        newSavings.deposit("1234567890", 20);
        assertEquals(120, newSavings.checkBalance("1234567890"),0.001);
        newSavings.deposit("1234567890", 40.05);
        assertEquals(160.05, newSavings.checkBalance("1234567890"),0.001);
        assertThrows(IllegalArgumentException.class, ()-> newSavings.checkBalance("1234567899"));
    }

    @Test
    void withdrawTests() throws InsufficientFundsException {
        Savings newSavings = new Savings("1234567890", "Mike", "dg38g8qw", 100, 5, 30);
        newSavings.withdraw("1234567890", 20);
        assertEquals(80, newSavings.checkBalance("1234567890"), 0.001);
        newSavings.withdraw("1234567890", 30);
        assertEquals(50, newSavings.checkBalance("1234567890"), 0.001);
        assertThrows(IllegalArgumentException.class, ()-> newSavings.withdraw("1234567890", 30.01));
        assertEquals(50, newSavings.checkBalance("1234567890"), 0.001);
        assertThrows(IllegalArgumentException.class, ()-> newSavings.withdraw("1234567880", 25));
        assertEquals(50, newSavings.checkBalance("1234567890"), 0.001);
        assertThrows(IllegalArgumentException.class, ()-> newSavings.withdraw("1234567890", 0));
        assertEquals(50, newSavings.checkBalance("1234567890"), 0.001);
        assertThrows(IllegalArgumentException.class, ()-> newSavings.withdraw("1234567880", -25));
        assertEquals(50, newSavings.checkBalance("1234567890"), 0.001);
        newSavings.withdraw("1234567890", 30);
        assertEquals(20, newSavings.checkBalance("1234567890"));
        assertThrows(InsufficientFundsException.class, ()-> newSavings.withdraw("1234567890", 20.01));
        assertEquals(20, newSavings.checkBalance("1234567890"), 0.001);
    }

    @Test
    void depositTests(){
        Savings newSavings = new Savings("1234567890", "Mike", "jdsakjh23u329", 100, 3, 50);
        assertEquals(100, newSavings.checkBalance("1234567890"), 0.001);
        newSavings.deposit("1234567890", 20);
        assertEquals(120, newSavings.checkBalance("1234567890"),0.001);
        newSavings.deposit("1234567890", 40.05);
        assertEquals(160.05, newSavings.checkBalance("1234567890"),0.001);
        assertThrows(IllegalArgumentException.class, ()-> newSavings.deposit("1234567899", 10));
        assertThrows(IllegalArgumentException.class, ()-> newSavings.deposit("1234567890", 0));
        assertThrows(IllegalArgumentException.class, ()-> newSavings.deposit("1234567890", -10));
    }


    @Test
    void transactionHistoryTests(){

    }

    @Test
    void compoundInterestTests(){
        Savings newSavings = new Savings("1234567890", "Mike", "fduiewh9uf", 200, 3, 40);
        newSavings.compoundInterest("1234567890");
        assertEquals(206, newSavings.checkBalance("1234567890"), 0.001);
        newSavings.compoundInterest("1234567890");
        assertEquals(212.18, newSavings.checkBalance("1234567890"),0.001);
        newSavings.compoundInterest("1234567890");
        assertEquals(218.54, newSavings.checkBalance("1234567890"),0.001);
        assertThrows(IllegalArgumentException.class, ()-> newSavings.compoundInterest("1234567899"));

    }


}
