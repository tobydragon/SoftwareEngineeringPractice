package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CentralBankTest {
    @Test
    void confirmCredentialsTest() {
        //TODO
        CentralBank testBank = new CentralBank();
        testBank.createAccount("User01", 100);
    }

    @Test
    void checkBalanceTest() {
        CentralBank testBank = new CentralBank();
        testBank.createAccount("User01", 100);
        assertEquals(100, testBank.checkBalance("User01"));

        testBank.createAccount("User02", 0);
        assertEquals(0, testBank.checkBalance("User02"));
        assertNotEquals(0, testBank.checkBalance("User02"));

        testBank.createAccount("User03", 1);
        assertEquals(1, testBank.checkBalance("User03"));
        assertNotEquals(1, testBank.checkBalance("User02"));
        assertNotEquals(1, testBank.checkBalance("User01"));
    }

    @Test
    void withdrawTest() throws IllegalArgumentException, InsufficientFundsException{
        CentralBank testBank = new CentralBank();
        testBank.createAccount("User01", 100);

        testBank.withdraw("User01", 10);
        assertEquals(90, testBank.checkBalance("User01"));

        testBank.withdraw("User01", 1);
        assertEquals(89, testBank.checkBalance("User01"));

        testBank.withdraw("User01", 0);
        assertEquals(89, testBank.checkBalance("User01"));

        testBank.withdraw("User01", 10.5);
        assertEquals(78.5, testBank.checkBalance("User01"));

        testBank.withdraw("User01", 10.50);
        assertEquals(68, testBank.checkBalance("User01"));

        testBank.withdraw("User01", 10.500);
        assertEquals(57.5, testBank.checkBalance("User01"));

        assertThrows(IllegalArgumentException.class, ()->testBank.withdraw("User01", 10.123));

        assertThrows(InsufficientFundsException.class, () -> testBank.withdraw("User01", 1000));

        assertThrows(IllegalArgumentException.class, () -> testBank.withdraw("User01", -1000));

    }

    @Test
    void depositTest() throws IllegalArgumentException{
        CentralBank testBank = new CentralBank();
        testBank.createAccount("User01", 100);

        testBank.deposit("User01", 10);
        assertEquals(110, testBank.checkBalance("User01"));

        testBank.deposit("User01", 1);
        assertEquals(111, testBank.checkBalance("User01"));

        testBank.deposit("User01", 0);
        assertEquals(111, testBank.checkBalance("User01"));

        testBank.deposit("User01", 1.5);
        assertEquals(112.5, testBank.checkBalance("User01"));

        testBank.deposit("User01", 1.55);
        assertEquals(114.05, testBank.checkBalance("User01"));

        assertThrows(IllegalArgumentException.class, () -> testBank.deposit("User01", 1.555));

        assertThrows(IllegalArgumentException.class, () -> testBank.deposit("User01", -1));
    }

    @Test
    void getHistoryTest() {

    }

    @Test
    void createAccountTest() {
        CentralBank testBank = new CentralBank();
        testBank.createAccount("User01", 100);
        assertEquals(100, testBank.checkBalance("User01"));

        testBank.createAccount("User02", 1);
        assertEquals(1, testBank.checkBalance("User02"));

        testBank.createAccount("User03", 1.000);
        assertEquals(0, testBank.checkBalance("User03"));

        testBank.createAccount("User04", 0);
        assertEquals(0, testBank.checkBalance("User04"));

        assertThrows(IllegalArgumentException.class, ()-> testBank.createAccount("User05", -1));
        assertThrows(IllegalArgumentException.class, ()-> testBank.createAccount("User05", 1.234));
    }

    @Test
    void closeAccountTest() throws NonExistentAccountException{
        CentralBank testBank = new CentralBank();
        testBank.createAccount("User01", 100);

        testBank.closeAccount("User01");
        assertThrows(NonExistentAccountException.class, ()->testBank.checkBalance("User01"));
    }

    @Test
    void getSuspiciousAccountsTest() {

    }

    @Test
    void freezeAccountTest() {

    }

    @Test
    void unfreezeAccountTest() {

    }
}
