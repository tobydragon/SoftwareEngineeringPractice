package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    private static final double THRESHOLD = 0.001;

    @Test
    void withdrawTest() throws InsufficientFundsException{
        Account bankAccount = new CheckingAccount(1000, "a@b.com");

        //non-negative amount less than or equal to balance with more than two decimal places
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(0.001));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(0.9999));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(3141.592));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(Double.MIN_VALUE));

        //non-negative amount greater than balance with two decimal places or less
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(1000.01));
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(5432));
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(3216.8));

        //non-negative amount greater than balance with more than two decimal places
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(1000.001));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(9876.543));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(Double.MAX_VALUE));

        //negative amount with two decimal places or less
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-0.01));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-0.99));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-2718.2));

        //negative amount with more than two decimal places
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-0.001));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-0.9999));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-14850.607));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-Double.MIN_VALUE));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-Double.MAX_VALUE));

        //non-negative amount less than or equal to balance with two decimal places or less
        bankAccount.withdraw(0);
        assertEquals(1000, bankAccount.getBalance(), THRESHOLD);
        bankAccount.withdraw(100);
        assertEquals(900, bankAccount.getBalance(), THRESHOLD);
        bankAccount.withdraw(0.01);
        assertEquals(899.99, bankAccount.getBalance(), THRESHOLD);
        bankAccount.withdraw(898.99);
        assertEquals(1, bankAccount.getBalance(), THRESHOLD);
        bankAccount.withdraw(1);
        assertEquals(0, bankAccount.getBalance(), THRESHOLD);
    }

    @Test
    void depositTest() {

        Account bankAccount = new CheckingAccount( 0, "1");

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
    void transferTest() throws InsufficientFundsException {
        Account a = new CheckingAccount(1000, "a@b.com");
        Account b = new CheckingAccount(0, "a@b.com");

        //non-negative amount less than or equal to balance with more than two decimal places
        assertThrows(IllegalArgumentException.class, () -> a.transfer(b, 0.001));
        assertThrows(IllegalArgumentException.class, () -> a.transfer(b, 0.9999));
        assertThrows(IllegalArgumentException.class, () -> a.transfer(b, 999.999));

        //non-negative amount greater than balance with two decimal places or less
        assertThrows(InsufficientFundsException.class, () -> a.transfer(b, 1000.01));
        assertThrows(InsufficientFundsException.class, () -> a.transfer(b, 2500.5));
        assertThrows(InsufficientFundsException.class, () -> a.transfer(b, 1357));

        //non-negative amount greater than balance with more than two decimal places
        assertThrows(IllegalArgumentException.class, () -> a.transfer(b, 1000.001));
        assertThrows(IllegalArgumentException.class, () -> a.transfer(b, 6666.6666));
        assertThrows(IllegalArgumentException.class, () -> a.transfer(b, Double.MAX_VALUE));

        //negative amount with two decimal places or less
        assertThrows(IllegalArgumentException.class, () -> a.transfer(b, -0.01));
        assertThrows(IllegalArgumentException.class, () -> a.transfer(b, -0.99));
        assertThrows(IllegalArgumentException.class, () -> a.transfer(b, -607.2));

        //negative amount with more than two decimal places
        assertThrows(IllegalArgumentException.class, () -> a.transfer(b, -0.001));
        assertThrows(IllegalArgumentException.class, () -> a.transfer(b, -0.9999));
        assertThrows(IllegalArgumentException.class, () -> a.transfer(b, -9876.54321));
        assertThrows(IllegalArgumentException.class, () -> a.transfer(b, -Double.MIN_VALUE));
        assertThrows(IllegalArgumentException.class, () -> a.transfer(b, -Double.MAX_VALUE));

        //non-negative amount less than or equal to balance with two decimal places or less
        a.transfer(b, 0);
        assertEquals(1000, a.getBalance(), THRESHOLD);
        assertEquals(0, b.getBalance(), THRESHOLD);
        a.transfer(b, 0.01);
        assertEquals(999.99, a.getBalance(), THRESHOLD);
        assertEquals(0.01, b.getBalance(), THRESHOLD);
        a.transfer(b, 500);
        assertEquals(499.99, a.getBalance(), THRESHOLD);
        assertEquals(500.01, b.getBalance(), THRESHOLD);
        a.transfer(b, 498.99);
        assertEquals(1, a.getBalance(), THRESHOLD);
        assertEquals(999, b.getBalance(), THRESHOLD);
        a.transfer(b, 1);
        assertEquals(0, a.getBalance(), THRESHOLD);
        assertEquals(1000, b.getBalance(), THRESHOLD);
    }

    @Test
    void constructorTest() {
        Account bankAccount = new CheckingAccount( 200, "1");

        assertEquals(200, bankAccount.getBalance());

        //non-negative balance with two decimal places or less
        bankAccount = new CheckingAccount( 0, "1");
        assertEquals(0, bankAccount.getBalance(), THRESHOLD);
        bankAccount = new CheckingAccount( 0.01, "1");
        assertEquals(0.01, bankAccount.getBalance(), THRESHOLD);
        bankAccount = new CheckingAccount( 0.99, "1");
        assertEquals(0.99, bankAccount.getBalance(), THRESHOLD);
        bankAccount = new CheckingAccount( 9876.5, "1");
        assertEquals(9876.5, bankAccount.getBalance(), THRESHOLD);
        bankAccount = new CheckingAccount(248, "1");
        assertEquals(248, bankAccount.getBalance(), THRESHOLD);

        //non-negative balance with more than two decimal places
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount(0.001, "1"));
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount(0.9999, "1"));
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( 369.333, "1"));
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( Double.MAX_VALUE, "1"));
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( Double.MIN_VALUE, "1"));

        //negative balance with two decimal places or less
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( -0.01, "1"));
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( -0.99, "1"));
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( -10.2, "1"));
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( -125, "1"));

        //negative balance with more than two decimal places
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( -0.001, "1"));
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( -0.9999, "1"));
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( -369.333, "1"));
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( -Double.MAX_VALUE, "1"));
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( -Double.MIN_VALUE, "1"));

        //for now assuming any non-empty string is a valid id, may have to update these tests later
        bankAccount = new CheckingAccount(50, "1");
        assertEquals("1", bankAccount.getID());
        bankAccount = new CheckingAccount(50, "abc");
        assertEquals("abc", bankAccount.getID());

        //checking with emptyString, illegal argument for balance takes precedence
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( -0.001, ""));//Bad balance takes precedence
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount( 50, ""));

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
