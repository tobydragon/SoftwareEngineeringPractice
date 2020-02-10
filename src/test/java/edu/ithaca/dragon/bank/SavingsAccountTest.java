package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SavingsAccountTest {


    @Test
    void withdrawTest() throws InsufficientFundsException, AccountFrozenException{
        Account a = new SavingsAccount(30, 0.5, 20, "a@b.com");

        a.withdraw(10);
        assertEquals(20, a.getBalance());

        assertThrows(IllegalArgumentException.class, () -> a.withdraw(50));
        a.withdraw(10);
        assertThrows(InsufficientFundsException.class, () -> a.withdraw(15));

    }

    @Test
    void calcInterestTest() {
        SavingsAccount a = new SavingsAccount(100, 0.5, 20, "a@b.com");

        a.calculateInterest();
        assertEquals(150, a.getBalance());
    }

}
