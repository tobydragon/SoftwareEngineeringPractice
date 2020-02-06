package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CentralBankTest {

    @Test
    void depositTest(){
        //WILL BE DONE FORE REAL SOMEDAY
    }

    @Test
    void accountExistsTest() throws AccountAlreadyExistsException, IllegalArgumentException{
        CentralBank bank = new CentralBank();
        bank.createAccount("yes@yes.com", 0);
        assertTrue(bank.accountExists("yes@yes.com"));
        assertFalse(bank.accountExists("nope@nope.com"));
    }

    @Test
    void createAccountTest() throws AccountAlreadyExistsException, IllegalArgumentException {

        CentralBank bank = new CentralBank();

        //equivalence classes

        //account created
        //good id and start balance
        String id1 = "a@b.com";
        bank.createAccount(id1, 0);
        assertTrue(bank.accountExists(id1));
        assertEquals(0, bank.checkBalance(id1));

        String id2 = "b@c.com";
        bank.createAccount(id2, 100.5);
        assertTrue(bank.accountExists(id2));
        assertEquals(100.5, bank.checkBalance(id2));

        //this project does not allow dashes in the domain
        //String id3 = "c.long.email@d-long-email.com";
        String id3 = "test.test.test@email.com";
        bank.createAccount(id3, 100000.86);
        assertTrue(bank.accountExists(id3));
        assertEquals(100000.86, bank.checkBalance(id3));


        //account not created
        //invalid id/email
        assertThrows(IllegalArgumentException.class, ()-> bank.createAccount("#bad", 100));
        assertThrows(IllegalArgumentException.class, ()-> bank.createAccount("bad..email@bad-.com", 100));
        assertThrows(IllegalArgumentException.class, ()-> bank.createAccount("bad@bad.c", 100));

        //id already exists
        assertThrows(AccountAlreadyExistsException.class, ()-> bank.createAccount(id1, 100));
        assertThrows(AccountAlreadyExistsException.class, ()-> bank.createAccount(id2, 100));
        assertThrows(AccountAlreadyExistsException.class, ()-> bank.createAccount(id3, 100));

        //invalid start balance
        assertThrows(IllegalArgumentException.class, ()-> bank.createAccount("c@d.com", -0.01));
        assertThrows(IllegalArgumentException.class, ()-> bank.createAccount("d@e.com", 100.999));
        assertThrows(IllegalArgumentException.class, ()-> bank.createAccount("e@f.com", -5.055));

        //invalid id and start balance
        assertThrows(AccountAlreadyExistsException.class, ()-> bank.createAccount(id1, -0.01));
        assertThrows(AccountAlreadyExistsException.class, ()-> bank.createAccount(id2, 100.999));
        assertThrows(AccountAlreadyExistsException.class, ()-> bank.createAccount(id3, -5.055));


    }
}
