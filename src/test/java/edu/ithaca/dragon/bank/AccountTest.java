package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountTest {
    private static final double THRESHOLD = 0.001;

    @Test
    void depositTest() {
        CheckingAccount bankAccount = new CheckingAccount(0, null);

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
}
