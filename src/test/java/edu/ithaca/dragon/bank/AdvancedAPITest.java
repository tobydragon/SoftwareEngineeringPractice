package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdvancedAPITest {

    @Test
    void systemTest() throws AccountAlreadyExistsException, AccountDoesNotExistException,
            BalanceRemainingException, ExceedsMaxWithdrawalException, InsufficientFundsException,
            IllegalArgumentException, AccountFrozenException
    {
        AdvancedAPI tellerSystem = new CentralBank();

        //create accounts
        tellerSystem.createAccount("kate@bank.com", "bankmaster", 500, false);
        assertThrows(AccountAlreadyExistsException.class, ()-> tellerSystem.createAccount("kate@bank.com", "letmeinplease", 100, false));
        tellerSystem.createAccount("vera@bank.com", "yaymoney", 750, true);
        tellerSystem.createAccount("josue@bank.com", "securepassword", 1000, false);

        //check balance
        assertEquals(500, tellerSystem.checkBalance("kate@bank.com"));
        assertEquals(750, tellerSystem.checkBalance("vera@bank.com"));
        assertEquals(1000, tellerSystem.checkBalance("josue@bank.com"));

        //confirm credentials
        assertFalse(tellerSystem.confirmCredentials("kate@bank.com", "letsgobank"));
        assertTrue(tellerSystem.confirmCredentials("kate@bank.com", "bankmaster"));
        assertFalse(tellerSystem.confirmCredentials("vera@bank.com", "bankmaster"));
        assertTrue(tellerSystem.confirmCredentials("vera@bank.com", "yaymoney"));

        //withdraw
        assertThrows(InsufficientFundsException.class, ()-> tellerSystem.withdraw("kate@bank.com", 600));
        assertThrows(ExceedsMaxWithdrawalException.class, ()-> tellerSystem.withdraw("vera@bank.com", 600));
        tellerSystem.withdraw("josue@bank.com", 600);
        assertEquals(400, tellerSystem.checkBalance("josue@bank.com"));

        //deposit
        assertThrows(IllegalArgumentException.class, ()-> tellerSystem.deposit("kate@bank.com", 100.9999));
        tellerSystem.deposit("kate@bank.com", 500);
        assertEquals(1000, tellerSystem.checkBalance("kate@bank.com"));
        tellerSystem.deposit("josue@bank.com", 12.34);
        assertEquals(412.34, tellerSystem.checkBalance("josue@bank.com"));

        //transfer
        tellerSystem.transfer("kate@bank.com", "vera@bank.com", 100);
        assertEquals(900, tellerSystem.checkBalance("kate@bank.com"));
        assertEquals(850, tellerSystem.checkBalance("vera@bank.com"));

        //transaction history - waiting on implementation
        assertEquals("deposit 500.00,transfer to vera@bank.com 100.00", tellerSystem.transactionHistory("kate@bank.com"));

        //close account
        assertThrows(BalanceRemainingException.class, ()-> tellerSystem.closeAccount("kate@bank.com"));
        tellerSystem.withdraw("kate@bank.com", 900);
        tellerSystem.closeAccount("kate@bank.com");
        assertThrows(AccountDoesNotExistException.class, ()-> tellerSystem.checkBalance("kate@bank.com"));
    }

}
