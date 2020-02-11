package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdminTesttt {

    @Test
    void freezeAccountTest() {
        BankAccount christian = new CheckingAccount(1000, "christian");
        CentralBank myBank = new CentralBank();
        myBank.accounts.add(christian);
        Admin admin = new Admin(myBank);
        //no change
        assertEquals(false, christian.isFrozen());

        admin.freezeAccount("christian");
        //after freezing check
        assertEquals(true, christian.isFrozen());

        admin.freezeAccount("christian");
        //freexing a frozen account
        assertEquals(true, christian.isFrozen());
        //Checking with IDs not present in collection
        assertThrows(IllegalArgumentException.class, () -> admin.freezeAccount("123"));
        assertThrows(IllegalArgumentException.class, () -> admin.freezeAccount(""));
        assertThrows(IllegalArgumentException.class, () -> admin.freezeAccount("abcd"));

    }

    @Test
    void unFreezeAccountTest() {
        BankAccount christian = new CheckingAccount(1000, "christian");
        CentralBank myBank = new CentralBank();
        myBank.accounts.add(christian);
        Admin admin = new Admin(myBank);
        //no change
        assertEquals(false, christian.isFrozen());

        admin.freezeAccount("christian");
        admin.unfreezeAcct("christian");
        //after unfreezing check
        assertEquals(false, christian.isFrozen());
        admin.unfreezeAcct("christian");
        //unfreezing an unfrozen account
        assertEquals(false, christian.isFrozen());
        //Checking with IDs not present in collection
        assertThrows(IllegalArgumentException.class, () -> admin.freezeAccount("123"));
        assertThrows(IllegalArgumentException.class, () -> admin.freezeAccount(""));
        assertThrows(IllegalArgumentException.class, () -> admin.freezeAccount("abcd"));

    }

    @Test
    void calcTotalAssetsTest() {
        BankAccount acc1 = new CheckingAccount(1000, "acc1");
        BankAccount acc2 = new CheckingAccount(1500, "acc2");
        BankAccount acc3 = new SavingsAccount(3000, "acc3", .1, 3000);
        CentralBank bank = new CentralBank();
        bank.accounts.add(acc1);
        bank.accounts.add(acc2);
        bank.accounts.add(acc3);
        Admin admin = new Admin(bank);
        assertEquals(5500, admin.calcTotalAssets());
    }
}
