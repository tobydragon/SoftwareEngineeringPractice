package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() throws InsufficientFundsException {
        //classes - fresh account, after withdrawal, after unsuccessful withdrawal
        BankAccount bankAccount = new BankAccount("a@b.com", 1000);

        //fresh account
        assertEquals(1000, bankAccount.getBalance());
        //after withdrawal
        bankAccount.withdraw(100);
        assertEquals(900, bankAccount.getBalance());
        bankAccount.withdraw(500);
        assertEquals(400, bankAccount.getBalance());
        bankAccount.withdraw(400);
        assertEquals(0, bankAccount.getBalance());

        //after unsuccessful withdrawal
        BankAccount unsuccessful = new BankAccount("a@b.com",1000);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(1100));
        assertEquals(1000, bankAccount.getBalance());
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(2000));
        assertEquals(1000, bankAccount.getBalance());
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(Integer.MAX_VALUE));
        assertEquals(1000, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        //classes - sufficient funds, insufficient funds, negative funds
        BankAccount bankAccount = new BankAccount("a@b.com", 1000);
        //sufficient funds
        bankAccount.withdraw(100);
        assertEquals(900, bankAccount.getBalance());
        bankAccount.withdraw(500);
        assertEquals(400, bankAccount.getBalance());
        bankAccount.withdraw(400);
        assertEquals(0, bankAccount.getBalance());
        //insufficient funds
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(max));
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(1));
        //negative numbers, does nothing for now
        BankAccount negative = new BankAccount("a@b.com", 1000);
        negative.withdraw(-1);
        assertEquals(1000, negative.getBalance());
        negative.withdraw(-500);
        assertEquals(1000, negative.getBalance());
        negative.withdraw(min);
        assertEquals(1000, negative.getBalance());

    }

    @Test
    void isEmailValidTest(){
        //one @ symbol class
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse(BankAccount.isEmailValid("abc@def@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc@d@ef@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc@d@ef@ma@il.com"));
        assertFalse( BankAccount.isEmailValid(""));
        //valid special characters in prefix
        assertFalse(BankAccount.isEmailValid("abc-@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc..@mail.com"));
        assertFalse(BankAccount.isEmailValid(".abc@mail.com"));
        //invalid characters in prefix
        assertFalse(BankAccount.isEmailValid("abc#def@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc#de!f@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));
        //invalid suffix characters
        assertFalse(BankAccount.isEmailValid("abc.def@mail#archive.com"));
        assertFalse(BankAccount.isEmailValid("abc.def@mail!ar%chive.com"));
        assertFalse(BankAccount.isEmailValid("abc.def@mail!ar%chi.ve.com"));
        assertFalse(BankAccount.isEmailValid("abc.def@mail--archive.com"));
        assertFalse(BankAccount.isEmailValid("abc.def@mail-arc!h.ive.com"));
        //valid domain
        assertFalse(BankAccount.isEmailValid("abc.def@mail.c"));
        assertFalse(BankAccount.isEmailValid("abc.def@mail"));
        assertFalse(BankAccount.isEmailValid("abc.def@mail..com"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.cc"));

    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}