package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BasicAPITest {

    @Test
    void checkBalanceTest() throws AccountNotFoundException, AccountAlreadyExistsException{
        CentralBank bank = new CentralBank();
        AdvancedAPI teller = bank;
        BasicAPI atm = bank;

        teller.createAccount("a@b.com", "pass", 200);

        assertEquals(200, atm.checkBalance("a@b.com"));  //Checks normal operation, not including edge cases and exceptions that are tested elsewhere.

        assertThrows(AccountNotFoundException.class, () -> atm.checkBalance("2"));  //Tests checking the balance of an account that doesn't exist
    }

    @Test
    void withdrawTest() throws AccountNotFoundException, AccountAlreadyExistsException, IllegalArgumentException, InsufficientFundsException{
        CentralBank bank = new CentralBank();
        AdvancedAPI teller = bank;
        BasicAPI atm = bank;

        teller.createAccount("a@b.com", "pass", 200);

        atm.withdraw("a@b.com", 100);

        assertEquals(100, atm.checkBalance("a@b.com"));  //Checks normal operation, not including edge cases and exceptions that are tested elsewhere.

        assertThrows(AccountNotFoundException.class, () -> atm.withdraw("2", 100));  //Tests withdrawing from an account that doesn't exist
    }

    @Test
    void depositTest() throws AccountNotFoundException, AccountAlreadyExistsException, IllegalArgumentException {
        CentralBank bank = new CentralBank();
        AdvancedAPI teller = bank;
        BasicAPI atm = bank;

        teller.createAccount("a@b.com", "pass", 200);

        atm.deposit("a@b.com", 100);

        assertEquals(300, atm.checkBalance("a@b.com"));  //Checks normal operation, not including edge cases and exceptions that are tested elsewhere.

        assertThrows(AccountNotFoundException.class, () -> atm.deposit("2", 100));  //Tests depositing to an account that doesn't exist
    }

    @Test
    void transferTest() throws AccountNotFoundException, AccountAlreadyExistsException, IllegalArgumentException, InsufficientFundsException{
        CentralBank bank = new CentralBank();
        AdvancedAPI teller = bank;
        BasicAPI atm = bank;

        teller.createAccount("a@c.com", "pass",  200);
        teller.createAccount("a@d.com", "pass", 200);

        atm.transfer("a@c.com", "a@d.com", 100);

        assertEquals(100, atm.checkBalance("a@c.com"));  //Checks normal operation, not including edge cases and exceptions that are tested elsewhere.
        assertEquals(300, atm.checkBalance("a@d.com"));  //Checks normal operation, not including edge cases and exceptions that are tested elsewhere.

        assertThrows(AccountNotFoundException.class, () -> atm.transfer("0", "2", 100));  //Tests transferring to an account that doesn't exist
        assertThrows(AccountNotFoundException.class, () -> atm.transfer("2", "0", 100));  //Tests transferring from an account that doesn't exist
    }

}