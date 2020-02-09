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
            CentralBank cb = new CentralBank();
            assertEquals(305, cb.checkBalance("a@b.com",customerCollection));

            //asks for the balance of an account not in the collection
            assertThrows(IllegalArgumentException.class, ()-> cb.checkBalance("ab@c.com",customerCollection));

        }

        @Test
        void depositTest() {
            BankAccount customerCollection[]= new BankAccount[1];
            customerCollection[0] = new BankAccount("a@b.com",305);

            //deposits a valid amount into a valid bank account
            CentralBank cb = new CentralBank();
            cb.deposit("a@b.com",50, customerCollection);
            assertEquals(355, cb.checkBalance("a@b.com",customerCollection));

            //attempts to deposit an invalid amount
            assertThrows(IllegalArgumentException.class, ()-> cb.deposit("a@b.com",5000.608,customerCollection));

            //attempts to deposit 0
            assertThrows(IllegalArgumentException.class, ()-> cb.deposit("a@b.com",0,customerCollection));


        }
    }

