package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        //negative balance (invalid) equivalence class - Commenting this out since this gives an invalid argument
        //BankAccount bankAccount1 = new BankAccount("a@b.com", -5);
        //assertEquals(-5,bankAccount1.getBalance());
        //BankAccount bankAccount2 = new BankAccount("a@b.com", -200);
        //assertEquals(-200,bankAccount2.getBalance());

        //0 balance equivalence class - Commenting this out since I made 0 an invalid option
        //BankAccount bankAccount3 = new BankAccount("a@b.com", 0);
        //assertEquals(0,bankAccount3.getBalance());

        //positive balance equivalence class
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance());
        BankAccount bankAccount4 = new BankAccount("a@b.com", 5);
        assertEquals(5,bankAccount4.getBalance());
    }

    @Test
    void withdrawTest() {

        //part of non-negative and smaller equivalence class
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());
        //bankAccount.withdraw(0);
        //assertEquals(100, bankAccount.getBalance());
        bankAccount.withdraw(5);
        assertEquals(95,bankAccount.getBalance());

        //non-negative and larger equivalence class
        BankAccount bankAccount1 = new BankAccount("a@b.com",200);
        bankAccount1.withdraw(500);
        assertEquals(200,bankAccount1.getBalance());
        bankAccount1.withdraw(1000);
        assertEquals(200,bankAccount1.getBalance());

        /*
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
        */

        //decimal tests
        //negatives and 3 decimals

        BankAccount bankAccount4 = new BankAccount("a@b.com",200);

        assertThrows(IllegalArgumentException.class, ()->bankAccount4.withdraw(0.00));
        assertThrows(IllegalArgumentException.class, ()->bankAccount4.withdraw(0.000));
        assertThrows(IllegalArgumentException.class, ()->bankAccount4.withdraw(.001));
        assertThrows(IllegalArgumentException.class, ()->bankAccount4.withdraw(.999));
        assertThrows(IllegalArgumentException.class, ()->bankAccount4.withdraw(-.01));
        assertThrows(IllegalArgumentException.class, ()->bankAccount4.withdraw(-.99));
        assertThrows(IllegalArgumentException.class, ()->bankAccount4.withdraw(-.001));
        assertThrows(IllegalArgumentException.class, ()->bankAccount4.withdraw(-.999));

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
        assertFalse(bankAccount.getAcctFrozen());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));

        //check for exception thrown correctly for decimal balance
        //negative 3 decimal
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 0.00));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 0.000));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", .001));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", .999));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", -.01));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", -.99));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", -.001));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", -.999));
    }

    @Test
    void acctFrozenTest(){
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertFalse(bankAccount.getAcctFrozen());

        bankAccount.setAcctFrozen(true); //freeze
        assertTrue(bankAccount.getAcctFrozen());


    }

    @Test
    void acctUnfrozenTest(){
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertFalse(bankAccount.getAcctFrozen());

        bankAccount.setAcctFrozen(true); //freeze
        assertTrue(bankAccount.getAcctFrozen());

        bankAccount.setAcctFrozen(false); //unfreeze
        assertFalse(bankAccount.getAcctFrozen());
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

        assertFalse(BankAccount.isEmailValid("ab__c@mail.com")); // testing _ and __

        assertFalse(BankAccount.isEmailValid("ab#c@mail.com"));
        assertFalse(BankAccount.isEmailValid("ab##c@mail.com")); //testing # symbol

        assertFalse(BankAccount.isEmailValid("abc@mail.c"));
        assertFalse(BankAccount.isEmailValid("abvc@mail.")); //testing need two characters after .

    }

    @Test
    void isAmountValidTest(){
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        //equivalence classes:
        //positive and 2 decimal place amounts
        assertTrue(BankAccount.isAmountValid(.01));
        assertTrue(BankAccount.isAmountValid(.99));

        //positive and 3 decimal places
        assertFalse(BankAccount.isAmountValid(.001));
        assertFalse(BankAccount.isAmountValid(.999));

        //0 and 2 decimal places (0 is not valid)
        assertFalse(BankAccount.isAmountValid(0.00));

        //0 and 3 decimals (0 is not valid)
        assertFalse(BankAccount.isAmountValid(0.000));

        //negative and 2 decimals
        assertFalse(BankAccount.isAmountValid(-.01));
        assertFalse(BankAccount.isAmountValid(-.99));

        //negative and 3 decimals
        assertFalse(BankAccount.isAmountValid(-.001));
        assertFalse(BankAccount.isAmountValid(-.999));
    }

    @Test
    void depositTest(){
        //equivalence classes:
        // positive, negative, 0

        //positive numbers
        //no decimal, or decimal of 2 places
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.deposit(200);
        assertEquals(400, bankAccount.getBalance());
        bankAccount.deposit(1);
        assertEquals(401,bankAccount.getBalance());
        bankAccount.deposit(.01);
        assertTrue(bankAccount.getBalance()-401.01 < .1);
        bankAccount.deposit(.99);
        assertTrue(bankAccount.getBalance()-402 < .1);

        //decimal of 3 places
        BankAccount bankAccount1 = new BankAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount1.deposit(.001));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount1.deposit(.999));

        // negative numbers
        //no decimal or 2 places
        assertThrows(IllegalArgumentException.class, ()-> bankAccount1.deposit(-200));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount1.deposit(-1));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount1.deposit(-.01));

        //decimal of 3 places
        assertThrows(IllegalArgumentException.class, ()-> bankAccount1.deposit(-.001));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount1.deposit(-.999));

        //ZERO
        assertThrows(IllegalArgumentException.class, ()-> bankAccount1.deposit(0));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount1.deposit(0.00));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount1.deposit(0.000));

    }

    @Test
    void transferTest(){
        //equivalence classes:
        // positive, negative, 0

        BankAccount bankAccount1 = new BankAccount("a@b.com",100);
        BankAccount bankAccount2 = new BankAccount("b@a.com", 500);

        //positive numbers
        //no decimal, or decimal of 2 places, within available balance
        bankAccount2.transfer(150, bankAccount1); //no decimal
        assertEquals(250, bankAccount1.getBalance()); //check balance of account getting the money
        assertEquals(350, bankAccount2.getBalance()); //check balance of account withdrawn from
        bankAccount2.transfer(50.00, bankAccount1); //decimal
        assertEquals(300, bankAccount1.getBalance()); //check balance of account getting the money
        assertEquals(300, bankAccount2.getBalance()); //check balance of account withdrawn from

        //positive numbers
        //no decimal, or decimal of 2 places, out of balance range
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.transfer(400,bankAccount1));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.transfer(400.00,bankAccount1));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.transfer(100.001,bankAccount1));//3 decimal

        //ZERO
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.transfer(0,bankAccount1));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.transfer(0.00,bankAccount1));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.transfer(0.000,bankAccount1));

        //negative numbers
        //no decimal, or decimal of 2 places, within available balance
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.transfer(-100,bankAccount1));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.transfer(-50.00,bankAccount1));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.transfer(-50.001,bankAccount1));//3 decimal

    }
}