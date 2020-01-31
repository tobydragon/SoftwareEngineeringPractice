package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        /*
         * Checks for a bank account with a perfectly valid amount of money. Equivalence case.
         */
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200.00, bankAccount.getBalance());
        /*
         * Checks for bank account with no money at all. Border case.
         */
        bankAccount = new BankAccount("a@b.com", 0);
        assertEquals(0, bankAccount.getBalance());
        /*
         * Checks for a bank account with negative amount of money. Equivalence case.
         */
        bankAccount = new BankAccount("a@b.com", -200);
        assertEquals(-200.00, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        /*
         * Check for proper withdrawal use. Equivalence test.
         */
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());
        /*
         * Check for zero withdrawal. Border test.
         */
        bankAccount.withdraw(0);
        assertEquals(100, bankAccount.getBalance());
        /*
         * Check for invalid negative withdrawal. Border test.
         */
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-1));
        /*
         * Check for invalid negative withdrawal. Equivalence test.
         */
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-100));
        /*
         * Check for valid withdrawal use with valid non-whole number. Border test.
         */
        bankAccount.withdraw(10.5);
        assertEquals(89.5, bankAccount.getBalance());
        /*
         * Check for valid withdrawal use with valid non-whole number. Equivalence test.
         */
        bankAccount.withdraw(10.50);
        assertEquals(79, bankAccount.getBalance());
        /*
         * Check for valid withdrawal use with invalid non-whole number. Border test.
         */
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(5.321));
        /*
         * Check for InsufficientFundsException error. Border test.
         */
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(80));
    }

    @Test
    void isEmailValidTest(){
        /*
         * This tests a valid email made of simple alpha characters, a suffix of length 3, and with a present @ symbol.
         * This is an equivalence test generally speaking, but can be a border test for having/not having an @ symbol.
         */
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        /*
         * This is a border case for having an non-alpha character in the username, and using it validly by making
         * it adjacent to two alpha characters.
         */
        assertTrue( BankAccount.isEmailValid("a-c@b.com"));
        /*
         * This is also the same border case as above, albeit, this may be redundant considering it's testing the
         * same rule.
         */
        assertTrue( BankAccount.isEmailValid("a.c@b.com"));
        /*
         * Same as above.
         */
        assertTrue( BankAccount.isEmailValid("a_c@b.com"));
        /*
         * This is a border case for proper email suffix by having the minimum length required.
         */
        assertTrue( BankAccount.isEmailValid("a@b.cc"));
        /*
         * This is an equivalence/"mid" value test for the suffix, testing it with three characters.
         */
        assertTrue( BankAccount.isEmailValid("a@b.org"));
        /*
         * This is a border test for using non-alpha characters in the domain name validly.
         */
        assertTrue( BankAccount.isEmailValid("a@b-c.com"));
        /*
         * This is a border test for not having an email entered in at all.
         */
        assertFalse( BankAccount.isEmailValid(""));
        /*
         * This is a border test checking for an invalid use of a non-alpha character.
         */
        assertFalse( BankAccount.isEmailValid("a-@b.com"));
        /*
         * This is an equivalence/"mid" value test for an invalid use of a non-alpha character since the above
         * already does it considering @ is also a non-alpha symbol and the above test functions the same way.
         */
        assertFalse( BankAccount.isEmailValid("a..@b.com"));
        /*
         * This is a border test checking for invalidly starting with a non-alpha character.
         */
        assertFalse( BankAccount.isEmailValid(".a@b.com"));
        /*
         * This is an equivalence test where a bunch of invalid characters are incorporated into the email
         * username/prefix.
         */
        assertFalse( BankAccount.isEmailValid("a!@#$%^&*()+=c<>?/@b.com"));
        /*
         * This is a border case for using an invalid suffix that is below the required character length minimum.
         */
        assertFalse( BankAccount.isEmailValid("a@b.c"));
        /*
         * This is an equivalence test where a bunch of invalid characters are incorporated into the email's
         * domain name.
         */
        assertFalse( BankAccount.isEmailValid("a@b!@#$%^&*()+=c<>?/.com"));
        /*
         * This is a border test for not even having a suffix present at all.
         */
        assertFalse( BankAccount.isEmailValid("a@b"));
        /*
         * This is an equivalence test for invalid use of non-alpha characters and improper way to start an
         * email suffix.
         */
        assertFalse( BankAccount.isEmailValid("a@b..com"));

    }

    @Test
    void isAmountValidTest(){
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        /**
         * Equivalence test checking a valid positive mid-value.
         */
        assertTrue(bankAccount.isAmountValid(100));
        /**
         * Border test checking a valid positive border value.
         */
        assertTrue(bankAccount.isAmountValid(1));
        /**
         * Equivalence test checking a valid non-negative number.
         */
        assertTrue(bankAccount.isAmountValid(0));
        /**
         * Border test checking an invalid negative border value.
         */
        assertFalse(bankAccount.isAmountValid(-1));
        /**
         * Border test checking a valid decimal value.
         */
        assertTrue(bankAccount.isAmountValid(100.1));
        /**
         * Equivalence test checking a valid decimal mid-value.
         */
        assertTrue(bankAccount.isAmountValid(100.12));
        /**
         * Border test checking an invalid decimal border value with too many digits.
         */
        assertFalse(bankAccount.isAmountValid(100.123));
        /**
         * Border test checking a valid positive decimal number that needs to be truncated.
         */
        assertTrue(bankAccount.isAmountValid(100.000));

    }

    @Test
    void transferTest() throws IllegalArgumentException, InsufficientFundsException{
        BankAccount bankAccountA = new BankAccount("a@b.com", 200);
        BankAccount bankAccountB = new BankAccount("a@b.com", 200);

        /**
         * Equivalence test for a valid positive mid-value.
         */
        bankAccountA.transfer(100, bankAccountB);
        assertEquals(100, bankAccountA.getBalance());
        assertEquals(300, bankAccountB.getBalance());

        /**
         * Border test for a valid positive border value.
         */
        bankAccountA.transfer(1, bankAccountB);
        assertEquals(99, bankAccountA.getBalance());
        assertEquals(301, bankAccountB.getBalance());

        /**
         * Border test for a valid non-negative border value.
         */
        bankAccountA.transfer(0, bankAccountB);
        assertEquals(99, bankAccountA.getBalance());
        assertEquals(301, bankAccountB.getBalance());

        /**
         * Border test for a valid decimal value.
         */
        bankAccountA.transfer(10.1, bankAccountB);
        assertEquals(88.9, bankAccountA.getBalance());
        assertEquals(311.1, bankAccountB.getBalance());

        /**
         * Equivalence test for a valid decimal mid-value.
         */
        bankAccountA.transfer(10.12, bankAccountB);
        assertEquals(78.78, bankAccountA.getBalance());
        assertEquals(321.22, bankAccountB.getBalance());

        /**
         * Border value for a valid decimal value that needs to be truncated.
         */
        bankAccountA.transfer(10.000, bankAccountB);
        assertEquals(68.78, bankAccountA.getBalance());
        assertEquals(331.22, bankAccountB.getBalance());

        /**
         * Border test for an invalid negative border value.
         */
        assertThrows(IllegalArgumentException.class, ()-> bankAccountA.transfer(-1, bankAccountB));

        /**
         * Equivalence test for an invalid negative mid-value.
         */
        assertThrows(IllegalArgumentException.class, ()-> bankAccountA.transfer(-100, bankAccountB));

        /**
         * Border test for an invalid decimal value with too many digits.
         */
        assertThrows(IllegalArgumentException.class, ()-> bankAccountA.transfer(10.123, bankAccountB));
    }

    @Test
    void depositTest(){
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.deposit(100);

        /**
         * Equivalence test checking a valid positive mid-value.
         */
        assertEquals(300, bankAccount.getBalance());

        /**
         * Border test checking a valid positive border value.
         */
        bankAccount.deposit(1);
        assertEquals(301, bankAccount.getBalance());

        /**
         * Border test checking a valid non-negative border-value.
         */
        bankAccount.deposit(0);
        assertEquals(301, bankAccount.getBalance());

        /**
         * Border test checking a valid positive value with a single decimal place.
         */
        bankAccount.deposit(10.1);
        assertEquals(311.1, bankAccount.getBalance());

        /**
         * Equivalence test checking a valid value with two decimal places.
         */
        bankAccount.deposit(10.12);
        assertEquals(321.22, bankAccount.getBalance());

        /**
         * Border test checking a valid decimal value that needs to be truncated.
         */
        bankAccount.deposit(10.000);
        assertEquals(331.22, bankAccount.getBalance());

        /**
         * Border value testing an invalid negative border-value.
         */
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(-1));

        /**
         * Equivalence value testing an invalid negative border-value.
         */
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(-100));

        /**
         * Border value testing an invalid value with too many decimal places.
         */
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(10.123));
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -100));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 100.123));

    }

}