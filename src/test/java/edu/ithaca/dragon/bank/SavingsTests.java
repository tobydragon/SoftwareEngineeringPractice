package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class SavingsTests {

    @Test
    void constructorTests(){
        assertThrows(IllegalArgumentException.class, ()-> new Savings("123456780", "Mike", "hjdsj328", 500, 4, 125));
        assertThrows(IllegalArgumentException.class, ()-> new Savings("1234567890", "Mike", "hjdsj328", -500, 4, 125));
        assertThrows(IllegalArgumentException.class, ()-> new Savings("1234567890", "Mike", "hjdsj328", 500, -4, 125));
        assertThrows(IllegalArgumentException.class, ()-> new Savings("1234567890", "Mike", "hjdsj328", 500, 0, 125));
        assertThrows(IllegalArgumentException.class, ()-> new Savings("1234567890", "Mike", "hjdsj328", 500, 4, -125));
        assertThrows(IllegalArgumentException.class, ()-> new Savings("1234567890", "Mike", "hjdsj328", 500, 4, 0));
        Savings newSavings = new Savings("1234567890", "Mike", "dheg", 100, 2, 80);
        assertEquals("1234567890", newSavings.getAcctId());
        assertEquals("Mike", newSavings.getName());
    }

    @Test
    void checkBalanceTests() throws AcctFrozenException {
        Savings newSavings = new Savings("1234567890", "Mike", "jdsakjh23u329", 100, 3, 50);
        assertEquals(100, newSavings.checkBalance("1234567890"), 0.009);
        newSavings.deposit("1234567890", 20);
        assertEquals(120, newSavings.checkBalance("1234567890"),0.009);
        newSavings.deposit("1234567890", 40.05);
        assertEquals(160.05, newSavings.checkBalance("1234567890"),0.009);
        assertThrows(IllegalArgumentException.class, ()-> newSavings.checkBalance("1234567899"));
        newSavings.setFrozen(true);
        assertThrows(AcctFrozenException.class, ()-> newSavings.checkBalance("1234567890"));
    }

    @Test
    void withdrawTests() throws InsufficientFundsException, AcctFrozenException {
        Savings newSavings = new Savings("1234567890", "Mike", "dg38g8qw", 100, 5, 30);
        newSavings.withdraw("1234567890", 20);
        assertEquals(80, newSavings.checkBalance("1234567890"), 0.009);
        newSavings.withdraw("1234567890", 30);
        assertEquals(50, newSavings.checkBalance("1234567890"), 0.009);
        assertThrows(IllegalArgumentException.class, ()-> newSavings.withdraw("1234567890", 30.01));
        assertEquals(50, newSavings.checkBalance("1234567890"), 0.009);
        assertThrows(IllegalArgumentException.class, ()-> newSavings.withdraw("1234567880", 25));
        assertEquals(50, newSavings.checkBalance("1234567890"), 0.009);
        assertThrows(IllegalArgumentException.class, ()-> newSavings.withdraw("1234567890", 0));
        assertEquals(50, newSavings.checkBalance("1234567890"), 0.009);
        assertThrows(IllegalArgumentException.class, ()-> newSavings.withdraw("1234567880", -25));
        assertEquals(50, newSavings.checkBalance("1234567890"), 0.009);
        newSavings.withdraw("1234567890", 30);
        assertEquals(20, newSavings.checkBalance("1234567890"));
        assertThrows(InsufficientFundsException.class, ()-> newSavings.withdraw("1234567890", 20.01));
        assertEquals(20, newSavings.checkBalance("1234567890"), 0.009);
        newSavings.setFrozen(true);
        assertThrows(AcctFrozenException.class, ()-> newSavings.withdraw("1234567890", 5));
    }

    @Test
    void depositTests() throws AcctFrozenException {
        Savings newSavings = new Savings("1234567890", "Mike", "jdsakjh23u329", 100, 3, 50);
        assertEquals(100, newSavings.checkBalance("1234567890"), 0.009);
        newSavings.deposit("1234567890", 20);
        assertEquals(120, newSavings.checkBalance("1234567890"),0.009);
        newSavings.deposit("1234567890", 40.05);
        assertEquals(160.05, newSavings.checkBalance("1234567890"),0.009);
        assertThrows(IllegalArgumentException.class, ()-> newSavings.deposit("1234567899", 10));
        assertThrows(IllegalArgumentException.class, ()-> newSavings.deposit("1234567890", 0));
        assertThrows(IllegalArgumentException.class, ()-> newSavings.deposit("1234567890", -10));
        newSavings.setFrozen(true);
        assertThrows(AcctFrozenException.class, ()-> newSavings.deposit("1234567890", 40.05));
    }


    @Test
    void transactionHistoryTests() throws InsufficientFundsException, AcctFrozenException {
        //this also acts as an integration test
        Savings newSavings = new Savings("1234567890", "Mike", "bfuid3b", 300, 2, 120);
        newSavings.deposit("1234567890", 20);
        newSavings.deposit("1234567890", 40);
        newSavings.withdraw("1234567890", 90);
        assertThrows(IllegalArgumentException.class, ()-> newSavings.transactionHistory("1238456789"));
        assertEquals("deposit of 20.0; deposit of 40.0; withdrawal of 90.0", newSavings.transactionHistory("1234567890"));
        newSavings.deposit("1234567890", 80);
        assertEquals("deposit of 20.0; deposit of 40.0; withdrawal of 90.0; deposit of 80.0", newSavings.transactionHistory("1234567890"));
        newSavings.setFrozen(true);
        assertThrows(AcctFrozenException.class, ()-> newSavings.transactionHistory("1234567890"));
    }

    @Test
    void compoundInterestTests() throws AcctFrozenException {
        Savings newSavings = new Savings("1234567890", "Mike", "fduiewh9uf", 200, 3, 40);
        newSavings.compoundInterest("1234567890");
        assertEquals(206, newSavings.checkBalance("1234567890"), 0.009);
        newSavings.compoundInterest("1234567890");
        assertEquals(212.18, newSavings.checkBalance("1234567890"),0.009);
        newSavings.compoundInterest("1234567890");
        assertEquals(218.54, newSavings.checkBalance("1234567890"),0.009);
        assertThrows(IllegalArgumentException.class, ()-> newSavings.compoundInterest("1234567899"));
        newSavings.setFrozen(true);
        assertThrows(AcctFrozenException.class, ()-> newSavings.compoundInterest("1234567890"));
    }

    @Test
    void setFrozenTests(){
        Savings newSavings = new Savings("1234567890", "Mike", "fduiewh9uf", 200, 3, 40);
        newSavings.setFrozen(true);
        assertEquals(true, newSavings.getFrozenStatus());
        newSavings.setFrozen(false);
        assertEquals(false, newSavings.getFrozenStatus());
        newSavings.setFrozen(true);
        newSavings.setFrozen(false);
        newSavings.setFrozen(false);
        assertEquals(false, newSavings.getFrozenStatus());
    }



}
