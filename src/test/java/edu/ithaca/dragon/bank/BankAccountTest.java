package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance());

        bankAccount = new BankAccount("a@b.com", 0);
        assertEquals(0, bankAccount.getBalance());


    }

    @Test
    void withdrawTest() throws IllegalArgumentException, InsufficientFundsException {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        // check zero balance
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());

        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(200));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-20));

        bankAccount.withdraw(100);
        assertEquals(0, bankAccount.getBalance());

        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(1));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-20000));
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
        assertFalse(BankAccount.isEmailValid("a@b#.com"));
        //test length of domain
        assertFalse(BankAccount.isEmailValid("a@b"));

    }

    @Test
    void constructorTest() throws IllegalArgumentException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -100));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 123.345));
    }

    @Test
    void isAmountValidTest() {
        // check negative
        assertFalse(BankAccount.isAmountValid(-23.45));

        // check zero
        assertTrue(BankAccount.isAmountValid(0));

        // check positive
        assertTrue(BankAccount.isAmountValid(1.00));
        assertTrue(BankAccount.isAmountValid(2.0));
        assertTrue(BankAccount.isAmountValid(3.45));
        assertFalse(BankAccount.isAmountValid(4.567));

        // check large positive
        assertTrue(BankAccount.isAmountValid(12345.67));
        assertTrue(BankAccount.isAmountValid(23456.7));
        assertTrue(BankAccount.isAmountValid(34567.89));
        assertFalse(BankAccount.isAmountValid(45678.901));
    }

    @Test
    void depositTest() throws IllegalArgumentException {
        BankAccount bankAccount = new BankAccount("a@b.com", 0);
        //check negative
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-10.0));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-10.123));

        //check zero
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(0));

        // check positive
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(1.0));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(10.0002));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(12345670));
    }

}