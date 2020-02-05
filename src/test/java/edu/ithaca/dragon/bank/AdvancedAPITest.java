package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdvancedAPITest {

    @Test
    void createAccountTest(){
        CentralBank bankAccount = new CentralBank();
        bankAccount.createAccount("1245", "a1@hello.com", "testpassword", 500);
        assertNull( bankAccount.accountMap.get("12466"));
        assertEquals(500, bankAccount.accountMap.get("1245").getBalance());
        assertEquals("a1@hello.com", bankAccount.accountMap.get("1245").getEmail());
        assertEquals("testpassword", bankAccount.accountMap.get("1245").getPassword());

        CentralBank bankAccount2 = new CentralBank();
        bankAccount2.createAccount("BH8525", "atest3@gmail.com", "funny", 1000);
        assertNull(bankAccount2.accountMap.get("BH85425"));
        assertEquals(1000, bankAccount2.accountMap.get("BH8525").getBalance());
        assertEquals("atest3@gmail.com", bankAccount2.accountMap.get("BH8525").getEmail());
        assertEquals("funny", bankAccount2.accountMap.get("BH8525").getPassword());

    }


    @Test
    void closeAccountTest() {
        CentralBank bankAccount = new CentralBank();
        bankAccount.createAccount("1245", "a1@hello.com", "testpassword", 500);
        assertEquals("a1@hello.com", bankAccount.accountMap.get("1245").getEmail());
        bankAccount.closeAccount("1245");
        assertNull(bankAccount.accountMap.get("1245"));

        CentralBank bankAccount2 = new CentralBank();
        bankAccount2.createAccount("BH8525", "atest3@gmail.com", "funny", 1000);
        assertEquals("atest3@gmail.com", bankAccount2.accountMap.get("BH8525").getEmail());
        bankAccount2.closeAccount("BH8525");
        assertNull(bankAccount2.accountMap.get("BH8525"));

    }

}
