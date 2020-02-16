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

    @Test
    void confirmCredentialsTest(){
        CentralBank bank = new CentralBank();
        bank.getAccounts().put("abc", new CheckingAccount(100, "abc", "password"));
        bank.getAccounts().put("def", new CheckingAccount(100, "def", "123ABcD"));
        ATM a = new ATM(bank);

        //Checking basic case for true and false
        assertTrue(a.confirmCredentials("abc", "password"));
        assertFalse(a.confirmCredentials("ghi", "notOnTheList"));

        //Checking it distinguishes capitals
        assertTrue(a.confirmCredentials("def", "123ABcD"));
        assertFalse(a.confirmCredentials("def", "123abcd"));
        assertFalse(a.confirmCredentials("def", "123ABCD"));

        //Check that password matches account
        assertFalse(a.confirmCredentials("abc", "123ABcD"));
        assertFalse(a.confirmCredentials("def", "password"));

        //Check that one char missing from password fails
        assertFalse(a.confirmCredentials("abc", "assword"));
        assertFalse(a.confirmCredentials("abc", "passwor"));
        assertFalse(a.confirmCredentials("abc", "passord"));

        //Check that one char missing from id fails
        assertFalse(a.confirmCredentials("bc", "password"));
        assertFalse(a.confirmCredentials("ac", "password"));
        assertFalse(a.confirmCredentials("ab", "password"));

        //Check that one extra char in password fails
        assertFalse(a.confirmCredentials("abc", "password1"));
        assertFalse(a.confirmCredentials("abc", "pass1word"));
        assertFalse(a.confirmCredentials("abc", "1password"));

        //Check that one extra char in id fails
        assertFalse(a.confirmCredentials("abcd", "password"));
        assertFalse(a.confirmCredentials("abdc", "password"));
        assertFalse(a.confirmCredentials("dabc", "password"));

        //Check that one different character in password fails
        assertFalse(a.confirmCredentials("abc", "dassword"));
        assertFalse(a.confirmCredentials("abc", "passmord"));
        assertFalse(a.confirmCredentials("abc", "passwork"));

        //Check that one different character in id fails
        assertFalse(a.confirmCredentials("dbc", "password"));
        assertFalse(a.confirmCredentials("ajc", "password"));
        assertFalse(a.confirmCredentials("ab1", "password"));

    }



}
