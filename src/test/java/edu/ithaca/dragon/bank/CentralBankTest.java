package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CentralBankTest {



        @Test
        void checkBalance() {
            BankAccount customerCollection[]= new BankAccount[1];
            customerCollection[0] = new BankAccount("a@b.com",305);

            //checks the balance of an account in the collection
            BankAccount bankAccount = new BankAccount("a@b.com",305);
            CentralBank cb = new CentralBank();
            assertEquals(305, cb.checkBalance("a@b.com",customerCollection));

            //asks for the balance of an account not in the collection
            assertThrows(IllegalArgumentException.class, ()-> cb.checkBalance("ab@c.com",customerCollection));



        }
    }

