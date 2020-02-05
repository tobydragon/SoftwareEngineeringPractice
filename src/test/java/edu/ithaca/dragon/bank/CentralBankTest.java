package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CentralBankTest {

    @Test
    void checkBalanceTest(){
        CentralBank account1 = new CentralBank("1234", 600);

        assertEquals(600, account1.checkBalance("1234"));

        CentralBank account2 = new CentralBank("5678", 400);

        assertEquals(400, account2.checkBalance("5678"));
    }
}
