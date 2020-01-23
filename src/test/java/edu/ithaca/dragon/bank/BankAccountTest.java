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
        //Email should be true if starts with character or number, includes @ and ends with .'character'
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse( BankAccount.isEmailValid(""));
        assertTrue(BankAccount.isEmailValid("23s@b.com"));
        assertTrue(BankAccount.isEmailValid("a1@b.com"));
        assertFalse(BankAccount.isEmailValid("@n.com"));
        assertFalse(BankAccount.isEmailValid("n.com"));
        assertFalse(BankAccount.isEmailValid("n@Gcom"));
        assertTrue(BankAccount.isEmailValid("n@gmail.com"));
        assertTrue(BankAccount.isEmailValid("n@gyi.org"));
        assertFalse(BankAccount.isEmailValid(".n@Gcom"));
        assertFalse(BankAccount.isEmailValid(".#Gcom"));
        assertFalse(BankAccount.isEmailValid("n$@@G$$com"));
        assertTrue(BankAccount.isEmailValid("n122@gyi.guo"));


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