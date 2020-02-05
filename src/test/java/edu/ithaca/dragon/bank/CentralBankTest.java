package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CentralBankTest {
    @Test
    void checkbalanceTest(){
        CheckingAccount acct =new CheckingAccount("christian",200);
        assertEquals(200,acct.getBalance());
    }

}
