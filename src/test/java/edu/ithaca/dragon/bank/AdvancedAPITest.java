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

    @Test
    void checkBalanceTest() throws AccountNotFoundException, AccountAlreadyExistsException{
        AdvancedAPI teller = new CentralBank();
        teller.createAccount("a@b.com", "pass", 200);

        assertEquals(200, teller.checkBalance("a@b.com"));  //Checks normal operation, not including edge cases and exceptions that are tested elsewhere.

        assertThrows(AccountNotFoundException.class, () -> teller.checkBalance("2"));  //Tests checking the balance of an account that doesn't exist
    }

    @Test
    void withdrawTest() throws AccountNotFoundException, AccountAlreadyExistsException, IllegalArgumentException, InsufficientFundsException{
        AdvancedAPI teller = new CentralBank();
        teller.createAccount("a@b.com", "pass", 200);

        teller.withdraw("a@b.com", 100);

        assertEquals(100, teller.checkBalance("a@b.com"));  //Checks normal operation, not including edge cases and exceptions that are tested elsewhere.

        assertThrows(AccountNotFoundException.class, () -> teller.withdraw("2", 100));  //Tests withdrawing from an account that doesn't exist
    }

    @Test
    void depositTest() throws AccountNotFoundException, AccountAlreadyExistsException, IllegalArgumentException {
        AdvancedAPI teller = new CentralBank();
        teller.createAccount("a@b.com", "pass", 200);

        teller.deposit("a@b.com", 100);

        assertEquals(300, teller.checkBalance("a@b.com"));  //Checks normal operation, not including edge cases and exceptions that are tested elsewhere.

        assertThrows(AccountNotFoundException.class, () -> teller.deposit("2", 100));  //Tests depositing to an account that doesn't exist
    }

    @Test
    void transferTest() throws AccountNotFoundException, AccountAlreadyExistsException, IllegalArgumentException, InsufficientFundsException{
        AdvancedAPI teller = new CentralBank();
        teller.createAccount("a@c.com", "pass",  200);
        teller.createAccount("a@d.com", "pass", 200);

        teller.transfer("a@c.com", "a@d.com", 100);

        assertEquals(100, teller.checkBalance("a@c.com"));  //Checks normal operation, not including edge cases and exceptions that are tested elsewhere.
        assertEquals(300, teller.checkBalance("a@d.com"));  //Checks normal operation, not including edge cases and exceptions that are tested elsewhere.

        assertThrows(AccountNotFoundException.class, () -> teller.transfer("0", "2", 100));  //Tests transferring to an account that doesn't exist
        assertThrows(AccountNotFoundException.class, () -> teller.transfer("2", "0", 100));  //Tests transferring from an account that doesn't exist
    }

}