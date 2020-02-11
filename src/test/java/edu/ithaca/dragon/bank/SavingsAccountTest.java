package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SavingsAccountTest {

    @Test
    void constructorTest(){
        SavingsAccount savingsAccount1 = new SavingsAccount("a@b.com", 200, "s1"); // unit test valid equivalence class (valid email, middle case)
        assertEquals(200, savingsAccount1.getBalance());
        assertThrows(IllegalArgumentException.class, ()-> new SavingsAccount("a-@b.com", 200, "s2")); // integration test invalid equivalence class (invalid email, border case)
        assertThrows(IllegalArgumentException.class, ()-> new SavingsAccount("a@b.com", -100, "s3")); // integration test invalid equivalence class (invalid amount, middle case)
        assertThrows(IllegalArgumentException.class, ()-> new SavingsAccount("a@b.com", 0.001, "s4")); // integration test invalid equivalence class (invalid amount, border case)
        SavingsAccount savingsAccount2 = new SavingsAccount("a@b.com", 0.01, "s5"); // integration test valid equivalence class (valid amount, border case)
        assertEquals(0.01, savingsAccount2.getBalance());
    }

    @Test
    void createSavingsAccountTest(){
        assertFalse(true);
    }

    @Test
    void compoundInterestTest(){
        assertFalse(true);
    }

    @Test
    void withdrawTest(){
        assertFalse(true);
    }

    @Test
    void transferTest(){
        assertFalse(true);
    }

}
