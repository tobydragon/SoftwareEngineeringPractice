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
    void checkBalanceTest() throws AccountFrozenException {
        CentralBank bank = new CentralBank();
        ATM a = new ATM(bank);
        // id does not exist
        assertThrows(NullPointerException.class, () -> a.checkBalance(""));
        // 0 balance
        bank.getAccounts().put("0", new CheckingAccount(0, "0", "password"));
        assertEquals(0, a.checkBalance("0"));
        // non 0 balance
        bank.getAccounts().get("0").deposit(100);
        assertEquals(100, a.checkBalance("0"));
    }

    @Test
    void withdrawTest() throws InsufficientFundsException, AccountFrozenException{
        CentralBank bank = new CentralBank();
        ATM a = new ATM(bank);

        // id does not exist
        assertThrows(IllegalArgumentException.class, () -> a.withdraw( "0", 0));

        // withdraw 0 from account
        bank.getAccounts().put("0", new CheckingAccount(0, "0", "password"));
        a.withdraw("0", 0);
        assertEquals(0, a.checkBalance("0"));

        // withdraw more than balance
        assertThrows(InsufficientFundsException.class, () -> a.withdraw("0", 1));

        // withdraw entire balance
        bank.getAccounts().put("1", new CheckingAccount(1, "1", "password"));
        a.withdraw("1", 1);
        assertEquals(0, a.checkBalance("1"));

        // withdraw partial balance
        bank.getAccounts().put("abc", new CheckingAccount(100, "abc", "password"));
        a.withdraw("abc", 50);
        assertEquals(50, a.checkBalance("abc"));
    }

    @Test
    void depositTest() throws AccountFrozenException{
        CentralBank bank = new CentralBank();
        ATM a = new ATM(bank);
        // id does not exist
        assertThrows(IllegalArgumentException.class, () -> a.deposit( "0", 0));

        // deposit 0
        bank.getAccounts().put("0", new CheckingAccount(0, "0", "password"));
        a.deposit("0", 0);
        assertEquals(0, a.checkBalance("0"));

        // deposit non 0
        bank.getAccounts().put("abc", new CheckingAccount(100, "abc", "password"));
        a.deposit("abc", 100);
        assertEquals(200, a.checkBalance("abc"));
    }

    @Test
    void transferTest() throws InsufficientFundsException, AccountFrozenException{
        CentralBank bank = new CentralBank();
        bank.getAccounts().put("abc", new CheckingAccount(100, "abc", "password"));
        bank.getAccounts().put("def", new CheckingAccount(100, "abc", "password"));
        ATM a = new ATM(bank);

        a.transfer("abc", "def", 50);
        assertEquals(50, a.checkBalance("abc"));
        assertEquals(150, a.checkBalance("def"));

        assertThrows(InsufficientFundsException.class, () -> a.transfer("abc", "def", 200));
        assertThrows(IllegalArgumentException.class, () -> a.transfer("abc", "help", 100));
    }



}
