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
    }

    @Test
    void withdrawTest(){
        assertFalse(true);
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
