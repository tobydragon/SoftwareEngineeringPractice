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

        //valid checking acct positive deposit
        admin.createCheckingForTeller("1111111111", "Bob Jo", "dog223", 500);
        finalAtm.deposit("1111111111", 200.75);
        assertEquals(299.25, finalAtm.checkBalance("1111111111"));

        //valid checking acct left with 0
        finalAtm.deposit("1111111111", 299.25);
        assertEquals(0, finalAtm.checkBalance("1111111111"));

        //valid checking acct negative deposit
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

        //valid savings acct positive deposit
        admin.createSavingsForTeller("3333333333", "Rob Jo", "dog443", 300, .1, 600);
        finalAtm.deposit("1111111111", 175);
        assertEquals(125, finalAtm.checkBalance("3333333333"));

        //valid savings acct left with 0
        finalAtm.deposit("3333333333", 125);
        assertEquals(0, finalAtm.checkBalance("3333333333"));

        //valid savings acct overdraw
        admin.createSavingsForTeller("4444444444", "Goo Boo", "dog64", 1000, .2, 600);
        assertThrows(InsufficientFundsException.class, ()-> finalAtm.deposit("4444444444", 1001));

        //valid savings acct negative deposit
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
        Admin admin = new Admin();
        ATM finalAtm = new ATM();

        //valid checking acct to savings acct
        admin.createCheckingForTeller("1111111111", "Joe Bob", "dog244", 400);
        admin.createSavingsForTeller("2222222222", "Billy B", "3effe", 200, .2, 500);
        finalAtm.transfer("1111111111", "2222222222", 200);
        assertEquals(200, finalAtm.checkBalance("1111111111"));
        assertEquals(400, finalAtm.checkBalance("2222222222"));

        //valid checking acct to savings acct left with 0
        finalAtm.transfer("1111111111", "2222222222", 200);
        assertEquals(0, finalAtm.checkBalance("1111111111"));
        assertEquals(600, finalAtm.checkBalance("2222222222"));

        //valid checking acct to savings acct overdraw
        admin.createCheckingForTeller("3333333333", "Bo J", "3rf2d", 225);
        admin.createSavingsForTeller("4444444444", "Robby D", "tefer3r", 300, .1, 600);
        assertThrows(InsufficientFundsException.class, ()-> finalAtm.transfer("3333333333", "4444444444", 226));

        //valid checking acct to savings acct negative value
        assertThrows(IllegalArgumentException.class, ()-> finalAtm.transfer("3333333333", "4444444444", -100));

        //valid checking acct to savings acct three decimal points
        assertThrows(IllegalArgumentException.class, ()-> finalAtm.transfer("3333333333", "4444444444", 50.353));

        //frozen checking acct to savings acct
        admin.freezeAccount("3333333333");
        assertThrows(AcctFrozenException.class, ()-> finalAtm.transfer("3333333333", "4444444444", 200));

        //frozen checking acct to frozen savings acct
        admin.freezeAccount("4444444444");
        assertThrows(AcctFrozenException.class, ()-> finalAtm.transfer("3333333333", "4444444444", 100.53));

        //checking acct to frozen savings acct
        admin.unfreezeAcct("3333333333");
        assertThrows(AcctFrozenException.class, ()-> finalAtm.transfer("3333333333", "4444444444", 100.53));

        //checking acct after unfrozen to savings acct after unfrozen
        admin.unfreezeAcct("4444444444");
        finalAtm.transfer("3333333333", "4444444444", 50);
        assertEquals(175, finalAtm.checkBalance("3333333333"));
        assertEquals(350, finalAtm.checkBalance("4444444444"));

        //valid checking acct to invalid acct
        assertThrows(IllegalArgumentException.class, ()-> finalAtm.transfer("3333333333", "353535", 15));

        //valid savings acct to checking acct positive value
        admin.createSavingsForTeller("5555555555", "Jon Locke", "3effe", 500, .2, 800);
        admin.createCheckingForTeller("6666666666", "Bo Li" , "dog244", 400);
        finalAtm.transfer("5555555555", "6666666666", 100.25);
        assertEquals(399.75, finalAtm.checkBalance("5555555555"));
        assertEquals(500.25, finalAtm.checkBalance("6666666666"));

        //valid savings acct to checking acct left with 0
        finalAtm.transfer("5555555555", "6666666666", 399.75);
        assertEquals(0, finalAtm.checkBalance("5555555555"));
        assertEquals(900, finalAtm.checkBalance("6666666666"));

        //valid savings acct to checking acct overdraw
        admin.createSavingsForTeller("7777777777", "Gob Holly", "4tef3r", 800, .2, 500);
        admin.createCheckingForTeller("8888888888", "Bob Dono", "44gg4g4", 300);
        assertThrows(InsufficientFundsException.class, ()-> finalAtm.transfer("7777777777", "8888888888", 801));

        //valid savings acct to checking acct negative value
        assertThrows(IllegalArgumentException.class, ()-> finalAtm.transfer("7777777777", "8888888888", -25));

        //valid savings acct to checking acct three decimal points
        assertThrows(IllegalArgumentException.class, ()-> finalAtm.transfer("7777777777", "8888888888", 78.321));

        //frozen savings acct to checking acct
        admin.freezeAccount("7777777777");
        assertThrows(AcctFrozenException.class, ()-> finalAtm.transfer("7777777777", "8888888888", 100));

        //frozen savings acct to frozen checking acct
        admin.freezeAccount("8888888888");
        assertThrows(AcctFrozenException.class, ()-> finalAtm.transfer("777777777", "8888888888", 50));

        //savings acct to frozen checking acct
        admin.unfreezeAcct("7777777777");
        assertThrows(AcctFrozenException.class, ()-> finalAtm.transfer("777777777", "8888888888", 100));

        //savings acct after unfrozen to checking acct after unfrozen
        admin.unfreezeAcct("8888888888");
        finalAtm.transfer("7777777777", "8888888888", 400);
        assertEquals(400, finalAtm.checkBalance("7777777777"));
        assertEquals(700, finalAtm.checkBalance("8888888888"));

        //savings acct over withdrawal limit
        assertThrows(IllegalArgumentException.class, ()-> finalAtm.transfer("7777777777", "8888888888", 101));

        //valid savings acct to invalid acct
        assertThrows(IllegalArgumentException.class, ()-> finalAtm.transfer("7777777777", "35353535", 50));

        //invalid acct to invalid acct
        assertThrows(IllegalArgumentException.class, ()-> finalAtm.transfer("355363636", "35333636", 50));
    }

    @Test
    void transactionHistoryTest() throws InsufficientFundsException, IllegalArgumentException, AcctFrozenException{
        Admin admin = new Admin();
        ATM finalAtm = new ATM();

        //valid checking acct after 1 withdraw
        admin.createCheckingForTeller("1111111111", "Greg Leg", "3r3f3f", 400);
        finalAtm.withdraw("1111111111", 100.25);
        assertEquals("withdraw of 100.25", finalAtm.transactionHistory("1111111111"));

        //valid checking acct after 1 deposit
        admin.createCheckingForTeller("2222222222", "Sam Bammo", "rgeg322", 100);
        finalAtm.deposit("2222222222", 300);
        assertEquals("deposit of 300.0", finalAtm.transactionHistory("2222222222"));

        //valid checking acct after 1 deposit then 1 withdraw
        finalAtm.withdraw("2222222222", 100.52);
        assertEquals("deposit of 300.0; withdraw of 100.52", finalAtm.transactionHistory("2222222222"));

        //valid checking acct after 1 withdraw then 1 deposit
        admin.createCheckingForTeller("3333333333", "Sally Skebab", "kfjell2k", 300);
        finalAtm.withdraw("3333333333", 100);
        finalAtm.deposit("3333333333", 200);
        assertEquals("withdraw of 100.0; deposit of 200.0", finalAtm.transactionHistory("3333333333"));

        //valid checking acct after 2 withdraws then 2 deposits
        admin.createCheckingForTeller("4444444444", "Arthur Lang", "ljfn3lk", 500);
        finalAtm.withdraw("4444444444", 100);
        finalAtm.withdraw("4444444444", 300.25);
        finalAtm.deposit("4444444444", 235.61);
        finalAtm.deposit("4444444444", 56.29);
        assertEquals("withdraw of 100.0; withdraw of 300.25; deposit of 235.61; deposit of 56.29", finalAtm.transactionHistory("4444444444"));

        //frozen checking acct
        admin.freezeAccount("4444444444");
        assertThrows(AcctFrozenException.class, ()-> finalAtm.transactionHistory("4444444444"));

        //checking acct after unfrozen
        admin.unfreezeAcct("4444444444");
        finalAtm.deposit("4444444444", 225);
        assertEquals("withdraw of 100.0; withdraw of 300.25; deposit of 235.61; deposit of 56.29; deposit of 225", finalAtm.transactionHistory("4444444444"));

        //check transfer shows up in checking acct out and checking acct in
        admin.createCheckingForTeller("5555555555", "Lucy Trolleyman", "3t3tfef", 200);
        admin.createCheckingForTeller("6666666666", "Sandra Qualdrick", "r3foihf2", 400);
        finalAtm.transfer("5555555555", "6666666666", 150);
        assertEquals("transfer of 150.0 to 6666666666", finalAtm.transactionHistory("5555555555"));
        assertEquals("transfer of 150.0 from 5555555555", finalAtm.transactionHistory("6666666666"));

        //valid checking acct after a transfer out then deposit then transfer in
        finalAtm.deposit("5555555555", 50);
        finalAtm.transfer("6666666666", "5555555555",  25.43);
        assertEquals("transfer of 150.0 to 6666666666; deposit of 50; transfer of 25.43 from 5555555555", finalAtm.transactionHistory("5555555555"));

        //valid savings acct after 1 withdraw
        admin.createSavingsForTeller("7777777777", "Paul Giamatti", "3f3f33f", 400, 0.1, 800);
        finalAtm.withdraw("7777777777", 50.64);
        assertEquals("withdraw of 50.64", finalAtm.transactionHistory("7777777777"));

        //valid savings acct after 1 deposit
        admin.createSavingsForTeller("8888888888", "Sara Palooza", "eflkn33", 2000, 0.1, 500);
        finalAtm.deposit("8888888888", 125);
        assertEquals("deposit of 125.0", finalAtm.transactionHistory("8888888888"));

        //valid savings acct after 1 deposit then 1 withdraw
        finalAtm.withdraw("8888888888", 56.32);
        assertEquals("deposit of 125.0; withdraw of 56.32", finalAtm.transactionHistory("8888888888"));

        //valid savings acct after 1 withdraw then 1 deposit
        finalAtm.deposit("7777777777", 145.13);
        assertEquals("withdraw of 50.64; deposit of 145.13", finalAtm.transactionHistory("7777777777"));

        //frozen savings acct
        admin.freezeAccount("8888888888");
        assertThrows(AcctFrozenException.class, ()-> finalAtm.transactionHistory("8888888888"));

        //savings acct after unfrozen
        admin.unfreezeAcct("8888888888");
        finalAtm.deposit("8888888888", 90.11);
        assertEquals("deposit of 125.0; withdraw of 56.32; deposit of 90.11", finalAtm.transactionHistory("8888888888"));

        //check transfer shows up after savings acct out and savings acct in
        admin.createSavingsForTeller("9999999999", "Natalie Price", "feij302", 600, .1, 900);
        admin.createSavingsForTeller("0000000000", "Riley Ranger", "0t3082", 400, .2, 1000);
        finalAtm.transfer("9999999999", "0000000000", 120.65);
        assertEquals("transfer of 120.65 to 0000000000", finalAtm.transactionHistory("9999999999"));
        assertEquals("transfer of 120.65 from 9999999999", finalAtm.transactionHistory("0000000000"));

        //valid savings acct after transfer out then deposit then transfer in
        finalAtm.deposit("9999999999", 50.65);
        finalAtm.transfer("0000000000", "9999999999", 20);
        assertEquals("transfer of 120.65 to 0000000000; deposit of 50.65; transfer of 20.0 from 0000000000", finalAtm.transactionHistory("9999999999"));

        //invalid acct
        assertThrows(IllegalArgumentException.class, ()-> finalAtm.transactionHistory("336366336"));
    }
}
