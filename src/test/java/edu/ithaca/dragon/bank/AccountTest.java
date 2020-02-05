package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    private static final double THRESHOLD = 0.001;

    @Test
    void depositTest() {
        Account bankAccount = new CheckingAccount( 0);

        //non-negative amount with more than two decimal places
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(0.001));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(0.9999));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(1010.101));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(Double.MIN_VALUE));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(Double.MAX_VALUE));

        //negative amount with two decimal places or less
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-0.01));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-0.99));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-8008.2));

        //negative amount with more than two decimal places
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-0.001));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-0.9999));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-5000.125));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-Double.MIN_VALUE));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-Double.MAX_VALUE));

        //non-negative amount with two decimal places or less
        bankAccount.deposit(0);
        assertEquals(0, bankAccount.getBalance(), THRESHOLD);
        bankAccount.deposit(10);
        assertEquals(10, bankAccount.getBalance(), THRESHOLD);
        bankAccount.deposit(0.01);
        assertEquals(10.01, bankAccount.getBalance(), THRESHOLD);
        bankAccount.deposit(0.99);
        assertEquals(11, bankAccount.getBalance(), THRESHOLD);
        bankAccount.deposit(419.5);
        assertEquals(430.5, bankAccount.getBalance(), THRESHOLD);

    }

    @Test
    void constructorTest() {
        Account bankAccount = new CheckingAccount( 200);

        assertEquals(200, bankAccount.getBalance());

        //non-negative balance with two decimal places or less
        bankAccount = new CheckingAccount( 0);
        assertEquals(0, bankAccount.getBalance(), THRESHOLD);
        bankAccount = new CheckingAccount( 0.01);
        assertEquals(0.01, bankAccount.getBalance(), THRESHOLD);
        bankAccount = new CheckingAccount( 0.99);
        assertEquals(0.99, bankAccount.getBalance(), THRESHOLD);
        bankAccount = new CheckingAccount( 9876.5);
        assertEquals(9876.5, bankAccount.getBalance(), THRESHOLD);
        bankAccount = new CheckingAccount(248);
        assertEquals(248, bankAccount.getBalance(), THRESHOLD);

        //non-negative balance with more than two decimal places
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount(0.001));
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount(0.9999));
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( 369.333));
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( Double.MAX_VALUE));
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( Double.MIN_VALUE));

        //negative balance with two decimal places or less
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( -0.01));
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( -0.99));
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( -10.2));
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( -125));

        //negative balance with more than two decimal places
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( -0.001));
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( -0.9999));
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( -369.333));
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( -Double.MAX_VALUE));
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( -Double.MIN_VALUE));
    }

    @Test
    void isAmountValidTest() {
        //non-negative amount with two decimal points or less
        assertTrue(Account.isAmountValid(0));
        assertTrue(Account.isAmountValid(0.01));
        assertTrue(Account.isAmountValid(0.99));
        assertTrue(Account.isAmountValid(250));
        assertTrue(Account.isAmountValid(1234.50));

        //non-negative amount with more than two decimal points
        assertFalse(Account.isAmountValid(0.001));
        assertFalse(Account.isAmountValid(0.9999));
        assertFalse(Account.isAmountValid(536.125));
        assertFalse(Account.isAmountValid(Double.MIN_VALUE));
        assertFalse(Account.isAmountValid(Double.MAX_VALUE));

        //negative amount with two decimal points or less
        assertFalse(Account.isAmountValid(-0.01));
        assertFalse(Account.isAmountValid(-0.99));
        assertFalse(Account.isAmountValid(-120));
        assertFalse(Account.isAmountValid(-946.5));

        //negative amount with more than two decimal points
        assertFalse(Account.isAmountValid(-0.001));
        assertFalse(Account.isAmountValid(-0.9999));
        assertFalse(Account.isAmountValid(-Double.MIN_VALUE));
        assertFalse(Account.isAmountValid(-Double.MAX_VALUE));
    }
}
