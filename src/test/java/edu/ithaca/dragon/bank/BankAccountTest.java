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
    void isEmailValidTestUpdated(){
        // checks for a basic, valid email and for empty string
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse( BankAccount.isEmailValid(""));

        // checks for forbidden characters... border case:  one forbidden characters is present
        assertFalse(BankAccount.isEmailValid("ab#c@gmail.com"));
        assertFalse(BankAccount.isEmailValid("ab-c @gmail.com"));

        // checks for 1 period after the @... border case: 0, 1, or 2 periods found
        assertTrue(BankAccount.isEmailValid("hay@mail.com"));
        assertFalse(BankAccount.isEmailValid("hayyy@mailcom"));
        assertFalse(BankAccount.isEmailValid("hay@ma.il.com"));

        // checks for the proper number of @ symbols... border case: 0, 1, or 2 @'s present
        assertTrue(BankAccount.isEmailValid("cmartano@gmail.com"));
        assertFalse(BankAccount.isEmailValid("cmartanoaol.com"));
        assertFalse(BankAccount.isEmailValid("c@martano@yahoo.com"));

        // checks for the number of characters after the last period... border case: 1 or 2
        assertTrue(BankAccount.isEmailValid("name@place.co"));
        assertFalse(BankAccount.isEmailValid("name@place.c"));

        // checks that each dash is a "legal" dash... border case: dash to start, 2 in a row, before @ symbol
        assertTrue(BankAccount.isEmailValid("c-martano@gmail.com"));
        assertFalse(BankAccount.isEmailValid("marta--no@gmail.com"));
        assertFalse(BankAccount.isEmailValid("-christianmar-tano@aol.com"));
        assertFalse(BankAccount.isEmailValid("c-l-martano-@gmail.com"));

        // checks that each period is a "legal" period... border case: period to start, 2 in a row, before @ symbol
        assertTrue(BankAccount.isEmailValid("c.martano@gmail.com"));
        assertFalse(BankAccount.isEmailValid("c.ma..rtano@gmail.com"));
        assertFalse(BankAccount.isEmailValid(".christian@gmail.com"));
        assertFalse(BankAccount.isEmailValid("c.l.martano.@gmail.com"));


        //presence of invalid character
        assertTrue(BankAccount.isEmailValid("abc@google.com"));
        assertFalse( BankAccount.isEmailValid("abc-@mail.com"));
        assertFalse( BankAccount.isEmailValid("abc.@mail.com"));
        assertFalse( BankAccount.isEmailValid(".abc@mail.com"));
        assertFalse( BankAccount.isEmailValid("abc#def@mail.com"));
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