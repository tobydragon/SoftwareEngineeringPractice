package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountCollectionTest {
    @Test
    void BankAccountCollectionConstructorTest(){
        BankAccountCollection testCollection = new BankAccountCollection();
        /* No other tests here at the moment. */
    }

    @Test
    void BankAccountCollectionAdditionAndRetrieveTest() throws NonExistentAccountException, IllegalArgumentException{
        BankAccountCollection testCollection = new BankAccountCollection();
        BankAccount testBankAccount01 = new BankAccount("ekuznetsov@ithaca.edu", 100, 1);
        BankAccount testBankAccount02 = new BankAccount("abc@def.com", 50, 2);

        testCollection.addBankAccount(testBankAccount01);
        assertTrue(testBankAccount01.equals(testCollection.retrieveAccount(1,0)));
        assertFalse(testBankAccount02.equals(testCollection.retrieveAccount(1,0)));


    }

    @Test
    void BankAccountCollectionRemovalTest() throws NonExistentAccountException, IllegalArgumentException{
        BankAccountCollection testCollection = new BankAccountCollection();
        BankAccount testBankAccount01 = new BankAccount("ekuznetsov@ithaca.edu", 100, 1);
        BankAccount testBankAccount02 = new BankAccount("ekuznetsov@ithaca.edu", 50, 1);
        BankAccount testBankAccount03 = new BankAccount("ekuznetsov@ithaca.edu", 0, 1);

        testCollection.addBankAccount(testBankAccount01);
        testCollection.addBankAccount(testBankAccount02);
        testCollection.addBankAccount(testBankAccount03);

        assertThrows(NonExistentAccountException.class, () -> testCollection.removeBankAccount(0, 1));
        testCollection.removeBankAccount(1, 1);
        assertTrue(testBankAccount01.equals(testCollection.retrieveAccount(1,0)));
        assertTrue(testBankAccount03.equals(testCollection.retrieveAccount(1,1)));
        assertThrows(NonExistentAccountException.class, () -> testCollection.retrieveAccount(1,2));

    }

}
