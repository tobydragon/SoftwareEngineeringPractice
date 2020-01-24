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
    void withdrawTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance());
    }

    @Test
    void isEmailValidTest(){
        //valid equivalence class
        //border case
        assertTrue(BankAccount.isEmailValid( "a@b.com"));

        //invalid equivalence class email length
        //border case-less
        assertFalse( BankAccount.isEmailValid(""));

        //invalid equivalence class special characters
        //not border case
        // prefix tests
        assertFalse(BankAccount.isEmailValid("a-@b.com"));
        //invalid equivalence class email with dots
        //not border case
        assertFalse(BankAccount.isEmailValid("a..@b.com"));
        //invalid equivalence class email with dots
        //border case
        assertFalse(BankAccount.isEmailValid(".a@b.com"));
        //invalid equivalence class special characters
        //not border case
        assertFalse(BankAccount.isEmailValid("a#b@b.com"));

        // domain tests
        //invalid equivalence class email domain
        //border case
        assertFalse(BankAccount.isEmailValid("a@b."));
        //invalid equivalence class email domain
        //not a border case
        assertFalse(BankAccount.isEmailValid("a@b.."));

    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}