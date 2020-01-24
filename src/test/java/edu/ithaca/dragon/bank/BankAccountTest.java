package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() throws InsufficientFundsException {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance());
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse( BankAccount.isEmailValid(""));
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

    @Test
    void isEmailValidTestUpdated(){
        assertFalse(BankAccount.isEmailValid(""));
        // Empty String
        assertFalse(BankAccount.isEmailValid("a-@b.com"));
        // Ends in special char
        assertFalse(BankAccount.isEmailValid("a..@b.com"));
        // Ends in two dots
        assertFalse(BankAccount.isEmailValid(".a@b.com"));
        // Starts with dot
        assertFalse(BankAccount.isEmailValid("a#@b.com"));
        // Ends in special char
        assertFalse(BankAccount.isEmailValid("a@b.c"));
        // Too short a domain
        assertFalse(BankAccount.isEmailValid("a@b#c.com"));
        // Special char in domain
        assertFalse(BankAccount.isEmailValid("a@b"));
        // Too short a domain
        assertFalse(BankAccount.isEmailValid("a@b..com"));
        // Two dots in domain
        assertTrue(BankAccount.isEmailValid("a@b.com"));
        assertTrue(BankAccount.isEmailValid("a-b@c.com"));
        assertTrue(BankAccount.isEmailValid("a.b@c.com"));
        assertTrue(BankAccount.isEmailValid("a_b@c.com"));
        assertTrue(BankAccount.isEmailValid("a@b.cc"));
        assertTrue(BankAccount.isEmailValid("a@b-c.com"));
    }

    @Test
    void withdrawTestUpdated() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.withdraw(-100));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.withdraw(300));
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());
    }

}