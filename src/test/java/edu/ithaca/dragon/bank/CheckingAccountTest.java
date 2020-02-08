package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CheckingAccountTest {
    @Test
    void withdrawCheckingTest() throws InsufficientFundsException{
        CheckingAccount bankAccount = new CheckingAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance());

        //Equivalence Class Has balance
        CheckingAccount bankAccount1 = new CheckingAccount("a@c.com", 300);
        bankAccount1.withdraw(99);//valid withdraw
        assertEquals(201, bankAccount1.getBalance());
        bankAccount1.withdraw(1);//valid withdraw edgecase
        assertEquals(200, bankAccount1.getBalance());
        bankAccount1.withdraw(0.1);
        assertEquals(199.9, bankAccount1.getBalance(),10);
        bankAccount1.withdraw(0.01);
        assertEquals(199.89, bankAccount1.getBalance(), 10);
        assertThrows(InsufficientFundsException.class, ()-> bankAccount1.withdraw(201));
        assertEquals(199.89, bankAccount1.getBalance(), 10);
        assertThrows(InsufficientFundsException.class, ()-> bankAccount1.withdraw(20100));
        assertEquals(199.89, bankAccount1.getBalance(), 10);
        bankAccount1.withdraw(199.89);//perfect withdraw edgecase
        assertEquals(0, bankAccount1.getBalance(), 10);

        //Equivalence Class No balance
        CheckingAccount ba2 = new CheckingAccount("a@c.cm", 0);
        assertThrows(InsufficientFundsException.class, ()-> ba2.withdraw(1));
        assertEquals(0, ba2.getBalance());

        CheckingAccount ba3 = new CheckingAccount("a@c.cm", 200);
        assertThrows(IllegalArgumentException.class, ()-> ba3.withdraw(0));
        assertThrows(IllegalArgumentException.class, ()-> ba3.withdraw(1.001));
        assertThrows(IllegalArgumentException.class, ()-> ba3.withdraw(-14));
        assertThrows(IllegalArgumentException.class, ()-> ba3.withdraw(-0.001));

    }
    @Test
    void constructorTest() {
        CheckingAccount bankAccount = new CheckingAccount("a@b.com", 200);
        assertEquals("a@b.com", bankAccount.getID());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new CheckingAccount("", 100));
        assertThrows(IllegalArgumentException.class, ()-> new CheckingAccount("a@basd.com", 100.001));
        assertThrows(IllegalArgumentException.class, ()-> new CheckingAccount("a@basd.com", -100));
    }

    @Test
    void isAmountValidTest(){
        assertTrue(CheckingAccount.isAmountValid(10));
        assertTrue(CheckingAccount.isAmountValid(10.0));
        assertTrue(CheckingAccount.isAmountValid(10.00));
        assertTrue(CheckingAccount.isAmountValid(10.000000000));
        assertTrue(CheckingAccount.isAmountValid(10.1));
        assertTrue(CheckingAccount.isAmountValid(10.11));
        assertTrue(CheckingAccount.isAmountValid(0.0000000));
        assertTrue(CheckingAccount.isAmountValid(0.1));
        assertTrue(CheckingAccount.isAmountValid(0.01));
        assertTrue(CheckingAccount.isAmountValid(0.00000000001));

        assertFalse(CheckingAccount.isAmountValid(10.001));
        assertFalse(CheckingAccount.isAmountValid(10.0001));
        assertFalse(CheckingAccount.isAmountValid(10.00000001));
        assertFalse(CheckingAccount.isAmountValid(-10));
        assertFalse(CheckingAccount.isAmountValid(-10.0));
        assertFalse(CheckingAccount.isAmountValid(-10.00));
        assertFalse(CheckingAccount.isAmountValid(-10.00000));
        assertFalse(CheckingAccount.isAmountValid(-10.1));
        assertFalse(CheckingAccount.isAmountValid(-10.01));
        assertFalse(CheckingAccount.isAmountValid(-10.001));
        assertFalse(CheckingAccount.isAmountValid(-0.1));
        assertFalse(CheckingAccount.isAmountValid(-0.01));
        assertFalse(CheckingAccount.isAmountValid(-0.001));
    }

    @Test
    void depositTest(){
        CheckingAccount bankAccount = new CheckingAccount("a@a.cc", 0);

        bankAccount.deposit(100);
        assertEquals(100, bankAccount.getBalance(), 10);
        bankAccount.deposit(0.1);
        assertEquals(100.1, bankAccount.getBalance(), 10);
        bankAccount.deposit(0.01);
        assertEquals(100.11, bankAccount.getBalance(), 10);

        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(-1));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(0));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(-1.01));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(1.001));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(-1.001));
    }

    @Test
    void transferTest() throws InsufficientFundsException{
        CheckingAccount bankAccount = new CheckingAccount("a@a.cc", 300);
        CheckingAccount bankAccount1 = new CheckingAccount("A@AA.CC", 0);

        bankAccount.transfer( bankAccount1, 100);
        assertEquals(200, bankAccount.getBalance(), 10);
        assertEquals(100, bankAccount1.getBalance(), 10);
        bankAccount.transfer( bankAccount1, 0.1);
        assertEquals(199.9, bankAccount.getBalance(), 10);
        assertEquals(100.1, bankAccount1.getBalance(), 10);
        bankAccount.transfer( bankAccount1, 0.01);
        assertEquals(199.89, bankAccount.getBalance(), 10);
        assertEquals(100.11, bankAccount1.getBalance(), 10);

        assertThrows(IllegalArgumentException.class, ()-> bankAccount.transfer( bankAccount1,0));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.transfer( bankAccount1, -1));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.transfer( bankAccount1, -1.01));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.transfer( bankAccount1, -1.0001));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.transfer( bankAccount1, 0.001));

        assertThrows(InsufficientFundsException.class, ()->bankAccount.transfer( bankAccount1, 1000));
    }

    @Test
    void getBalanceTest() {
        //Equivalence Class starting positive balance
        CheckingAccount bankAccount = new CheckingAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance());
        bankAccount = new CheckingAccount("a@b.com", 0);
        assertEquals(0, bankAccount.getBalance());
    }
}
