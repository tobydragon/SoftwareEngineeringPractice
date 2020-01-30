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
    void withdrawTest() throws InsufficientFundsException {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        //non-negative amount less than or equal to balance
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());

        //non-negative amount less than or equal to balance
        bankAccount.withdraw(0);
        assertEquals(100, bankAccount.getBalance());

        //non-negative amount greater than balance
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(101));

        //non-negative amount greater than balance
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));

        //negative amount
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-100));

        //non-negative amount less than or equal to balance
        bankAccount.withdraw(100);
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    void isEmailValidTest() {
        //valid prefix and domain
        assertTrue(BankAccount.isEmailValid("a@b.com"));
        //missing prefix and/or domain
        assertFalse(BankAccount.isEmailValid(""));

        //prefix with underscore, period, or dash not followed by one or more letter or number
        assertFalse(BankAccount.isEmailValid("abc-@mail.com"));
        //prefix with underscore, period, or dash followed by one or more letter or number
        assertTrue(BankAccount.isEmailValid("abc-d@mail.com"));
        //prefix with underscore, period, or dash not followed by one or more letter or number
        assertFalse(BankAccount.isEmailValid("abc..def@mail.com"));
        //prefix with underscore, period, or dash followed by one or more letter or number
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));
        //prefix with underscore, period, or dash not preceded by one or more letter or number
        assertFalse(BankAccount.isEmailValid(".abc@mail.com"));
        //valid prefix and domain
        assertTrue(BankAccount.isEmailValid("abc@mail.com"));
        //prefix with invalid character
        assertFalse(BankAccount.isEmailValid("abc#def@mail.com"));
        //prefix with underscore, period, or dash followed by one or more letter or number
        assertTrue(BankAccount.isEmailValid("abc_def@mail.com"));
        //last portion of the domain without at least two characters
        assertFalse(BankAccount.isEmailValid("abc.def@mail.c"));
        //last portion of the domain with at least two characters
        assertTrue(BankAccount.isEmailValid("abc.def@mail.cc"));
        //domain with invalid character
        assertFalse(BankAccount.isEmailValid("abc.def@mail#archive.com"));
        //domain with period or dash followed by one or more letter or number
        assertTrue(BankAccount.isEmailValid("abc.def@mail-archive.com"));
        //last portion of the domain without at least two characters
        assertFalse(BankAccount.isEmailValid("abc.def@mail"));
        //last portion of the domain with at least two characters
        assertTrue(BankAccount.isEmailValid("abc.def@mail.org"));
        //domain with period or dash not followed by one or more letter or number
        assertFalse(BankAccount.isEmailValid("abc.def@mail..com"));
        //prefix with underscore, period, or dash followed by one or more letter or number
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));
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