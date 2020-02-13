package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdminTest {

    @Test
    void constructorTest() {
        Admin admin = new Admin(null);
        assertEquals(null, admin.getBank());
        Map<String, Account> testCollection = new HashMap<>();
        testCollection.put("1234", new CheckingAccount(50, "1234"));
        testCollection.put("123", new CheckingAccount(100,"123"));
        CentralBank testBank = new CentralBank();
        testBank.setAccounts(testCollection);
        admin = new Admin(testBank);
        assertEquals(testBank, admin.getBank());

    }

    @Test
    void freezeAccountTest() {
        Account abcAcc = new CheckingAccount(500, "abc");
        Account xyzAcc = new CheckingAccount(500, "xyz");
        Map<String, Account>  collection = new HashMap<>();
        collection.put(abcAcc.getID(), abcAcc);
        collection.put(xyzAcc.getID(), xyzAcc);
        CentralBank testBank = new CentralBank();
        testBank.setAccounts(collection);
        Admin admin = new Admin(testBank);
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

    @Test
    void unFreezeAccountTest() {
        Account abcAcc = new CheckingAccount(500, "abc");
        Account xyzAcc = new CheckingAccount(500, "xyz");
        Map<String, Account>  collection = new HashMap<>();
        collection.put(abcAcc.getID(), abcAcc);
        collection.put(xyzAcc.getID(), xyzAcc);
        CentralBank testBank = new CentralBank();
        testBank.setAccounts(collection);
        Admin admin = new Admin(testBank);
        //Checking true frozen status after change
        abcAcc.setFrozen(true);
        xyzAcc.setFrozen(true);
        assertEquals(true, abcAcc.getFrozenStatus());
        assertEquals(true, xyzAcc.getFrozenStatus());
        //Checking only one acc gets changed
        admin.unfreezeAcct("abc");
        assertEquals(false, abcAcc.getFrozenStatus());
        assertEquals(true, xyzAcc.getFrozenStatus());
        //Checking can unfreeze other acc
        admin.unfreezeAcct("xyz");
        assertEquals(false, xyzAcc.getFrozenStatus());
        //Checking that unfreezing an unfrozen account remains false
        admin.unfreezeAcct("xyz");
        assertEquals(false, xyzAcc.getFrozenStatus());
        //Checking with IDs not present in collection
        assertThrows(IllegalArgumentException.class, () -> admin.unfreezeAcct("123"));
        assertThrows(IllegalArgumentException.class, () -> admin.unfreezeAcct(""));
        assertThrows(IllegalArgumentException.class, () -> admin.unfreezeAcct("abcd"));

    }

    @Test
    void calcTotalAssetsTest(){
        CentralBank testBank = new CentralBank();
        Admin admin = new Admin(testBank);
        //Checking with no accounts
        assertEquals(0, admin.calcTotalAssets());
        //Checking with one account
        testBank.getAccounts().put("abc", new CheckingAccount(100, "abc"));
        assertEquals(100, admin.calcTotalAssets());
        //Checking with multiple accounts
        testBank.getAccounts().put("xyz", new CheckingAccount(50.65, "xyz"));
        testBank.getAccounts().put("def",new CheckingAccount(156, "def"));
        assertEquals(306.65, admin.calcTotalAssets());
        //Check when there is an account with 0 assets
        testBank.getAccounts().put("ghi", new CheckingAccount(0, "ghi"));
        assertEquals(306.65, admin.calcTotalAssets());
    }



    @Test
    void integrationTest() throws InsufficientFundsException,  AccountFrozenException{
        //Going to test that teller and admin are working properly, use teller to create new accounts and admin to find the total balance
        CentralBank bank = new CentralBank();
        Teller teller = new Teller(bank);
        Admin admin = new Admin(bank);
        assertEquals(0, admin.calcTotalAssets());
        teller.createAccount("abc", 150);
        assertEquals(150, admin.calcTotalAssets());
        teller.createAccount("123", 20.45);
        assertEquals(170.45, admin.calcTotalAssets());
        teller.createAccount("xyz", 0);
        assertEquals(170.45, admin.calcTotalAssets());
        teller.createAccount("acc", 0.14);
        assertEquals(170.59, admin.calcTotalAssets());
        assertThrows(IllegalArgumentException.class, ()->teller.createAccount("account", -50));
        assertThrows(IllegalArgumentException.class, ()->teller.createAccount("ghi", 100.505));

    }

    @Test
    void systemTest() throws InsufficientFundsException, AccountFrozenException{
        //Meant to test that freeze/unfreeze can work in conjunction, as well as calcTotalAssets working with the Account methods
        CentralBank testBank = new CentralBank();
        Teller teller = new Teller(testBank);
        teller.createAccount("abc", 500);
        teller.createAccount("xyz", 500);
        Admin admin = new Admin(testBank);
        assertEquals(1000, admin.calcTotalAssets());
        teller.transfer("abc", "xyz", 200);
        assertEquals(1000, admin.calcTotalAssets());
        teller.deposit("abc",300);
        assertEquals(1300, admin.calcTotalAssets());
        teller.withdraw("xyz",100.55);
        assertEquals(1199.45, admin.calcTotalAssets());

        admin.freezeAccount("abc");
        assertThrows(AccountFrozenException.class, ()->teller.deposit("abc", 50));
        //Checking that a frozen account is still counted in banks assets
        assertEquals(1199.45, admin.calcTotalAssets());
        assertThrows(AccountFrozenException.class, ()->teller.withdraw("abc", 50));
        assertThrows(AccountFrozenException.class, ()->teller.transfer("abc", "xyz",50));
        assertThrows(AccountFrozenException.class, ()->teller.transfer("xyz", "abc",50));
        teller.deposit("xyz", 0.13);
        assertEquals(1199.58, admin.calcTotalAssets());

        //Going to add a new account and make sure unfreeze account works as expected

        teller.createAccount("new", 300.42);
        assertEquals(1500, admin.calcTotalAssets());
        assertThrows(AccountFrozenException.class, ()->teller.transfer("abc", "new",50));
        assertThrows(AccountFrozenException.class, ()->teller.transfer("new", "abc",50));
        admin.unfreezeAcct("abc");
        teller.transfer("abc", "new", 100);
        assertEquals(1500, admin.calcTotalAssets());
        teller.deposit("abc", 150);
        assertEquals(1650, admin.calcTotalAssets());
        teller.withdraw("abc", 200);
        assertEquals(1450, admin.calcTotalAssets());


    }




}
