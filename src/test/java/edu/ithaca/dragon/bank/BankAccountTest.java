package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private static final double THRESHOLD = .001;

    @Test
    void getBalanceTest() {
        //negative balance
        BankAccount bankAccount = new BankAccount("a@b.com", -200);
        assertEquals(-200, bankAccount.getBalance());

        //non-negative balance
        bankAccount = new BankAccount("a@b.com", 0);
        assertEquals(0, bankAccount.getBalance());

        //non-negative balance
        bankAccount = new BankAccount("a@b.com", 100);
        assertEquals(100, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() throws InsufficientFundsException {
        BankAccount bankAccount = new BankAccount("a@b.com", 1000);

        //non-negative amount less than or equal to balance with more than two decimal places
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(0.001));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(0.9999));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(3141.592));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(Double.MIN_VALUE));

        //non-negative amount greater than balance with two decimal places or less
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(1000.01));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(5432));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(3216.8));

        //non-negative amount greater than balance with more than two decimal places
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(1000.001));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(9876.543));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(Double.MAX_VALUE));

        //negative amount with two decimal places or less
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-0.01));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-0.99));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-2718.2));

        //negative amount with more than two decimal places
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-0.001));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-0.9999));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-14850.607));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-Double.MIN_VALUE));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-Double.MAX_VALUE));

        //non-negative amount less than or equal to balance with two decimal places or less
        bankAccount.withdraw(0);
        assertEquals(1000, bankAccount.getBalance(), THRESHOLD);
        bankAccount.withdraw(100);
        assertEquals(900, bankAccount.getBalance(), THRESHOLD);
        bankAccount.withdraw(0.01);
        assertEquals(899.99, bankAccount.getBalance(), THRESHOLD);
        bankAccount.withdraw(0.99);
        assertEquals(899, bankAccount.getBalance(), THRESHOLD);
        bankAccount.withdraw(898.99);
        assertEquals(0.01, bankAccount.getBalance(), THRESHOLD);
        bankAccount.withdraw(0.01);
        assertEquals(0, bankAccount.getBalance(), THRESHOLD);
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
    void isAmountValidTest() {
        //non-negative amount with two decimal points or less
        assertTrue(BankAccount.isAmountValid(0));
        assertTrue(BankAccount.isAmountValid(0.01));
        assertTrue(BankAccount.isAmountValid(0.99));
        assertTrue(BankAccount.isAmountValid(250));
        assertTrue(BankAccount.isAmountValid(1234.50));

        //non-negative amount with more than two decimal points
        assertFalse(BankAccount.isAmountValid(0.001));
        assertFalse(BankAccount.isAmountValid(0.9999));
        assertFalse(BankAccount.isAmountValid(536.125));
        assertFalse(BankAccount.isAmountValid(Double.MIN_VALUE));
        assertFalse(BankAccount.isAmountValid(Double.MAX_VALUE));

        //negative amount with two decimal points or less
        assertFalse(BankAccount.isAmountValid(-0.01));
        assertFalse(BankAccount.isAmountValid(-0.99));
        assertFalse(BankAccount.isAmountValid(-120));
        assertFalse(BankAccount.isAmountValid(-946.5));

        //negative amount with more than two decimal points
        assertFalse(BankAccount.isAmountValid(-0.001));
        assertFalse(BankAccount.isAmountValid(-0.9999));
        assertFalse(BankAccount.isAmountValid(-Double.MIN_VALUE));
        assertFalse(BankAccount.isAmountValid(-Double.MAX_VALUE));
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("", 100));

        //non-negative balance with two decimal places or less
        bankAccount = new BankAccount("a@b.com", 0);
        assertEquals(0, bankAccount.getBalance(), THRESHOLD);
        bankAccount = new BankAccount("a@b.com", 0.01);
        assertEquals(0.01, bankAccount.getBalance(), THRESHOLD);
        bankAccount = new BankAccount("a@b.com", 0.99);
        assertEquals(0.99, bankAccount.getBalance(), THRESHOLD);
        bankAccount = new BankAccount("a@b.com", 9876.5);
        assertEquals(9876.5, bankAccount.getBalance(), THRESHOLD);
        bankAccount = new BankAccount("a@b.com", 248);
        assertEquals(248, bankAccount.getBalance(), THRESHOLD);

        //non-negative balance with more than two decimal places
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("a@b.com", 0.001));
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("a@b.com", 0.9999));
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("a@b.com", 369.333));
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("a@b.com", Double.MAX_VALUE));
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("a@b.com", Double.MIN_VALUE));

        //negative balance with two decimal places or less
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("a@b.com", -0.01));
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("a@b.com", -0.99));
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("a@b.com", -10.2));
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("a@b.com", -125));

        //negative balance with more than two decimal places
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("a@b.com", -0.001));
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("a@b.com", -0.9999));
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("a@b.com", -369.333));
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("a@b.com", -Double.MAX_VALUE));
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("a@b.com", -Double.MIN_VALUE));

    }

}