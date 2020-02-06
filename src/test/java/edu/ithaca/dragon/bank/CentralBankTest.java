package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CentralBankTest {

        @Test
        void checkBalance() {
            BankAccount bankAccount = new BankAccount("a@b.com",305);
            assertEquals(305, CentralBank.checkBalance("a@b.com"));


        }
    }

