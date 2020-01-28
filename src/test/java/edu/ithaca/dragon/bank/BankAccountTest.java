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
        // Border case two periods not consecutive (valid)
        assertTrue(BankAccount.isEmailValid("a.b@c.com"));
        assertTrue(BankAccount.isEmailValid("a_b@c.com"));
        // Border case special character "-" before @ (valid)
        assertTrue(BankAccount.isEmailValid("a@b-c.com"));
        // Border case special character "-" after @ (valid)
        assertTrue(BankAccount.isEmailValid("a-b@c.com"));
        // Border case length of email domain (valid)
        assertTrue(BankAccount.isEmailValid("a@b.cc"));
    }

    @Test
    void withdrawTestUpdated() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertThrows(InsufficientFundsException.class, ()-> bankAccount.withdraw(-100));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.withdraw(300));
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());

        BankAccount bankAccount2 = new BankAccount("a@b.com", 200);
        // Border case (3+ decimal places not allowed)
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.withdraw(199.999));
        // Valid border case (<=2 decimal places allowed)
        bankAccount2.withdraw(199.99);
        assertEquals(1.01, bankAccount2.getBalance());
        // Invalid border case (greater amount with decimals)
        assertThrows(InsufficientFundsException.class, ()-> bankAccount2.withdraw(200.01));
    }

}