package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BasicAPITest {

    @Test
    void confirmCredentialsTest(){

        CentralBank  bankAccount = new CentralBank();
        CentralBank bankAccount2 = new CentralBank();
        bankAccount.createAccount("11212", "a@b.com", "testingPassword", 500);
        bankAccount2.createAccount("11BFWGG", "tester@gmail.com", "singleLetter", 1000);

        assertFalse(bankAccount.confirmCredentials("11212", "test"));
        assertFalse( bankAccount.confirmCredentials("112", "testingPassword"));
        assertTrue( bankAccount.confirmCredentials("11212", "testingPassword"));

        assertFalse(bankAccount2.confirmCredentials("11bfwgg", "singleLetter"));
        assertFalse(bankAccount2.confirmCredentials("11bfwgg", "SingleLetter"));
        assertTrue(bankAccount2.confirmCredentials("11BFWGG", "singleLetter"));

    }

    @Test
    void checkBalanceTest() throws IllegalArgumentException, InsufficientFundsException{
        CentralBank bank = new CentralBank();
        bank.createAccount("test123", "a@b.com", "testpass", 1000);
        assertEquals(1000, bank.checkBalance("test123"));
        //different account names
        bank.withdraw("test123", 1);
        assertEquals(999, bank.checkBalance("test123"));
        bank.withdraw("test123", 250.47);
        assertEquals(748.53, bank.checkBalance("test123"));
        bank.withdraw("test123", 748.53);
        assertEquals(0, bank.checkBalance("test123"));

        bank.createAccount("test456", "b@a.com", "testpass", 3290.57);
        assertEquals(3290.57, bank.checkBalance("test456"));

        bank.createAccount("test789", "b@a.com", "testpass", 999999999.99);
        assertEquals(999999999.99, bank.checkBalance("test789"));



    }

    @Test
    void withdrawTest() throws IllegalArgumentException, InsufficientFundsException{
        CentralBank bank = new CentralBank();
        bank.createAccount("test123", "a@b.com", "testpass", 1000);
        assertEquals(1000, bank.checkBalance("test123"));
        //legal withdraws
        bank.withdraw("test123", 1);
        assertEquals(999, bank.checkBalance("test123"));
        bank.withdraw("test123", 250.47);
        assertEquals(748.53, bank.checkBalance("test123"));
        bank.withdraw("test123", 748.53);
        assertEquals(0, bank.checkBalance("test123"));
        //illegal withdraws
        assertThrows(IllegalArgumentException.class, ()->bank.withdraw("test123", -1));
        assertThrows(IllegalArgumentException.class, ()->bank.withdraw("test123", -500.89));
        assertThrows(IllegalArgumentException.class, ()->bank.withdraw("test123", -1000000.45));
        assertThrows(IllegalArgumentException.class, ()->bank.withdraw("test123", -1.96646));
        assertThrows(IllegalArgumentException.class, ()->bank.withdraw("test123", -1.9648690548065809546));
        assertThrows(IllegalArgumentException.class, ()->bank.withdraw("test123", 50.3940685));



    }







}
