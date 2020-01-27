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
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance());
    }

    @Test
    void isEmailValidTest(){
        //Prefix length tests
        //Invalid case (0)
        assertFalse(BankAccount.isEmailValid("@mail.com"));  //Edge case, size 0
        //Valid case (1-infinity)
        assertTrue(BankAccount.isEmailValid( "a@mail.com"));  //Edge case, size 1
        assertTrue(BankAccount.isEmailValid( "abcdef@mail.com"));  //Normal case, size 5

        //Invalid character tests:
        //Valid case (0)
        assertTrue(BankAccount.isEmailValid( "abcdef@mail.com"));  //Edge case, 0 invalid characters
        //Invalid case (1-infinity)
        assertFalse( BankAccount.isEmailValid("abc#def@mail.com"));  //Edge case, 1 invalid character
        assertFalse( BankAccount.isEmailValid("a^bc#de%f@mail.com"));  //Normal case, 3 invalid characters

        //Number of @ signs tests:
        //Invalid case (0)
        assertFalse(BankAccount.isEmailValid( "abcdef.mail.com"));  //Edge case, 0 @ signs
        //Valid case (1)
        assertTrue(BankAccount.isEmailValid( "abcdef@mail.com"));  //Edge case, 1 @ sign
        //Invalid case (2-infinity)
        assertFalse(BankAccount.isEmailValid( "abcdef@@mail.com"));  //Edge case, 2 @ signs
        assertFalse(BankAccount.isEmailValid( "abcdef@@@@@mail.com"));  //Normal case, 5 @ signs

        //.com length tests:
        //Invalid case (0-1)
        assertFalse(BankAccount.isEmailValid( "abcdef@mail"));  //Edge case, 0 .com length
        assertFalse(BankAccount.isEmailValid( "abcdef@mail."));  //Edge case, 0 .com length
        assertFalse(BankAccount.isEmailValid( "abcdef@mail.c"));  //Edge case, 1 .com length
        //Valid case (2-infinity)
        assertTrue(BankAccount.isEmailValid( "abcdef@mail.co"));  //Edge case, 2 .com length
        assertTrue(BankAccount.isEmailValid( "abcdef@mail.info"));  //Normal case, 4 .com length

        //Alphanumeric characters in a row tests:
        //Valid case (0-1)
        assertTrue(BankAccount.isEmailValid( "abcdef@mail.com"));  //Normal case, 0 non-alphanum character, 0 in a row
        assertTrue(BankAccount.isEmailValid( "abc.def@mail.com"));  //Normal case, 1 non-alphanum character, 1 in a row
        assertTrue(BankAccount.isEmailValid( "a.b.c.d.e.f@mail.com"));  //Edge case, 5 non-alphanum characters, 1 in a row
        //Invalid case (2-infinity)
        assertFalse(BankAccount.isEmailValid( "abc..def@mail.com"));  //Edge case, 2 non-alphanum characters, 2 in a row
        assertFalse(BankAccount.isEmailValid( "a.b.c..d.e.f@mail.com"));  //Normal case, 6 non-alphanum characters, 2 in a row
        assertFalse(BankAccount.isEmailValid( "abc.....def@mail.com"));  //Normal case, 5 non-alphanum characters, 5 in a row

        //Alphanumeric starting/ending characters test:
        //Valid case (Prefix, domain, and .com all start and end with alphanumeric characters):
        assertTrue(BankAccount.isEmailValid( "abcdef@mail.com"));  //Edge case, all valid
        //Invalid case (Prefix, domain, or .com don't start or end with alphanumeric characters):
        assertFalse(BankAccount.isEmailValid( "-abcdef@mail.com"));  //Edge case, prefix doesn't start with alphanumeric character
        assertFalse(BankAccount.isEmailValid( "abcdef-@mail.com"));  //Edge case, prefix doesn't end with alphanumeric character
        assertFalse(BankAccount.isEmailValid( "abcdef@-mail.com"));  //Edge case, suffix doesn't start with alphanumeric character
        assertFalse(BankAccount.isEmailValid( "abcdef@mail-.com"));  //Edge case, suffix doesn't end with alphanumeric character
        assertFalse(BankAccount.isEmailValid( "abcdef@mail.-com"));  //Edge case, domain doesn't start with alphanumeric character
        assertFalse(BankAccount.isEmailValid( "abcdef@mail.com-"));  //Edge case, domain doesn't end with alphanumeric character
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