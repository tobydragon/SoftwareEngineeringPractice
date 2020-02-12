package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdvancedAPITest {

    @Test
    void createAccountTest() throws AccountNotFoundException, AccountAlreadyExistsException{
        AdvancedAPI teller = new CentralBank();
        teller.createAccount("a@b.com", "pass", 200);

        assertEquals(200, teller.checkBalance("a@b.com"));  //Checks normal operation, not including edge cases and exceptions that are tested elsewhere.

        assertThrows(AccountAlreadyExistsException.class, () -> teller.createAccount("a@b.com", "pass",  200));  //Tests creating an account with an id that exists
    }

    @Test
    void closeAccountTest() throws AccountNotFoundException, AccountAlreadyExistsException{
        AdvancedAPI teller = new CentralBank();
        teller.createAccount("a@b.com", "pass", 200);

        teller.closeAccount("a@b.com");

        assertThrows(AccountNotFoundException.class, () -> teller.checkBalance("0"));  //Tests that account was removed properly

        assertThrows(AccountNotFoundException.class, () -> teller.closeAccount("2"));  //Tests that a nonexistent account can't be closed
    }

}