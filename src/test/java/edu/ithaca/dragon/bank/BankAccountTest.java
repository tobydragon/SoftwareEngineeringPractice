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
        //assertTrue(BankAccount.isEmailValid( "a@b.com"));
        //assertFalse( BankAccount.isEmailValid(""));

        //Josue's invalid email prefix tests
        assertFalse((BankAccount.isEmailValid("TomHanksIsCool-@b.com")));
        assertFalse((BankAccount.isEmailValid("Maud..Vile@b.com")));
        assertFalse((BankAccount.isEmailValid("Yung#Muney@b.com")));
        assertFalse((BankAccount.isEmailValid(".TableScraps@b.com")));

        //Josue's invalid email domain tests
        assertFalse((BankAccount.isEmailValid("TomHanksIsCold@b#c.com")));
        assertFalse((BankAccount.isEmailValid("KevinLevin@b")));
        assertFalse((BankAccount.isEmailValid("JetSetRadio@b..com")));
        assertFalse((BankAccount.isEmailValid("Jajaja@b.SpanishLaugh.com")));
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