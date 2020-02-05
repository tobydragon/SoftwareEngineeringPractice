package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DepositTest {
    @Test
    void depositTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        // Negative, One to Two Decimals
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-1.01)); // border case
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-53.83));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-9999999.9)); // border case

        // Positive, One to Two Decimals
        bankAccount.deposit(0);
        assertEquals(200, bankAccount.getBalance()); //border case
        bankAccount.deposit(100);
        assertEquals(300, bankAccount.getBalance());
        bankAccount.deposit(999);
        assertEquals(1299, bankAccount.getBalance()); //border case

        // Negative, Multiple Decimals
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-1.0000001)); // border case
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-7.48));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-9999999.9999999)); // border case

        // Positive, Multiple Decimals
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(0.000001)); // border case
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(92.498865));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(9999999.999999)); //border case
    }
}