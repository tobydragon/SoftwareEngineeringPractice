package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckingTest {
    @Test
    void constructorTest() throws IllegalArgumentException{
        //checks constructor is working properly
        Checking checkingAcct = new Checking("1234567890", "Bob Loblaw", "dog123", 100);
        assertEquals("1234567890", checkingAcct.getAcctId());
        assertEquals("Bob Loblaw", checkingAcct.getName());

        //checks that it throws exceptions
        //invalid account id (less than 10)
        assertThrows(IllegalArgumentException.class, () -> new Checking("10", "Bob Loblaw", "dog123", 100));
        //invalid name
        assertThrows(IllegalArgumentException.class, () -> new Checking("1234567890", "123 Joe", "dog123", 100));
        //negative balance
        assertThrows(IllegalArgumentException.class, () -> new Checking("1234567890", "Bob Loblaw", "dog123", -100));
        //balance with more than 2 decimal places
        assertThrows(IllegalArgumentException.class, () -> new Checking("1234567890", "Bob Loblaw", "dog123", 10.342));
    }

    @Test
    void isNameValidTest(){
        assertTrue(Checking.isNameValid("Bob Loblaw"));
        assertTrue(Checking.isNameValid("John M Labler"));
        assertTrue(Checking.isNameValid("Steven M ELridge Smith"));

        assertFalse(Checking.isNameValid("Flor"));
        assertFalse(Checking.isNameValid("Bob John123"));
        assertFalse(Checking.isNameValid("@Jerome Johnson"));
    }

    @Test
    void checkBalanceTest(){
        Checking checkingAcct = new Checking("1234567890", "Bob Lob", "dog123", 100);
        assertEquals(100, checkingAcct.checkBalance("1234567890"));

        checkingAcct = new Checking("1234567890", "Bob Lob", "dog123", 424.35);
        assertEquals(424.35, checkingAcct.checkBalance("1234567890"));

        checkingAcct = new Checking("1234567890", "Bob Lob", "dog123", 0);
        assertEquals(0, checkingAcct.checkBalance("1234567890"));
    }

    @Test
    void withdrawTest() throws InsufficientFundsException, AcctFrozenException{
        //zero balance
        Checking checkingAcct = new Checking("1234567890", "Bob Lob", "dog123", 75.24);
        checkingAcct.withdraw("1234567890", 75.24);
        assertEquals(0, checkingAcct.checkBalance("1234567890"));

        //normal positive case
        checkingAcct = new Checking("1234567890", "Bob Lob", "dog123", 100);
        checkingAcct.withdraw("1234567890", 50);
        assertEquals(50, checkingAcct.checkBalance("1234567890"));

        //two in a row
        checkingAcct.withdraw("1234567890", 30.25);
        assertEquals(19.75, checkingAcct.checkBalance("1234567890"));

        //check overdraw
        Checking finalCheckingAcct = new Checking("1234567890", "Bobby J", "dog", 200);
        assertThrows(InsufficientFundsException.class, ()-> finalCheckingAcct.withdraw("1234567890", 200.25));

        //check negative amount
        assertThrows(IllegalArgumentException.class, ()-> finalCheckingAcct.withdraw("1234567890", -130));

        //check amount with more than 2 decimal places
        assertThrows(IllegalArgumentException.class, ()-> finalCheckingAcct.withdraw("1234567890", 2.4422));
    }

    @Test
    void depositTest() throws AcctFrozenException{
        //zero value
        Checking checkingAcct = new Checking("1234567890", "Bob G", "bao", 200);
        checkingAcct.deposit("1234567890", 0);
        assertEquals(200, checkingAcct.checkBalance("1234567890"));

        //normal positive case
        checkingAcct = new Checking("1234567890", "Bob Lob", "dog123", 100);
        checkingAcct.deposit("1234567890", 50);
        assertEquals(150, checkingAcct.checkBalance("1234567890"));

        //two in a row
        checkingAcct.deposit("1234567890", 23.63);
        assertEquals(173.63, checkingAcct.checkBalance("1234567890"));

        Checking finalCheckingAcct = new Checking("1234567890", "Bill Mill", "dg", 140);
        //check negative amount
        assertThrows(IllegalArgumentException.class, ()-> finalCheckingAcct.withdraw("1234567890", -130));

        //check amount with more than 2 decimal places
        assertThrows(IllegalArgumentException.class, ()-> finalCheckingAcct.withdraw("1234567890", 2.4422));
    }

    /*@Test
    void transferTest() throws InsufficientFundsException{
        Checking checkingAcctA = new Checking("1234567890", "Jim Bean", "dogog", 100);
        Checking checkingAcctB = new Checking("0987654321", "John Bon", "froggs", 100);

        // check positive
        checkingAcctA.transfer("1234567890", "01234567890", 50);
        assertEquals(50, checkingAcctA.checkBalance("1234567890"));
        assertEquals(150, checkingAcctB.checkBalance("0987654321"));

        //check two in a row
        checkingAcctA.transfer("1234567890", "0987654321", 25);
        assertEquals(25, checkingAcctA.checkBalance("1234567890"));
        assertEquals(175, checkingAcctB.checkBalance("0987654321"));

        //check swapped roles
        checkingAcctB.transfer("0987654321", "1234567890", 30.75);
        assertEquals(55.75, checkingAcctA.checkBalance("1234567890"));
        assertEquals(144.25, checkingAcctB.checkBalance("0987654321"));

        Checking finalCheckingAcctA = new Checking("1234567890", "Jim Bean", "dogog", 100);
        Checking finalCheckingAcctB = new Checking("0987654321", "John Bon", "froggs", 100);
        // check negative
        assertThrows(IllegalArgumentException.class, () -> finalCheckingAcctA.transfer("1234567890", "0987654321", -50));

        //check more than 2 decimal points
        assertThrows(IllegalArgumentException.class, ()-> finalCheckingAcctB.transfer("0987654321", "1234567890", 30.4222));

        // check overdraw
        assertThrows(InsufficientFundsException.class, () -> finalCheckingAcctA.transfer("1234567890", "0987654321", 101));
    }*/

    @Test
    void transactionHistoryTest() throws InsufficientFundsException, AcctFrozenException{
        //check deposit
        Checking checkingAcct = new Checking("1234567890", "Bob James", "frog", 200);
        checkingAcct.deposit("1234567890", 100);
        assertEquals("deposit of 100.0", checkingAcct.transactionHistory("1234567890"));

        //check withdraw
        checkingAcct = new Checking("1234567890", "Bob James", "frog", 200);
        checkingAcct.withdraw("1234567890", 50);
        assertEquals("withdraw of 50.0", checkingAcct.transactionHistory("1234567890"));

        //check deposit then withdraw
        checkingAcct = new Checking("1234567890", "Bob James", "frog", 200);
        checkingAcct.deposit("1234567890", 50);
        checkingAcct.withdraw("1234567890", 30.25);
        assertEquals("deposit of 50.0; withdraw of 30.25", checkingAcct.transactionHistory("1234567890"));

        //check withdraw then deposit
        checkingAcct = new Checking("1234567890", "Bob James", "frog", 200);
        checkingAcct.withdraw("1234567890", 145);
        checkingAcct.deposit("1234567890", 90);
        assertEquals("withdraw of 145.0; deposit of 90.0", checkingAcct.transactionHistory("1234567890"));

        //check three withdraws in a row
        checkingAcct = new Checking("1234567890", "Bob James", "frog", 200);
        checkingAcct.withdraw("1234567890", 50);
        checkingAcct.withdraw("1234567890", 14);
        checkingAcct.withdraw("1234567890", 54.5);
        assertEquals("withdraw of 50.0; withdraw of 14.0; withdraw of 54.5", checkingAcct.transactionHistory("1234567890"));

        //check five deposits in a row
        checkingAcct = new Checking("1234567890", "Bob James", "frog", 200);
        checkingAcct.deposit("1234567890", 50);
        checkingAcct.deposit("1234567890", 301);
        checkingAcct.deposit("1234567890", 19.35);
        checkingAcct.deposit("1234567890", 29.5);
        checkingAcct.deposit("1234567890", 130.45);
        assertEquals("deposit of 50.0; deposit of 301.0; deposit of 19.35; deposit of 29.5; deposit of 130.45", checkingAcct.transactionHistory("1234567890"));
    }

}
