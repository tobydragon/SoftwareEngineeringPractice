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

        /**for this function, the only two equivalence classes would be withdrawing more than you have
         (giving expected negative balance) and having enough in your balance to withdraw (expected positive).
         This assert test would be apart of the equivalence class that is valid, and it is not a border case.
         There is not an equivalence class for invalid withdrawals (that would give you a negative balance or attempting to withdraw a negative amount) and there is not a border case
         to test withdrawing how much you have. The only border case there should be is withdrawing how much the current balance is and withdrawing 0, because we did
         not specify a maximum withdrawal.
         */
        assertEquals(100, bankAccount.getBalance());
    }

    @Test
    void isEmailValidTest(){
        //Valid Checks
        //I am confused on how equivalence classes and borders would be applied to emails

        //equivalence class of valid email with no symbols and correct domain. border case because minimum digits before domain
        assertTrue(BankAccount.isEmailValid( "a@b.com"));

        //equivalence class of valid email with a symbol and a letter following. border case for letters/digit follow symbol
        assertTrue(BankAccount.isEmailValid( "abc-d@mail.com"));

        //same equivalence class as above, not border case
        assertTrue(BankAccount.isEmailValid( "abc.def@mail.com"));

        //same equivalence class as first test, not border case
        assertTrue(BankAccount.isEmailValid( "abc@mail.com"));

        //same class as second test, not border case
        assertTrue(BankAccount.isEmailValid( "abc_def@mail.com"));

        //same equivalence class as second test but also border case because of the length of .cc
        assertTrue(BankAccount.isEmailValid( "abc.def@mail.cc"));

        //equivalence class of symbol in both prefix and domain, not border case
        assertTrue(BankAccount.isEmailValid( "abc.def@mail-archive.com"));

        //same class as second,not border case
        assertTrue(BankAccount.isEmailValid( "abc.def@mail.org\n"));

        //same class as second, not border case
        assertTrue(BankAccount.isEmailValid( "abc.def@mail.com"));

        //Invalid Checks
        //border case and equivalence class of length
        assertFalse( BankAccount.isEmailValid(""));

        //equivalence class of invalid email because symbol not followed by letter or digit. border case for same reason
        assertFalse( BankAccount.isEmailValid("abc-@mail.com"));

        //equivalence class of invalid email because back to back symbols. not border case
        assertFalse( BankAccount.isEmailValid("abc..def@mail.com"));

        //equivalence class of invalid email because not starting with a letter. not border case
        assertFalse( BankAccount.isEmailValid(".abc@mail.com"));

        //equivalence class of invalid symbol. not border case
        assertFalse( BankAccount.isEmailValid("abc#def@mail.com"));

        //equivalence class of too short of domain after the period, not border case
        assertFalse( BankAccount.isEmailValid("abc.def@mail.c"));

        //equivalence class of having no @ for domain. not border case
        assertFalse( BankAccount.isEmailValid("abc.def@mail#archive.com"));

        //equivalence class of not having a . in domain. not border case
        assertFalse( BankAccount.isEmailValid("abc.def@mail"));

        //equivalence class of having two . in domain. not border case
        assertFalse( BankAccount.isEmailValid("abc.def@mail..com"));
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