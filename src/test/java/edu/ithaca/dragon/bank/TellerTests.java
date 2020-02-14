package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TellerTests {
    @Test
    void confirmCredentialsTest() throws IllegalArgumentException, AcctFrozenException{
        Teller finalTeller = new Teller();

        //valid checking account
        finalTeller.admin.createCheckingForTeller("1234567890", "Bob Job", "dog123", 100);
        assertTrue(finalTeller.confirmCredentials("1234567890", "dog123"));

        //checking account valid ID invalid password
        assertFalse(finalTeller.confirmCredentials("1234567890", "frog"));

        //valid savings account
        finalTeller.admin.createSavingsForTeller("1111111111", "Bob Lob", "frog123", 100, .1, 800);
        assertTrue(finalTeller.confirmCredentials("1111111111", "frog123"));

        //savings account valid ID invalid password
        assertFalse(finalTeller.confirmCredentials("1111111111", "bob"));

        //account invalid ID valid password
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.confirmCredentials("24235354", "dog123"));

        //account invalid ID and password
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.confirmCredentials("24353535", "dog"));
    }

    @Test
    void checkBalanceTest() throws IllegalArgumentException, AcctFrozenException{

        Teller finalTeller = new Teller();

        //check checkings balance
        finalTeller.admin.createCheckingForTeller("1234567890", "Bob Job", "dog123", 225.56);
        assertEquals(225.56, finalTeller.checkBalance("1234567890","dog123"));

        //check savings balance
        finalTeller.admin.createSavingsForTeller("1111111111", "Bob Lob", "frog123", 45.32, .1, 800);
        assertEquals(45.32, finalTeller.checkBalance("1111111111", "frog123"));

        //check invalid account balance throws exception
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.checkBalance("245422244", "frog123"));

        //check frozen checking account
        finalTeller.admin.freezeAccount("1234567890");
        assertThrows(AcctFrozenException.class, ()-> finalTeller.checkBalance("1234567890", "dog123"));

        //check checking balance after unfrozen
        finalTeller.admin.unfreezeAcct("1234567890");
        assertEquals(225.56, finalTeller.checkBalance("1234567890", "dog123"));

        //check frozen savings account
        finalTeller.admin.freezeAccount("1111111111");
        assertThrows(AcctFrozenException.class, ()-> finalTeller.checkBalance("1111111111", "frog123"));

        //check savings account balance after unfrozen
        finalTeller.admin.unfreezeAcct("1111111111");
        assertEquals(45.32, finalTeller.checkBalance("1111111111", "frog123"));
    }

    @Test
    void withdrawTest() throws IllegalArgumentException, InsufficientFundsException, AcctFrozenException{

        Teller finalTeller = new Teller();

        //valid checking acct positive withdrawal
        finalTeller.admin.createCheckingForTeller("1111111111", "Bob Jo", "dog223", 500);
        finalTeller.withdraw("1111111111", "dog223",200.75);
        assertEquals(299.25, finalTeller.checkBalance("1111111111","dog223"));

        //valid checking acct left with 0
        finalTeller.withdraw("1111111111", "dog223",299.25);
        assertEquals(0, finalTeller.checkBalance("1111111111","dog223"));

        //valid checking acct overdraw
        finalTeller.admin.createCheckingForTeller("2222222222", "Lob E", "dog456", 300);
        assertThrows(InsufficientFundsException.class, ()-> finalTeller.withdraw("2222222222", "dog456",301));

        //valid checking acct negative withdrawal
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.withdraw("2222222222", "dog456",-35));

        //valid checking acct three decimal points
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.withdraw("2222222222", "dog456", 211.532));

        //frozen checking acct
        finalTeller.admin.freezeAccount("2222222222");
        assertThrows(AcctFrozenException.class, ()-> finalTeller.withdraw("2222222222", "dog456",35));

        //checking acct after unfrozen
        finalTeller.admin.unfreezeAcct("2222222222");
        finalTeller.withdraw("2222222222", "dog456",40);
        assertEquals(260, finalTeller.checkBalance("2222222222", "dog456"));

        //valid savings acct positive withdrawal
        finalTeller.admin.createSavingsForTeller("3333333333", "Rob Jo", "dog443", 300, .1, 600);
        finalTeller.withdraw("3333333333", "dog443",175);
        assertEquals(125, finalTeller.checkBalance("3333333333","dog443"));

        //valid savings acct left with 0
        finalTeller.withdraw("3333333333", "dog443",125);
        assertEquals(0, finalTeller.checkBalance("3333333333", "dog443"));

        //valid savings acct overdraw
        finalTeller.admin.createSavingsForTeller("4444444444", "Goo Boo", "dog64", 1000, .2, 600);
        assertThrows(InsufficientFundsException.class, ()-> finalTeller.withdraw("4444444444", "dog64",1001));

        //valid savings acct negative withdrawal
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.withdraw("4444444444", "dog64",-35));

        //valid savings acct three decimal points
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.withdraw("4444444444","dog64", 35.632));

        //frozen savings acct
        finalTeller.admin.freezeAccount("4444444444");
        assertThrows(AcctFrozenException.class, ()-> finalTeller.withdraw("4444444444", "dog64",50));

        //savings acct after unfrozen
        finalTeller.admin.unfreezeAcct("4444444444");
        finalTeller.withdraw("4444444444", "dog64",400);
        assertEquals(600, finalTeller.checkBalance("4444444444","dog64"));


        //invalid acct
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.withdraw("23535353", "dog443", 250));
    }

    @Test
    void depositTest() throws IllegalArgumentException, AcctFrozenException{

        Teller finalTeller = new Teller();

        //valid checking acct positive deposit
        finalTeller.admin.createCheckingForTeller("1111111111", "Bob Jo", "dog223", 500);
        finalTeller.admin.createCheckingForTeller("2222222222", "Bill Ji", "dog456", 200);
        finalTeller.deposit("1111111111", "dog223",200.75);
        finalTeller.deposit("2222222222", "dog456",20);
        assertEquals(700.75, finalTeller.checkBalance("1111111111","dog223"));
        assertEquals(220, finalTeller.checkBalance("2222222222","dog456"));

        //valid checking acct negative deposit
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.deposit("2222222222", "dog456",-35));

        //valid checking acct three decimal points
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.deposit("2222222222", "dog456",211.532));

        //frozen checking acct
        finalTeller.admin.freezeAccount("2222222222");
        assertThrows(AcctFrozenException.class, ()-> finalTeller.deposit("2222222222", "dog456",35));

        //checking acct after unfrozen
        finalTeller.admin.unfreezeAcct("2222222222");
        finalTeller.deposit("2222222222", "dog456",40);
        assertEquals(260, finalTeller.checkBalance("2222222222", "dog456"));

        //valid savings acct positive deposit
        finalTeller.admin.createSavingsForTeller("3333333333", "Rob Jo", "dog443", 300, .1, 600);
        finalTeller.deposit("3333333333", "dog443", 175);
        assertEquals(475, finalTeller.checkBalance("3333333333","dog443"));
        finalTeller.admin.createSavingsForTeller("4444444444", "Rob Oj", "dog64", 25, .1, 600);
        finalTeller.deposit("4444444444", "dog64", 175);
        assertEquals(200, finalTeller.checkBalance("4444444444","dog64"));

        //valid savings acct negative deposit
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.deposit("4444444444", "dog64",-35));

        //valid savings acct three decimal points
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.deposit("4444444444", "dog64",35.632));

        //frozen savings acct
        finalTeller.admin.freezeAccount("4444444444");
        assertThrows(AcctFrozenException.class, ()-> finalTeller.deposit("4444444444", "dog64",50));

        //savings acct after unfrozen
        finalTeller.admin.unfreezeAcct("4444444444");
        finalTeller.deposit("4444444444", "dog64",400);
        assertEquals(600, finalTeller.checkBalance("4444444444","dog64"));

        //invalid acct
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.deposit("23535353", "dog64",250));
    }

    @Test
    void transferTest() throws IllegalArgumentException, InsufficientFundsException, AcctFrozenException{

        Teller finalTeller = new Teller();

        //valid checking acct to savings acct
        finalTeller.admin.createCheckingForTeller("1111111111", "Joe Bob", "dog244", 400);
        finalTeller.admin.createSavingsForTeller("2222222222", "Billy B", "3effe", 200, .2, 500);
        finalTeller.transfer("1111111111", "2222222222", "dog244", "3effe",200);
        assertEquals(200, finalTeller.checkBalance("1111111111", "dog244"));
        assertEquals(400, finalTeller.checkBalance("2222222222", "3effe"));

        //valid checking acct to savings acct left with 0
        finalTeller.transfer("1111111111", "2222222222", "dog244", "3effe",200);
        assertEquals(0, finalTeller.checkBalance("1111111111", "dog244"));
        assertEquals(600, finalTeller.checkBalance("2222222222", "3effe"));

        //valid checking acct to savings acct overdraw
        finalTeller.admin.createCheckingForTeller("3333333333", "Bo J", "3rf2d", 225);
        finalTeller.admin.createSavingsForTeller("4444444444", "Robby D", "tefer3r", 300, .1, 600);
        assertThrows(InsufficientFundsException.class, ()-> finalTeller.transfer("3333333333", "4444444444", "3rf2d", "tefer3r",226));

        //valid checking acct to savings acct negative value
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.transfer("3333333333", "4444444444", "3rf2d", "tefer3r",-100));

        //valid checking acct to savings acct three decimal points
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.transfer("3333333333", "4444444444", "3rf2d","tefer3r",50.353));

        //frozen checking acct to savings acct
        finalTeller.admin.freezeAccount("3333333333");
        assertThrows(AcctFrozenException.class, ()-> finalTeller.transfer("3333333333", "4444444444", "3rf2d","tefer3r",200));

        //frozen checking acct to frozen savings acct
        finalTeller.admin.freezeAccount("4444444444");
        assertThrows(AcctFrozenException.class, ()-> finalTeller.transfer("3333333333", "4444444444", "3rf2d","tefer3r",100.53));

        //checking acct to frozen savings acct
        finalTeller.admin.unfreezeAcct("3333333333");
        assertThrows(AcctFrozenException.class, ()-> finalTeller.transfer("3333333333", "4444444444", "3rf2d","tefer3r",100.53));

        //checking acct after unfrozen to savings acct after unfrozen
        finalTeller.admin.unfreezeAcct("4444444444");
        finalTeller.transfer("3333333333", "4444444444", "3rf2d","tefer3r",50);
        assertEquals(175, finalTeller.checkBalance("3333333333","3rf2d"));
        assertEquals(350, finalTeller.checkBalance("4444444444", "tefer3r"));

        //valid checking acct to invalid acct
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.transfer("3333333333", "353535", "3rf2d","3rf2d",15));

        //valid savings acct to checking acct positive value
        finalTeller.admin.createSavingsForTeller("5555555555", "Jon Locke", "3effe", 500, .2, 800);
        finalTeller.admin.createCheckingForTeller("6666666666", "Bo Li" , "dog244", 400);
        finalTeller.transfer("5555555555", "6666666666", "3effe","dog244",100.25);
        assertEquals(399.75, finalTeller.checkBalance("5555555555","3effe"));
        assertEquals(500.25, finalTeller.checkBalance("6666666666","dog244"));

        //valid savings acct to checking acct left with 0
        finalTeller.transfer("5555555555", "6666666666", "3effe","dog244",399.75);
        assertEquals(0, finalTeller.checkBalance("5555555555","3effe"));
        assertEquals(900, finalTeller.checkBalance("6666666666","dog244"));

        //valid savings acct to checking acct overdraw
        finalTeller.admin.createSavingsForTeller("7777777777", "Gob Holly", "4tef3r", 800, .2, 500);
        finalTeller.admin.createCheckingForTeller("8888888888", "Bob Dono", "44gg4g4", 300);
        assertThrows(InsufficientFundsException.class, ()-> finalTeller.transfer("7777777777", "8888888888", "4tef3r","44gg4g4",801));

        //valid savings acct to checking acct negative value
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.transfer("7777777777", "8888888888", "4tef3r","44gg4g4",-25));

        //valid savings acct to checking acct three decimal points
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.transfer("7777777777", "8888888888", "4tef3r","44gg4g4",78.321));

        //frozen savings acct to checking acct
        finalTeller.admin.freezeAccount("7777777777");
        assertThrows(AcctFrozenException.class, ()-> finalTeller.transfer("7777777777", "8888888888", "4tef3r","44gg4g4",100));

        //frozen savings acct to frozen checking acct
        finalTeller.admin.freezeAccount("8888888888");
        assertThrows(AcctFrozenException.class, ()-> finalTeller.transfer("7777777777", "8888888888", "4tef3r","44gg4g4",50));

        //savings acct to frozen checking acct
        finalTeller.admin.unfreezeAcct("7777777777");
        assertThrows(AcctFrozenException.class, ()-> finalTeller.transfer("7777777777", "8888888888", "4tef3r","44gg4g4",100));

        //savings acct after unfrozen to checking acct after unfrozen
        finalTeller.admin.unfreezeAcct("8888888888");
        finalTeller.transfer("7777777777", "8888888888", "4tef3r","44gg4g4",400);
        assertEquals(400, finalTeller.checkBalance("7777777777","4tef3r"));
        assertEquals(700, finalTeller.checkBalance("8888888888","44gg4g4"));

        //valid savings acct to invalid acct
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.transfer("7777777777", "35353535", "4tef3r","44gg4g4",50));

        //invalid acct to invalid acct
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.transfer("355363636", "35333636", "4tef3r","4tef3r",50));
    }

    @Test
    void transactionHistoryTest() throws InsufficientFundsException, IllegalArgumentException, AcctFrozenException{

        Teller finalTeller = new Teller();

        //valid checking acct after 1 withdraw
        finalTeller.admin.createCheckingForTeller("1111111111", "Greg Leg", "3r3f3f", 400);
        finalTeller.withdraw("1111111111", "3r3f3f",100.25);
        assertEquals("withdrawal of 100.25", finalTeller.transactionHistory("1111111111","3r3f3f"));

        //valid checking acct after 1 deposit
        finalTeller.admin.createCheckingForTeller("2222222222", "Sam Bammo", "rgeg322", 100);
        finalTeller.deposit("2222222222", "rgeg322",300);
        assertEquals("deposit of 300.0", finalTeller.transactionHistory("2222222222","rgeg322"));

        //valid checking acct after 1 deposit then 1 withdraw
        finalTeller.withdraw("2222222222", "rgeg322",100.52);
        assertEquals("deposit of 300.0; withdrawal of 100.52", finalTeller.transactionHistory("2222222222","rgeg322"));

        //valid checking acct after 1 withdraw then 1 deposit
        finalTeller.admin.createCheckingForTeller("3333333333", "Sally Skebab", "kfjell2k", 300);
        finalTeller.withdraw("3333333333", "kfjell2k",100);
        finalTeller.deposit("3333333333", "kfjell2k",200);
        assertEquals("withdrawal of 100.0; deposit of 200.0", finalTeller.transactionHistory("3333333333","kfjell2k"));

        //valid checking acct after 2 withdraws then 2 deposits
        finalTeller.admin.createCheckingForTeller("4444444444", "Arthur Lang", "ljfn3lk", 500);
        finalTeller.withdraw("4444444444","ljfn3lk", 100);
        finalTeller.withdraw("4444444444", "ljfn3lk",300.25);
        finalTeller.deposit("4444444444", "ljfn3lk",235.61);
        finalTeller.deposit("4444444444", "ljfn3lk",56.29);
        assertEquals("withdrawal of 100.0; withdrawal of 300.25; deposit of 235.61; deposit of 56.29", finalTeller.transactionHistory("4444444444","ljfn3lk"));

        //frozen checking acct
        finalTeller.admin.freezeAccount("4444444444");
        assertThrows(AcctFrozenException.class, ()-> finalTeller.transactionHistory("4444444444","ljfn3lk"));

        //checking acct after unfrozen
        finalTeller.admin.unfreezeAcct("4444444444");
        finalTeller.deposit("4444444444", "ljfn3lk",225);
        assertEquals("withdrawal of 100.0; withdrawal of 300.25; deposit of 235.61; deposit of 56.29; deposit of 225.0", finalTeller.transactionHistory("4444444444","ljfn3lk"));

        //check transfer shows up in checking acct out and checking acct in
        finalTeller.admin.createCheckingForTeller("5555555555", "Lucy Trolleyman", "3t3tfef", 200);
        finalTeller.admin.createCheckingForTeller("6666666666", "Sandra Qualdrick", "r3foihf2", 400);
        finalTeller.transfer("5555555555", "6666666666", "3t3tfef","r3foihf2",150);
        assertEquals("withdrawal of 150.0", finalTeller.transactionHistory("5555555555","3t3tfef"));
        assertEquals("deposit of 150.0", finalTeller.transactionHistory("6666666666","r3foihf2"));

        //valid checking acct after a transfer out then deposit then transfer in
        finalTeller.deposit("5555555555", "3t3tfef",50);
        finalTeller.transfer("6666666666", "5555555555",  "r3foihf2","3t3tfef",25.43);
        assertEquals("withdrawal of 150.0; deposit of 50.0; deposit of 25.43", finalTeller.transactionHistory("5555555555","3t3tfef"));

        //valid savings acct after 1 withdraw
        finalTeller.admin.createSavingsForTeller("7777777777", "Paul Giamatti", "3f3f33f", 400, 0.1, 800);
        finalTeller.withdraw("7777777777", "3f3f33f",50.64);
        assertEquals("withdrawal of 50.64", finalTeller.transactionHistory("7777777777","3f3f33f"));

        //valid savings acct after 1 deposit
        finalTeller.admin.createSavingsForTeller("8888888888", "Sara Palooza", "eflkn33", 2000, 0.1, 500);
        finalTeller.deposit("8888888888", "eflkn33",125);
        assertEquals("deposit of 125.0", finalTeller.transactionHistory("8888888888","eflkn33"));

        //valid savings acct after 1 deposit then 1 withdraw
        finalTeller.withdraw("8888888888", "eflkn33",56.32);
        assertEquals("deposit of 125.0; withdrawal of 56.32", finalTeller.transactionHistory("8888888888","eflkn33"));

        //valid savings acct after 1 withdraw then 1 deposit
        finalTeller.deposit("7777777777", "3f3f33f",145.13);
        assertEquals("withdrawal of 50.64; deposit of 145.13", finalTeller.transactionHistory("7777777777","3f3f33f"));

        //frozen savings acct
        finalTeller.admin.freezeAccount("8888888888");
        assertThrows(AcctFrozenException.class, ()-> finalTeller.transactionHistory("8888888888","eflkn33"));

        //savings acct after unfrozen
        finalTeller.admin.unfreezeAcct("8888888888");
        finalTeller.deposit("8888888888","eflkn33", 90.11);
        assertEquals("deposit of 125.0; withdrawal of 56.32; deposit of 90.11", finalTeller.transactionHistory("8888888888","eflkn33"));

        //check transfer shows up after savings acct out and savings acct in
        finalTeller.admin.createSavingsForTeller("9999999999", "Natalie Price", "feij302", 600, .1, 900);
        finalTeller.admin.createSavingsForTeller("0000000000", "Riley Ranger", "0t3082", 400, .2, 1000);
        finalTeller.transfer("9999999999", "0000000000", "feij302","0t3082",120.65);
        assertEquals("withdrawal of 120.65", finalTeller.transactionHistory("9999999999","feij302"));
        assertEquals("deposit of 120.65", finalTeller.transactionHistory("0000000000","0t3082"));

        //valid savings acct after transfer out then deposit then transfer in
        finalTeller.deposit("9999999999","feij302", 50.65);
        finalTeller.transfer("0000000000", "9999999999", "0t3082","feij302",20);
        assertEquals("withdrawal of 120.65; deposit of 50.65; deposit of 20.0", finalTeller.transactionHistory("9999999999","feij302"));

        //invalid acct
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.transactionHistory("336366336","feij302"));
    }

    @Test
    void createCheckingAccountTests(){
        Teller finalTeller = new Teller();

        //invalid balance
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.createAccount("1234567890", "Mike Santo", "fd6aa", -50));
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.createAccount("1234567890", "Mike Santo", "fd6aa", 50.003));
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.createAccount("1234567890", "Mike Santo", "fd6aa", 50.0034382978923));

        //invalid ID
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.createAccount("123456789", "Mike Santo", "fd6aa", 50));

        //invalid name
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.createAccount("1234567890", "Mikeanto", "fd6aa", 50));

        //valid info
        finalTeller.createAccount("1234567890", "Mike Santo", "ewqh99", 500);
        assertEquals("1234567890", finalTeller.admin.getAccount("1234567890").getAcctId());

        //account already exists
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.createAccount("1234567890", "Harold Johnson", "dfh293", 50));
    }

    @Test
    void createSavingsAccountTests(){
        Teller finalTeller = new Teller();

        //invalid balance
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.createAccount("1234567890", "Mike Santo", "fd6aa", -50, 2, 500));
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.createAccount("1234567890", "Mike Santo", "fd6aa", 50.003, 2, 500));
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.createAccount("1234567890", "Mike Santo", "fd6aa", 50.0034382978923, 2, 500));

        //invalid ID
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.createAccount("123456789", "Mike Santo", "fd6aa", 50, 2, 500));

        //invalid name
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.createAccount("1234567890", "Mikeanto", "fd6aa", 50, 2, 500));

        //0 interest rate
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.createAccount("1234567890", "Mike Santo", "fd6aa", 50, 0, 500));

        //negative interest rate
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.createAccount("1234567890", "Mike Santo", "fd6aa", 50, -5, 500));

        //0 max withdraw
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.createAccount("1234567890", "Mike Santo", "fd6aa", 50, 4, 0));

        //negative max withdraw
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.createAccount("1234567890", "Mike Santo", "fd6aa", 50, 4, -500));

        //valid info
        finalTeller.createAccount("1234567890", "Mike Santo", "db8732", 50, 4, 500);
        assertEquals("1234567890", finalTeller.admin.getAccount("1234567890").getAcctId());

        //account already exists
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.createAccount("1234567890", "Harold Johnson", "dfh293", 50, 2, 50));

    }

    @Test
    void closeAccountTests() throws AcctFrozenException {
        Teller finalTeller = new Teller();
        finalTeller.createAccount("1234567890", "Mike Santo", "db8732", 50, 4, 500);
        finalTeller.createAccount("0987654321", "Evan Santo", "ewqh99", 500);

        //account doesn't exist
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.closeAccount("5364783746"));

        //account is frozen
        Account checking = finalTeller.admin.getAccount("1234567890");
        checking.setFrozen(true);
        assertThrows(AcctFrozenException.class, ()-> finalTeller.closeAccount("1234567890"));
        Account savings = finalTeller.admin.getAccount("0987654321");
        savings.setFrozen(true);
        assertThrows(AcctFrozenException.class, ()-> finalTeller.closeAccount("0987654321"));

        checking.setFrozen(false);
        savings.setFrozen(false);

        //close checking
        finalTeller.closeAccount("1234567890");
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.admin.getAccount("1234567890"));

        //close savings
        finalTeller.closeAccount("0987654321");
        assertThrows(IllegalArgumentException.class, ()-> finalTeller.admin.getAccount("0987654321"));


    }


}
