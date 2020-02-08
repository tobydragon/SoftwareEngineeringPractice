
package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class CentralBankTest {

    @Test
    void createAccountTest() {
        CentralBank centralBank1 = new CentralBank("Keybank", null, null);
        centralBank1.createAccount("001", 500);

        //Check for correct creation of account
        assertEquals(centralBank1.checkBalance("001"), 500);

        //check for exception thrown correctly. All test cases for negatives and decimal places not required since isAmountValid()
        //already does so. Also, it wasn't specified that ID's only had to be numbers (can change if need be).
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("", 200));
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("001", 200));  //check for ID already exists
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("123", 75.899)); //positive number three decimals
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("678", -450)); //negative number
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("491", -500.671)); //negative number three decimals
    }

    @Test
    void checkBalanceTest() {

        CentralBank centralBank1 = new CentralBank("Keybank", null, null);
        centralBank1.createAccount("123", 1);
        centralBank1.createAccount("456", 2000);
        centralBank1.createAccount("789", 0);
        centralBank1.createAccount("024", 15.8);
        centralBank1.createAccount("689", 679.99);


        //Checking that it returns correct balance
        assertEquals(1, centralBank1.checkBalance("123")); //equivalence test with positive balance
        assertEquals(2000, centralBank1.checkBalance("456")); //equivalence test with positive balance
        assertEquals(0, centralBank1.checkBalance("789")); //equivalence test with zero balance
        assertEquals(15.80, centralBank1.checkBalance("024")); //equivalence test with positive balance and one decimal
        assertEquals(679.99, centralBank1.checkBalance("689")); //equivalence test with positive balance and two decimals

        //Checking that it throws if ID isn't found
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.checkBalance("123456789"));
    }

    @Test
    void calcTotalAssetsTest() {
        CentralBank centralBank0 = new CentralBank("Bank0", null, null);

        CentralBank centralBank1 = new CentralBank("Bank1", null, null);
        centralBank1.createAccount("123", 100);
        centralBank1.createAccount("456", 100);
        centralBank1.createAccount("789", 300);
        centralBank1.createAccount("024", 490.90);
        centralBank1.createAccount("689", 9.10);

        CentralBank centralBank2 = new CentralBank("Bank2", null, null);
        centralBank2.createAccount("001", 589.57);
        centralBank2.createAccount("002", 4.9);

        assertEquals(0, centralBank0.calcTotalAssets()); //Check for balance of no accounts
        assertEquals(1000, centralBank1.calcTotalAssets()); //Check for balance
        assertEquals(594.47, centralBank2.calcTotalAssets()); //Check for balance
    }

    @Test
    void freezeAccountTest() {
        /**CentralBank centralBank0 = new CentralBank("Bank0", null, null);
        centralBank0.createAccount("123", 500);
        centralBank0.freezeAccount("123");

        assert

        assertThrows(IllegalArgumentException.class, ()-> centralBank0.freezeAccount("9001"));
         **/
    }

    @Test
    void unfreezeAccountTest() {

    }

    @Test
    void checkAccountExistsTest() {
        CentralBank centralBank1 = new CentralBank("Bank1", null, null);
        centralBank1.createAccount("123", 100);
        centralBank1.createAccount("024", 490.90);
        centralBank1.createAccount("689", 9.10);

        //Check accounts that exist
        assertTrue(centralBank1.checkAccountExists("123"));
        assertTrue(centralBank1.checkAccountExists("024"));
        assertTrue(centralBank1.checkAccountExists("689"));

        //Check accounts that do not exist
        assertFalse(centralBank1.checkAccountExists("333"));
        assertFalse(centralBank1.checkAccountExists("1"));
        assertFalse(centralBank1.checkAccountExists("987651234"));
    }

    @Test
    void checkFrozenAccountExists() {
        HashMap<String, Double> frozenAccountsTest = new HashMap<String, Double>();
        frozenAccountsTest.put("123", 500.0);
        frozenAccountsTest.put("456", 570.0);
        frozenAccountsTest.put("313", 189.11);

        CentralBank centralBank1 = new CentralBank("Bank1", null, frozenAccountsTest);

        //Check frozen accounts that exist
        assertTrue(centralBank1.checkFrozenAccountExists("123"));
        assertTrue(centralBank1.checkFrozenAccountExists("456"));
        assertTrue(centralBank1.checkFrozenAccountExists("313"));

        //Check frozen accounts that don't exist
        assertFalse(centralBank1.checkFrozenAccountExists("999"));
        assertFalse(centralBank1.checkFrozenAccountExists("6"));
        assertFalse(centralBank1.checkFrozenAccountExists("999111333"));
    }
}