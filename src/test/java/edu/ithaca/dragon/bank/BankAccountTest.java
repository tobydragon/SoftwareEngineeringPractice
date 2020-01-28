
package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {

        //correctly gives you the balance when you have a valid email
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance());

        //gives you your balance when your balance is 0. Valid email.
        BankAccount bankAccount1 = new BankAccount("abc-d@mail.com", 0);
        assertEquals(0, bankAccount1.getBalance());

        //returns your balance when it is negative. Valid email.
        BankAccount bankAccount2 = new BankAccount("abc-d@mail.com", -10);
        assertEquals(-10, bankAccount2.getBalance());

        //correctly gives you the balance when you have a valid email
        BankAccount bankAccount3 = new BankAccount("a@b.com", 800.67);
        assertEquals(800.67, bankAccount3.getBalance());



    }

    @Test
    void withdrawTest() throws InsufficientFundsException {

        //tests for a valid withdrawal that will leave you with a half balance. Valid email is provided.
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());

        //tests for a valid withdrawal that will leave you with no money left in the account. Valid Email.
        BankAccount bankAccount1 = new BankAccount("abc-d@mail.com", 100);
        bankAccount.withdraw(100);
        assertEquals(0, bankAccount.getBalance());

        //tests for an invalid withdrawal of $0 that will leave you with the same balance no matter what it is. Valid email.
        BankAccount bankAccount2 = new BankAccount("abc-d@mail.com", 100);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.withdraw(0));
        assertEquals(100,bankAccount2.getBalance());

        //tests for an invalid withdrawal that is more than your current balance. valid email.
        BankAccount bankAccount3 = new BankAccount("abc-d@mail.com", 348.08);
        assertThrows(InsufficientFundsException.class, ()-> bankAccount3.withdraw(349));
        assertEquals(348.08,bankAccount3.getBalance());

        //tests for an invalid withdrawal that is a negative number. Valid email.
        BankAccount bankAccount4 = new BankAccount("abc-d@mail.com", 590.02);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount4.withdraw(-20));
        assertEquals(590.02,bankAccount4.getBalance());

        //tests for an invalid withdrawal that is a fraction of a penny. Should return an illegal argument exception
        //assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 200).withdraw(2.005));


    }

    @Test
    void isEmailValidTest(){


        assertTrue(BankAccount.isEmailValid("a@b.com"));
        assertTrue(BankAccount.isEmailValid("abc-d@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc_def@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.cc"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail-archive.com"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.org"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.archive.com"));

        //tests the equivalence class for no period.
        assertFalse(BankAccount.isEmailValid("a@b"));
        //tests for no domain after @ sign
        assertFalse(BankAccount.isEmailValid("ab.com@j"));
        //Tests for an invalid domain because it is too short
        assertFalse(BankAccount.isEmailValid("ab@j.c"));
        //tests for a domain that is invalid because it is too long.
        assertFalse(BankAccount.isEmailValid("ab@domain.c"));
        //Tests for a domain that comes before the @ sign and not after
        assertFalse(BankAccount.isEmailValid("ab.com@j"));
        //Tests for an email without an @ sign
        assertFalse(BankAccount.isEmailValid("ab#c#domain.com"));
        //Tests for an email that has a special character before the @ sign.
        assertFalse(BankAccount.isEmailValid("abc-@mail.com"));
        //tests for a an email that has double special characters
        assertFalse(BankAccount.isEmailValid("abc..def@mail.com"));
        //tests for an email that starts with a special character
        assertFalse(BankAccount.isEmailValid(".abc@mail.com"));
        //tests for an email that has an invalid character.
        assertFalse(BankAccount.isEmailValid("abc$def@mail.com"));
        //tests for an email that has invalid domain after the period because it is too short
        assertFalse(BankAccount.isEmailValid("abc.def@mail.c"));
        //tests for an email that has an invalid character after the @ sign.
        assertFalse(BankAccount.isEmailValid("abc.def@mail#archive.com"));
        //tests for an email that has no period after the @ sign
        assertFalse(BankAccount.isEmailValid("abc.def@mail"));
        //tets for an email that has double special characters after the @ sign
        assertFalse(BankAccount.isEmailValid("abc.def@mail..com"));
        //tests for a special character at the end of the email.
        assertFalse(BankAccount.isEmailValid("abc@def.co-"));


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