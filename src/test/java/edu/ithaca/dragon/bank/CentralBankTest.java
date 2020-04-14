package edu.ithaca.dragon.bank;

import edu.ithaca.dragon.util.JsonUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CentralBankTest {

    @Test
    void getCustomerCount() throws IOException {
        // Code that generated the original file
//        CentralBank testBank = new CentralBank();
//        testBank.addAccounts(JsonUtil.listFromJsonFile("src/test/resources/listFromJsonFileTest.json", BankAccount.class));
//        JsonUtil.toJsonFile("src/test/resources/centralBankTest.json", testBank);

        CentralBank testBank = JsonUtil.fromJsonFile("src/test/resources/centralBankTest.json", CentralBank.class);
        Assert.assertEquals(4, testBank.numberOfAccounts());
    }
}