package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance());
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse( BankAccount.isEmailValid(""));
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

    @Test
    void isEmailValidCorrectTest(){
        String correctEmail = "correct@test.com";
        String incorrectEmail = "incor#rect@test.com";

        String correctEmail2 = "cor-rect@test.com";
        String incorrectEmail2 = "incorrect-@test.com";

        String correctEmail3 = "cor.rect@test.com";
        String incorrectEmail3 = "incor..rect@test.com";

        String correctEmail4 = "cor_rect@test.com";
        String incorrectEmail4 = ".incorrect@test.com";

        assertFalse(BankAccount.isEmailValid(incorrectEmail));
        assertTrue(BankAccount.isEmailValid(correctEmail));

        assertFalse(BankAccount.isEmailValid(incorrectEmail2));
        assertTrue(BankAccount.isEmailValid(correctEmail2));

        assertFalse(BankAccount.isEmailValid(incorrectEmail3));
        assertTrue(BankAccount.isEmailValid(correctEmail3));

        assertFalse(BankAccount.isEmailValid(incorrectEmail4));
        assertTrue(BankAccount.isEmailValid(correctEmail4));

    }

}