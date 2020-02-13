package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ATMTest {
    @Test
    void confirmCredentialsTest() throws IllegalArgumentException, AcctFrozenException{
        Admin admin = new Admin();
        ATM finalAtm= new ATM();

        //valid checking account
        admin.createCheckingForTeller("1234567890", "Bob Job", "dog123", 100);
        assertTrue(finalAtm.confirmCredentials("1234567890", "dog123"));

        //checking account valid ID invalid password
        assertFalse(finalAtm.confirmCredentials("1234567890", "frog"));

        //valid savings account
        admin.createSavingsForTeller("1111111111", "Bob Lob", "frog123", 100, .1, 800);
        assertTrue(finalAtm.confirmCredentials("1111111111", "frog123"));

        //savings account valid ID invalid password
        assertFalse(finalAtm.confirmCredentials("1111111111", "bob"));

        //account invalid ID valid password
        assertThrows(IllegalArgumentException.class, ()-> finalAtm.confirmCredentials("24235354", "dog123"));

        //account invalid ID and password
        assertThrows(IllegalArgumentException.class, ()-> finalAtm.confirmCredentials("24353535", "dog"));
    }

    @Test
    void checkBalanceTest() throws IllegalArgumentException, AcctFrozenException{
        Admin admin = new Admin();
        ATM finalAtm = new ATM();

        //check checkings balance
        admin.createCheckingForTeller("1234567890", "Bob Job", "dog123", 225.56);
        assertEquals(225.56, finalAtm.checkBalance("1234567890"));

        //check savings balance
        admin.createSavingsForTeller("1111111111", "Bob Lob", "frog123", 45.32, .1, 800);
        assertEquals(45.32, finalAtm.checkBalance("1111111111"));

        //check invalid account balance throws exception
        assertThrows(IllegalArgumentException.class, ()-> finalAtm.checkBalance("245422244"));

        //check frozen checking account
        admin.freezeAccount("1234567890");
        assertThrows(AcctFrozenException.class, ()-> finalAtm.checkBalance("1234567890"));

        //check checking balance after unfrozen
        admin.unfreezeAcct("1234567890");
        assertEquals(225.56, finalAtm.checkBalance("1234567890"));

        //check frozen savings account
        admin.freezeAccount("1111111111");
        assertThrows(AcctFrozenException.class, ()-> finalAtm.checkBalance("1111111111"));

        //check savings account balance after unfrozen
        admin.unfreezeAcct("1111111111");
        assertEquals(45.32, finalAtm.checkBalance("1111111111"));
    }

    @Test
    void withdrawTest() throws IllegalArgumentException, InsufficientFundsException, AcctFrozenException{
        Admin admin = new Admin();
        ATM finalAtm = new ATM();

        //valid checking acct positive withdrawal
        admin.createCheckingForTeller("1111111111", "Bob Jo", "dog223", 500);
        finalAtm.withdraw("1111111111", 200.75);
        assertEquals(299.25, finalAtm.checkBalance("1111111111"));

        //valid checking acct left with 0
        finalAtm.withdraw("1111111111", 299.25);
        assertEquals(0, finalAtm.checkBalance("1111111111"));

        //valid checking acct overdraw
        admin.createCheckingForTeller("2222222222", "Lob E", "dog456", 300);
        assertThrows(InsufficientFundsException.class, ()-> finalAtm.withdraw("2222222222", 301));

        //valid checking acct negative withdrawal
        assertThrows(IllegalArgumentException.class, ()-> finalAtm.withdraw("2222222222", -35));

        //valid checking acct three decimal points
        assertThrows(IllegalArgumentException.class, ()-> finalAtm.withdraw("2222222222", 211.532));

        //frozen checking acct
        admin.freezeAccount("2222222222");
        assertThrows(AcctFrozenException.class, ()-> finalAtm.withdraw("2222222222", 35));

        //checking acct after unfrozen
        admin.unfreezeAcct("2222222222");
        finalAtm.withdraw("2222222222", 40);
        assertEquals(260, finalAtm.checkBalance("2222222222"));

        //valid savings acct positive withdrawal
        admin.createSavingsForTeller("3333333333", "Rob Jo", "dog443", 300, .1, 600);
        finalAtm.withdraw("1111111111", 175);
        assertEquals(125, finalAtm.checkBalance("3333333333"));

        //valid savings acct left with 0
        finalAtm.withdraw("3333333333", 125);
        assertEquals(0, finalAtm.checkBalance("3333333333"));

        //valid savings acct overdraw
        admin.createSavingsForTeller("4444444444", "Goo Boo", "dog64", 1000, .2, 600);
        assertThrows(InsufficientFundsException.class, ()-> finalAtm.withdraw("4444444444", 1001));

        //valid savings acct negative withdrawal
        assertThrows(IllegalArgumentException.class, ()-> finalAtm.withdraw("4444444444", -35));

        //valid savings acct three decimal points
        assertThrows(IllegalArgumentException.class, ()-> finalAtm.withdraw("4444444444", 35.632));

        //frozen savings acct
        admin.freezeAccount("4444444444");
        assertThrows(AcctFrozenException.class, ()-> finalAtm.withdraw("4444444444", 50));

        //savings acct after unfrozen
        admin.unfreezeAcct("4444444444");
        finalAtm.withdraw("4444444444", 400);
        assertEquals(600, finalAtm.checkBalance("4444444444"));

        //savings acct over withdrawal max
        assertThrows(IllegalArgumentException.class, ()-> finalAtm.withdraw("4444444444", 400));

        //invalid acct
        assertThrows(IllegalArgumentException.class, ()-> finalAtm.withdraw("23535353", 250));
    }

    @Test
    void depositTest() throws IllegalArgumentException, AcctFrozenException{
        Admin admin = new Admin();
        ATM finalAtm = new ATM();

        //valid checking acct positive withdrawal
        admin.createCheckingForTeller("1111111111", "Bob Jo", "dog223", 500);
        finalAtm.deposit("1111111111", 200.75);
        assertEquals(299.25, finalAtm.checkBalance("1111111111"));

        //valid checking acct left with 0
        finalAtm.deposit("1111111111", 299.25);
        assertEquals(0, finalAtm.checkBalance("1111111111"));

        //valid checking acct negative withdrawal
        assertThrows(IllegalArgumentException.class, ()-> finalAtm.deposit("2222222222", -35));

        //valid checking acct three decimal points
        assertThrows(IllegalArgumentException.class, ()-> finalAtm.deposit("2222222222", 211.532));

        //frozen checking acct
        admin.freezeAccount("2222222222");
        assertThrows(AcctFrozenException.class, ()-> finalAtm.deposit("2222222222", 35));

        //checking acct after unfrozen
        admin.unfreezeAcct("2222222222");
        finalAtm.deposit("2222222222", 40);
        assertEquals(260, finalAtm.checkBalance("2222222222"));

        //valid savings acct positive withdrawal
        admin.createSavingsForTeller("3333333333", "Rob Jo", "dog443", 300, .1, 600);
        finalAtm.deposit("1111111111", 175);
        assertEquals(125, finalAtm.checkBalance("3333333333"));

        //valid savings acct left with 0
        finalAtm.deposit("3333333333", 125);
        assertEquals(0, finalAtm.checkBalance("3333333333"));

        //valid savings acct overdraw
        admin.createSavingsForTeller("4444444444", "Goo Boo", "dog64", 1000, .2, 600);
        assertThrows(InsufficientFundsException.class, ()-> finalAtm.deposit("4444444444", 1001));

        //valid savings acct negative withdrawal
        assertThrows(IllegalArgumentException.class, ()-> finalAtm.deposit("4444444444", -35));

        //valid savings acct three decimal points
        assertThrows(IllegalArgumentException.class, ()-> finalAtm.deposit("4444444444", 35.632));

        //frozen savings acct
        admin.freezeAccount("4444444444");
        assertThrows(AcctFrozenException.class, ()-> finalAtm.deposit("4444444444", 50));

        //savings acct after unfrozen
        admin.unfreezeAcct("4444444444");
        finalAtm.deposit("4444444444", 400);
        assertEquals(600, finalAtm.checkBalance("4444444444"));

        //savings acct over withdrawal max
        assertThrows(IllegalArgumentException.class, ()-> finalAtm.deposit("4444444444", 400));

        //invalid acct
        assertThrows(IllegalArgumentException.class, ()-> finalAtm.deposit("23535353", 250));
    }

    @Test
    void transferTest() throws IllegalArgumentException, InsufficientFundsException, AcctFrozenException{

    }

    @Test
    void transactionHistoryTest() throws IllegalArgumentException, AcctFrozenException{

    }
}
