package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        //negative balance (invalid) equivalence class
        BankAccount bankAccount1 = new BankAccount("a@b.com", -5);
        assertEquals(-5,bankAccount1.getBalance());
        BankAccount bankAccount2 = new BankAccount("a@b.com", -200);
        assertEquals(-200,bankAccount2.getBalance());

        //0 balance equivalence class
        BankAccount bankAccount3 = new BankAccount("a@b.com", 0);
        assertEquals(0,bankAccount3.getBalance());

        //positive balance equivalence class
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance());
        BankAccount bankAccount4 = new BankAccount("a@b.com", 5);
        assertEquals(5,bankAccount4.getBalance());
    }

    @Test
    void withdrawTest() {
        //no test for floating point numbers or decimals
        //no test for exception throws

        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        //part of non-negative and smaller equivalence class
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());
        bankAccount.withdraw(0);
        assertEquals(100, bankAccount.getBalance());
        bankAccount.withdraw(5);
        assertEquals(95,bankAccount.getBalance());

        //non-negative and larger equivalence class
        BankAccount bankAccount1 = new BankAccount("a@b.com",200);
        bankAccount1.withdraw(500);
        assertEquals(200,bankAccount1.getBalance());
        bankAccount1.withdraw(1000);
        assertEquals(200,bankAccount1.getBalance());

        //negative and smaller equivalence class
        BankAccount bankAccount2 = new BankAccount("a@b.com",200);
        bankAccount2.withdraw(-5);
        assertEquals(200,bankAccount2.getBalance());
        bankAccount2.withdraw(-100);
        assertEquals(200,bankAccount2.getBalance());

        //negative and larger equivalence class
        BankAccount bankAccount3 = new BankAccount("a@b.com",200);
        bankAccount3.withdraw(-500);
        assertEquals(200,bankAccount3.getBalance());
        bankAccount1.withdraw(-1000);
        assertEquals(200,bankAccount3.getBalance());

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
        //border
        assertFalse(BankAccount.isEmailValid("abc-@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc-d@mail.com"));

        //border
        assertFalse(BankAccount.isEmailValid(".abc@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc@mail.com"));

        //border
        assertFalse(BankAccount.isEmailValid("abc.def@mail"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));

        //no test for  .. -- _
        //no test for other symbols besides . - _
        //no test to see that at least two characters follow the . in the domain name

        assertFalse(BankAccount.isEmailValid("ab..c@mail.com"));
        assertFalse(BankAccount.isEmailValid("ab....c@mail.com")); //tests for .. and multiple consecutive .'s
        assertFalse(BankAccount.isEmailValid("ab--c@mail.com")); //testing --

        //this should be true
        //assertFalse(BankAccount.isEmailValid("ab_c@mail.com"));

        assertFalse(BankAccount.isEmailValid("ab__c@mail.com")); // testing _ and __

        assertFalse(BankAccount.isEmailValid("ab#c@mail.com"));
        assertFalse(BankAccount.isEmailValid("ab##c@mail.com")); //testing # symbol

        assertFalse(BankAccount.isEmailValid("abc@mail.c"));
        assertFalse(BankAccount.isEmailValid("abvc@mail.")); //testing need two characters after .

    }

}