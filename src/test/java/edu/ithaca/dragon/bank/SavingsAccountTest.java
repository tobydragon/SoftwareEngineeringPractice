package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SavingsAccountTest {

    @Test
    void constructorTest(){
        SavingsAccount testAccount = new SavingsAccount("1234", 500, "password", 5);

        //Make sure it sets the object up
        assertEquals("1234", testAccount.getId());
        assertEquals(500, testAccount.getBalance());

        //test exception
        assertThrows(IllegalArgumentException.class, () -> new SavingsAccount("4321", -10, "password", 5) );
        assertThrows(IllegalArgumentException.class, () -> new SavingsAccount("4321", 100, "password", -5) );
        assertThrows(IllegalArgumentException.class, () -> new SavingsAccount("4321", 100, "password", 5.101) );

    }

    @Test
    void compoundInterestTest(){
        SavingsAccount testAccount = new SavingsAccount("1234", 1000, "Password", 5);

        testAccount.compoundInterest();
        assertEquals(1050, testAccount.getBalance());

        testAccount.compoundInterest();
        assertEquals(1157.625, testAccount.getBalance());
    }
}
