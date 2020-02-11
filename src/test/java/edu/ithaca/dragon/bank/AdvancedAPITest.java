package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdvancedAPITest {

    @Test
    void createAccountTest() throws AccountNotFoundException, AccountAlreadyExistsException{
        AdvancedAPI teller = new CentralBank();
        teller.createAccount("0", 200);

        assertEquals(200, teller.checkBalance("0"));

        assertThrows(AccountAlreadyExistsException.class, () -> teller.createAccount("0", 200));
    }

    @Test
    void checkBalanceTest() throws AccountNotFoundException, AccountAlreadyExistsException{
        AdvancedAPI teller = new CentralBank();
        teller.createAccount("0", 200);

        assertEquals(200, teller.checkBalance("0"));

        assertThrows(AccountNotFoundException.class, () -> teller.checkBalance("2"));
    }

}