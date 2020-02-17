package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import java.nio.channels.IllegalChannelGroupException;

import static org.junit.jupiter.api.Assertions.*;

public class CheckingAccountTest {

    @Test
    void constructorTest(){
        CheckingAccount checkingAccount1 = new CheckingAccount("a@b.com", 200, "c1"); // unit test valid equivalence class (valid email, middle case)
        assertEquals(200, checkingAccount1.getBalance());
        assertThrows(IllegalArgumentException.class, ()-> new CheckingAccount("a@b.c", 200, "c2")); // integration test invalid equivalence class (invalid email, border case)
        assertThrows(IllegalArgumentException.class, ()-> new CheckingAccount("a@b.com", -100, "c3")); // integration test invalid equivalence class (invalid amount, middle case)
        assertThrows(IllegalArgumentException.class, ()-> new CheckingAccount("a@b.com", 0.001, "c4")); // integration test invalid equivalence class (invalid amount, border case)
        CheckingAccount checkingAccount2 = new CheckingAccount("a@b.com", 0.01, "c5"); // integration test valid equivalence class (valid amount, border case)
        assertEquals(0.01, checkingAccount2.getBalance());
    }

    @Test
    void createCheckingAccountTest(){
        CentralBank centralBank1 = new CentralBank();
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("a.@b.c", 200, "Checking")); // integration test invalid equivalence class (invalid email, border case)
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("a@b.com", 0.001, "Checking")); // integration test invalid equivalence class (invalid starting balance, border case)
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("a@b.com", 0.000001, "Checking")); // integration test invalid equivalence class (invalid starting balance, middle case)
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("a@b.com", 200, "checking")); // integration test invalid equivalence class (invalid account type, border case)
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("a@b.com", 200, "Check")); // integration test invalid equivalence class (invalid account type, border case)
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("a@b.com", 200,"Funding")); // integration test invalid equivalence class (invalid account type, middle case)

        centralBank1.createAccount("a@b.com", 200, "Checking"); // integration test valid equivalence class (valid starting balance, middle case)
        assertEquals(200, centralBank1.getAccounts()[0].getBalance());
        centralBank1.createAccount("a@b.com", 0.01, "Checking"); // integration test valid equivalence class (valid starting balance, border case)
        assertEquals(0.01, centralBank1.getAccounts()[1].getBalance());
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        CheckingAccount account1 = new CheckingAccount("a@b.com", 200, "c1");
        assertThrows(IllegalArgumentException.class, ()-> account1.withdraw(-10)); // unit test invalid equivalence class (negative amount, middle case)
        assertThrows(IllegalArgumentException.class, ()-> account1.withdraw(-0.01)); // unit test invalid equivalence class (negative amount, border case)
        assertThrows(IllegalArgumentException.class, ()-> account1.withdraw(0.00001)); // unit test invalid equivalence class (decimal places more than 2, middle case)
        assertThrows(IllegalArgumentException.class, ()-> account1.withdraw(0.001)); // unit test invalid equivalence class (decimal places more than 2, border case)
        assertThrows(InsufficientFundsException.class, ()-> account1.withdraw(300)); // unit test invalid equivalence class (larger than account amount, middle case)
        assertThrows(InsufficientFundsException.class, ()-> account1.withdraw(200.01)); // unit test invalid equivalence class (larger than account amount, border case)
        assertThrows(IllegalArgumentException.class, ()-> account1.withdraw(0)); // unit test invalid equivalence class (invalid amount, middle case)
        account1.withdraw(100); // unit test valid equivalence class (valid withdraw amount, middle case)
        assertEquals(100, account1.getBalance());
    }

    @Test
    void depositTest(){
        assertFalse(true);
    }

    @Test
    void transferTest(){
        assertFalse(true);
    }

}
