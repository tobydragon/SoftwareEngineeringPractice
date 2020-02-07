package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CentralBankTest {
    @Test
    void createAccountTest(String acctId, double startingBalance){
        CentralBank bank = new CentralBank();
        bank.createAccount("1234@gmail.com", 233);
    }

}
