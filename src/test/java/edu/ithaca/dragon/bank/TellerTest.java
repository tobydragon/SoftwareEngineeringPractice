package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TellerTest {

    @Test
    void createAccountTest() {
        CentralBank centralBank = new CentralBank();
        Map<String, Account> accounts = new HashMap<>();
        centralBank.accounts = accounts;

        Teller teller = new Teller();
        teller.createAccount();

    }
}
