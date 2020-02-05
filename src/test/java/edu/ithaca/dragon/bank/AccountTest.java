package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private static final double THRESHOLD = 0.001;

    @Test
    void depositTest() {
        Account account = new CheckingAccount(0);

        //non-negative amount with more than two decimal places
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0.001));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0.9999));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(1010.101));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(Double.MIN_VALUE));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(Double.MAX_VALUE));

        //negative amount with two decimal places or less
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-0.01));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-0.99));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-8008.2));

        //negative amount with more than two decimal places
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-0.001));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-0.9999));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-5000.125));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-Double.MIN_VALUE));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-Double.MAX_VALUE));

        //non-negative amount with two decimal places or less
        account.deposit(0);
        assertEquals(0, account.getBalance(), THRESHOLD);
        account.deposit(10);
        assertEquals(10, account.getBalance(), THRESHOLD);
        account.deposit(0.01);
        assertEquals(10.01, account.getBalance(), THRESHOLD);
        account.deposit(0.99);
        assertEquals(11, account.getBalance(), THRESHOLD);
        account.deposit(419.5);
        assertEquals(430.5, account.getBalance(), THRESHOLD);
    }

    @Test
    void isAmountValidTest() {
        //non-negative amount with two decimal points or less
        assertTrue(BankAccount.isAmountValid(0));
        assertTrue(BankAccount.isAmountValid(0.01));
        assertTrue(BankAccount.isAmountValid(0.99));
        assertTrue(BankAccount.isAmountValid(250));
        assertTrue(BankAccount.isAmountValid(1234.50));

        //non-negative amount with more than two decimal points
        assertFalse(BankAccount.isAmountValid(0.001));
        assertFalse(BankAccount.isAmountValid(0.9999));
        assertFalse(BankAccount.isAmountValid(536.125));
        assertFalse(BankAccount.isAmountValid(Double.MIN_VALUE));
        assertFalse(BankAccount.isAmountValid(Double.MAX_VALUE));

        //negative amount with two decimal points or less
        assertFalse(BankAccount.isAmountValid(-0.01));
        assertFalse(BankAccount.isAmountValid(-0.99));
        assertFalse(BankAccount.isAmountValid(-120));
        assertFalse(BankAccount.isAmountValid(-946.5));

        //negative amount with more than two decimal points
        assertFalse(BankAccount.isAmountValid(-0.001));
        assertFalse(BankAccount.isAmountValid(-0.9999));
        assertFalse(BankAccount.isAmountValid(-Double.MIN_VALUE));
        assertFalse(BankAccount.isAmountValid(-Double.MAX_VALUE));
    }
}