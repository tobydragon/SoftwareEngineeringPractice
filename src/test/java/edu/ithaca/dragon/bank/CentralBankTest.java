package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CentralBankTest {

    @Test
    void constructorTest() {
        CentralBank bank = new CentralBank();

        assertEquals(0, bank.getAccounts().size());
    }


    @Test
    void withdrawTest() throws InsufficientFundsException{
        CentralBank bank = new CentralBank();
        bank.getAccounts().put("abc", new CheckingAccount(100, "abc"));

        bank.withdraw("abc", 50);
        assertEquals(50, bank.checkBalance("abc"));
        assertThrows(InsufficientFundsException.class, () -> bank.withdraw("abc", 100));
        assertThrows(IllegalArgumentException.class, () -> bank.withdraw( "help", 100));

    }

    @Test
    void depositTest() {
        CentralBank bank = new CentralBank();
        bank.getAccounts().put("abc", new CheckingAccount(100, "abc"));


        bank.deposit("abc", 100);
        assertEquals(200, bank.checkBalance("abc"));
        assertThrows(IllegalArgumentException.class, () -> bank.deposit( "help", 100));

    }

    @Test
    void transferTest() throws InsufficientFundsException{
        CentralBank bank = new CentralBank();
        bank.getAccounts().put("abc", new CheckingAccount(100, "abc"));
        bank.getAccounts().put("def", new CheckingAccount(100, "abc"));

        bank.transfer("abc", "def", 50);
        assertEquals(50, bank.checkBalance("abc"));
        assertEquals(150, bank.checkBalance("def"));

        assertThrows(InsufficientFundsException.class, () -> bank.transfer("abc", "def", 200));
        assertThrows(IllegalArgumentException.class, () -> bank.transfer("abc", "help", 100));
    }

}
