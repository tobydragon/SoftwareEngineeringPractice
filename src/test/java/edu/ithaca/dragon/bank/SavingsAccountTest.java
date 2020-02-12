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
        CentralBank centralBank1 = new CentralBank();
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("a-@b.com", 200, "Savings")); // integration test invalid equivalence class (invalid email, border case)
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("a@b.com", 0.001, "Savings")); // integration test invalid equivalence class (invalid starting balance, border case)
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("a@b.com", 0.000001, "Savings")); // integration test invalid equivalence class (invalid starting balance, middle case)
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("a@b.com", 200, "savings")); // integration test invalid equivalence class (invalid account type, border case)
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("a@b.com", 200, "Saving")); // integration test invalid equivalence class (invalid account type, border case)
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("a@b.com", 200, "Funding")); // integration test invalid equivalence class (invalid account type, middle case)

        centralBank1.createAccount("a@b.com", 200, "Savings"); // integration test valid equivalence class (valid starting balance, middle case)
        assertEquals(200, centralBank1.getAccounts()[0].getBalance());
        centralBank1.createAccount("a@b.com", 0.01, "Savings"); // integration test valid equivalence class (valid starting balance, border case)
        assertEquals(0.01, centralBank1.getAccounts()[1].getBalance());
    }

    @Test
    void setInterestRateTest(){
        SavingsAccount account1 = new SavingsAccount("a@b.com", 100, "s1");
        assertThrows(IllegalArgumentException.class, ()-> account1.setInterestRate(-2)); // unit test invalid equivalence class (negative amount, middle case)
        assertThrows(IllegalArgumentException.class, ()->account1.setInterestRate(0)); // unit test invalid equivalence class (negative amount, border case)
        assertThrows(IllegalArgumentException.class, ()->account1.setInterestRate(155)); // unit test invalid equivalence class (amount greater than or equal to 100, middle case)
        assertThrows(IllegalArgumentException.class, ()->account1.setInterestRate(100)); // unit test invalid equivalence class (amount greater than or equal to 100, border case)
        assertThrows(IllegalArgumentException.class, ()->account1.setInterestRate(0.00001)); // unit test invalid equivalence class (amount has more than 2 decimal places, middle case)
        assertThrows(IllegalArgumentException.class, ()->account1.setInterestRate(0.001)); // unit test invalid equivalence class (amount has more than 2 decimal places, border case)
        account1.setInterestRate(50); // unit test valid equivalence class (valid amount, middle case)
        assertEquals(50, account1.getInterestRate());
        account1.setInterestRate(0.01); // unit test valid equivalence class (valid amount, border case)
        assertEquals(0.01, account1.getInterestRate());
        account1.setInterestRate(99.99); // unit test valid equivalence class (valid amount, border case)
        assertEquals(99.99, account1.getInterestRate());
    }

    @Test
    void compoundInterestTest(){

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
