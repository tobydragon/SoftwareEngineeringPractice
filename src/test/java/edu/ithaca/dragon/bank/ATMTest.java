package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ATMTest {

    @Test
    void constructorTest() {
        CentralBank bank = new CentralBank();
        ATM a = new ATM(bank);

        assertEquals(bank, a.getCentralBank());
    }

    @Test
    void checkBalanceTest() {
        CentralBank bank = new CentralBank();
        bank.getAccounts().put("abc", new CheckingAccount(100, "abc"));
        ATM a = new ATM(bank);

        assertEquals(100, a.checkBalance("abc"));
    }

    @Test
    void withdrawTest() throws InsufficientFundsException, AccountFrozenException{
        CentralBank bank = new CentralBank();
        bank.getAccounts().put("abc", new CheckingAccount(100, "abc"));
        ATM a = new ATM(bank);

        a.withdraw("abc", 50);
        assertEquals(50, a.checkBalance("abc"));
        assertThrows(InsufficientFundsException.class, () -> a.withdraw("abc", 100));
        assertThrows(IllegalArgumentException.class, () -> a.withdraw( "help", 100));

    }

    @Test
    void depositTest() throws AccountFrozenException{
        CentralBank bank = new CentralBank();
        bank.getAccounts().put("abc", new CheckingAccount(100, "abc"));
        ATM a = new ATM(bank);

        a.deposit("abc", 100);
        assertEquals(200, a.checkBalance("abc"));
        assertThrows(IllegalArgumentException.class, () -> a.deposit( "help", 100));

    }

    @Test
    void transferTest() throws InsufficientFundsException, AccountFrozenException{
        CentralBank bank = new CentralBank();
        bank.getAccounts().put("abc", new CheckingAccount(100, "abc"));
        bank.getAccounts().put("def", new CheckingAccount(100, "abc"));
        ATM a = new ATM(bank);

        a.transfer("abc", "def", 50);
        assertEquals(50, a.checkBalance("abc"));
        assertEquals(150, a.checkBalance("def"));

        assertThrows(InsufficientFundsException.class, () -> a.transfer("abc", "def", 200));
        assertThrows(IllegalArgumentException.class, () -> a.transfer("abc", "help", 100));
    }



}
