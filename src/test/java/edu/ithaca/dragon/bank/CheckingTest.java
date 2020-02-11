package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckingTest {
    @Test
    void constructorTest() throws IllegalArgumentException{
        //checks constructor is working properly
        Checking checkingAcct = new Checking("1234567890", "Bob Loblaw", "dog123", 100);
        assertEquals("1234567890", checkingAcct.getAcctId());
        assertEquals("Bob Loblaw", checkingAcct.getName());

        //checks that it throws exceptions
        //invalid account id (less than 10)
        assertThrows(IllegalArgumentException.class, () -> new Checking("10", "Bob Loblaw", "dog123", 100));
        //invalid name
        assertThrows(IllegalArgumentException.class, () -> new Checking("1234567890", "123 Joe", "dog123", 100));
        //negative balance
        assertThrows(IllegalArgumentException.class, () -> new Checking("1234567890", "Bob Loblaw", "dog123", -100));
        //balance with more than 2 decimal places
        assertThrows(IllegalArgumentException.class, () -> new Checking("1234567890", "Bob Loblaw", "dog123", 10.342));
    }

    @Test
    void isNameValidTest(){
        assertTrue(Checking.isNameValid("Bob Loblaw"));
        assertTrue(Checking.isNameValid("John M Labler"));
        assertTrue(Checking.isNameValid("Steven M ELridge Smith"));

        assertFalse(Checking.isNameValid("Flor"));
        assertFalse(Checking.isNameValid("Bob John123"));
        assertFalse(Checking.isNameValid("@Jerome Johnson"));
    }

    @Test
    void checkBalanceTest(){
        Checking checkingAcct = new Checking("1234567890", "Bob Lob", "dog123", 100);
        assertEquals(100, checkingAcct.checkBalance("1234567890"));

        checkingAcct = new Checking("1234567890", "Bob Lob", "dog123", 424.35);
        assertEquals(424.35, checkingAcct.checkBalance("1234567890"));

        checkingAcct = new Checking("1234567890", "Bob Lob", "dog123", 0);
        assertEquals(0, checkingAcct.checkBalance("1234567890"));
    }
}
