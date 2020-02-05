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
    void checkBalanceTest(){

    }







}
