package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CentralBankTest {

    @Test
    void depositAPITest(){
        CentralBank accountTest = new CentralBank();
        CentralBank accountTest2 = new CentralBank();
        CentralBank accountTest3 = new CentralBank();
        CentralBank accountTest4 = new CentralBank();

        accountTest.deposit("c", 300);
        accountTest2.deposit("", 300);
        accountTest3.deposit("c", -300);
        accountTest4.deposit("s", 300);
    }
}
