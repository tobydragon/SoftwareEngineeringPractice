package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdminTest {

    @Test
    void constructorTest() {
        Admin admin = new Admin(null);
        assertEquals(null, admin.getAccounts());
        Collection <Account> testCollection = new ArrayList<Account>();
        testCollection.add(new CheckingAccount(50, "1234"));
        testCollection.add(new CheckingAccount(100,"123"));
        admin = new Admin(testCollection);
        assertEquals(testCollection,admin.getAccounts());

    }

    @Test
    void freezeAccountTest() {
        Account abcAcc = new CheckingAccount(500, "abc");
        Account xyzAcc = new CheckingAccount(500, "xyz");
        Collection<Account>  collection = new ArrayList<Account>();
        collection.add(abcAcc);
        collection.add(xyzAcc);
        Admin admin = new Admin(collection);
        //Checking false frozen status after no change
        assertEquals(false, abcAcc.getFrozenStatus());
        assertEquals(false, xyzAcc.getFrozenStatus());
        admin.freezeAccount("abc");
        //Checking for true after frozen, and that other account unaffected
        assertEquals(true, abcAcc.getFrozenStatus());
        assertEquals(false, xyzAcc.getFrozenStatus());
        admin.freezeAccount("abc");
        //Checking still true if you attempt to freeze a frozen account
        assertEquals(true, abcAcc.getFrozenStatus());
        //Checking freezing other account
        admin.freezeAccount("xyz");
        assertEquals(true, xyzAcc.getFrozenStatus());
        //Checking with IDs not present in collection
        assertThrows(IllegalArgumentException.class, () -> admin.freezeAccount("123"));
        assertThrows(IllegalArgumentException.class, () -> admin.freezeAccount(""));
        assertThrows(IllegalArgumentException.class, () -> admin.freezeAccount("abcd"));

    }



}
