package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CentralBankTest {


        @Test
        void checkCredentials(){
            BankAccount customerCollection[]= new BankAccount[1];
            customerCollection[0] = new BankAccount("a@b.com",305, "abcd1234");

            CentralBank cb = new CentralBank();
            assertEquals(true, cb.confirmCredentials("a@b.com","abcd1234", customerCollection));

        }

        @Test
        void checkBalance() {
            BankAccount customerCollection[]= new BankAccount[1];
            customerCollection[0] = new BankAccount("a@b.com",305, "abcd1234");

            //checks the balance of an account in the collection
            //BankAccount bankAccount = new BankAccount("a@b.com",305, "abcd1234");
            CentralBank cb = new CentralBank();
            assertEquals(305, cb.checkBalance("a@b.com",customerCollection));

            //asks for the balance of an account not in the collection
            assertThrows(IllegalArgumentException.class, ()-> cb.checkBalance("ab@c.com",customerCollection));



        }
    }

