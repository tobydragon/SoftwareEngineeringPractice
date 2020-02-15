
package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


public class CentralBankTest {

    @Test
    void createAccountTest() {
        CentralBank centralBank1 = new CentralBank("Keybank");
        centralBank1.createAccount("123", 500, "password", false);

        //Check for correct creation of account
        assertEquals(centralBank1.checkBalance("123"), 500);

        //check for exception thrown correctly. All test cases for negatives and decimal places not required since isAmountValid()
        //already does so. Also, it wasn't specified that ID's only had to be numbers (can change if need be).
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("", 200, "password", false));
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("123", 200, "password",true));  //check for ID already exists
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("264", 75.899, "password",true)); //positive number three decimals
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("678", -450, "password",false)); //negative number
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("491", -500.671, "password",false)); //negative number three decimals
    }

    @Test
    void closeAccountTest(){
        CentralBank centBank = new CentralBank("CoolBank");
        centBank.createAccount("407",200, "password", true);
        centBank.closeAccount("407");

        assertFalse(centBank.checkAccountExists("407"));
        assertThrows(IllegalArgumentException.class, () -> centBank.closeAccount("608"));


    }

    @Test
    void checkBalanceTest() {

        CentralBank centralBank1 = new CentralBank("Keybank");
        centralBank1.createAccount("123", 1, "password", false);
        centralBank1.createAccount("456", 2000, "password", false);
        centralBank1.createAccount("789", 0, "password", true);
        centralBank1.createAccount("024", 15.8, "password", true);
        centralBank1.createAccount("689", 679.99, "password", false);


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
        CentralBank centralBank0 = new CentralBank("Bank0");

        CentralBank centralBank1 = new CentralBank("Bank1");
        centralBank1.createAccount("123", 100, "password", true);
        centralBank1.createAccount("456", 100 ,"password", true);
        centralBank1.createAccount("789", 300, "password", false);
        centralBank1.createAccount("024", 490.90, "password", true);
        centralBank1.createAccount("689", 9.10, "password", false);

        CentralBank centralBank2 = new CentralBank("Bank2");
        centralBank2.createAccount("001", 589.57, "password", true);
        centralBank2.createAccount("002", 4.9, "password", true);

        assertEquals(0, centralBank0.calcTotalAssets()); //Check for balance of no accounts
        assertEquals(1000, centralBank1.calcTotalAssets()); //Check for balance
        assertEquals(594.47, centralBank2.calcTotalAssets()); //Check for balance
    }

    @Test
    void freezeAccountTest() {
        CentralBank centralBank0 = new CentralBank("Bank0");
        centralBank0.createAccount("123", 500, "password", false);
        centralBank0.createAccount("003", 1000.3, "password", true);
        centralBank0.freezeAccount("123");
        centralBank0.freezeAccount("003");

        //Check that frozen accounts exist under frozen accounts, but not normal accounts
        assertTrue(centralBank0.checkFrozenAccountExists("123"));
        assertFalse(centralBank0.checkAccountExists("123"));
        assertTrue(centralBank0.checkFrozenAccountExists("003"));
        assertFalse(centralBank0.checkAccountExists("003"));

        //If account doesn't exist throw exception
        assertThrows(IllegalArgumentException.class, ()-> centralBank0.freezeAccount("9001"));

        //If already froze throw exception
        assertThrows(IllegalArgumentException.class, ()-> centralBank0.freezeAccount("123"));

    }

    @Test
    void unfreezeAccountTest() {
        CentralBank centralBank0 = new CentralBank("Bank0");
        centralBank0.createAccount("345", 310, "password", true);
        centralBank0.createAccount("007", 21.38, "password", false);
        centralBank0.freezeAccount("345");
        centralBank0.freezeAccount("007");
        centralBank0.unfreezeAcct("345");
        centralBank0.unfreezeAcct("007");


        //Check that unfrozen accounts exist under accounts, but not frozen accounts
        assertFalse(centralBank0.checkFrozenAccountExists("345"));
        assertTrue(centralBank0.checkAccountExists("345"));
        assertFalse(centralBank0.checkFrozenAccountExists("007"));
        assertTrue(centralBank0.checkAccountExists("007"));

        //If account doesn't exist throw exception
        assertThrows(IllegalArgumentException.class, ()-> centralBank0.unfreezeAcct("9001"));

        //If already unfrozen throw exception
        assertThrows(IllegalArgumentException.class, ()-> centralBank0.unfreezeAcct("007"));

    }

    @Test
    void checkAccountExistsTest() {
        CentralBank centralBank1 = new CentralBank("Bank1");
        centralBank1.createAccount("123", 100, "password", false);
        centralBank1.createAccount("024", 490.90, "password", true);
        centralBank1.createAccount("689", 9.10, "password", true);

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

        CentralBank centralBank1 = new CentralBank("Bank1");
        centralBank1.createAccount("123", 1, "password", false);
        centralBank1.createAccount("456", .01, "password", true);
        centralBank1.createAccount("313", 678.9, "password", false);
        centralBank1.freezeAccount("123");
        centralBank1.freezeAccount("456");
        centralBank1.freezeAccount("313");

        //Check frozen accounts that exist
        assertTrue(centralBank1.checkFrozenAccountExists("123"));
        assertTrue(centralBank1.checkFrozenAccountExists("456"));
        assertTrue(centralBank1.checkFrozenAccountExists("313"));

        //Check frozen accounts that don't exist
        assertFalse(centralBank1.checkFrozenAccountExists("999"));
        assertFalse(centralBank1.checkFrozenAccountExists("6"));
        assertFalse(centralBank1.checkFrozenAccountExists("999111333"));
    }

    @Test
    void withdrawTest() throws InsufficientFundsException {
        CentralBank testBank = new CentralBank("Test Bank");

        //normal withdraw functions
        testBank.createAccount("1234", 1000, "password", true);
        testBank.withdraw("1234", 350);
        assertEquals(650, testBank.checkBalance("1234"));
        testBank.withdraw("1234", 500);
        assertEquals(150, testBank.checkBalance("1234"));
        testBank.withdraw("1234", 5);
        assertEquals(145, testBank.checkBalance("1234"));

        //withdraw more than balance
        testBank.createAccount("4321", 500, "password", false);
        assertThrows(InsufficientFundsException.class, () -> testBank.withdraw("4321", 501));
        assertThrows(InsufficientFundsException.class, () -> testBank.withdraw("4321", 1000));
        assertThrows(InsufficientFundsException.class, () -> testBank.withdraw("4321", 50000));

        //withdraw negative amount
        assertThrows(IllegalArgumentException.class, () -> testBank.withdraw("4321", -1));
        assertThrows(IllegalArgumentException.class, () -> testBank.withdraw("4321", -100));
        assertThrows(IllegalArgumentException.class, () -> testBank.withdraw("4321", -1000));
        assertThrows(IllegalArgumentException.class, () -> testBank.withdraw("4321", 200.101)); //three decimal places

        //throws exception if account does not exist
        assertThrows(IllegalArgumentException.class, () -> testBank.withdraw("321", 80.6));
    }

    @Test
    void depositTest(){
        CentralBank testBank = new CentralBank("Test Bank");

        //normal cases
        testBank.createAccount("1234", 50, "password", true);
        testBank.deposit("1234", 179.80);
        assertEquals(229.80, testBank.checkBalance("1234"));
        testBank.deposit("1234", 842.12);
        assertEquals(1071.92, testBank.checkBalance("1234"));
        testBank.deposit("1234", 5.30);
        assertEquals(1077.22, testBank.checkBalance("1234"));

        //negative amount/three decimals
        testBank.createAccount("4321", 500, "password", false);
        assertThrows(IllegalArgumentException.class, () -> testBank.deposit("4321", -10));
        assertThrows(IllegalArgumentException.class, () -> testBank.deposit("4321", -100));
        assertThrows(IllegalArgumentException.class, () -> testBank.deposit("4321", -1000));
        assertThrows(IllegalArgumentException.class, () -> testBank.deposit("4321", -10.101));

        //throws exception if account does not exist
        assertThrows(IllegalArgumentException.class, () -> testBank.deposit("999", 500));
    }

    @Test
    void transferTest() throws InsufficientFundsException {
        CentralBank testBank = new CentralBank("Test Bank");

        //normal cases
        testBank.createAccount("1234", 386.76, "password", false);
        testBank.createAccount("4321", 125.73, "password", true);
        testBank.transfer("1234", "4321", 100);
        assertEquals(286.76, testBank.checkBalance("1234"));
        //assertEquals(225.73,testBank.checkBalance("4321"));

        testBank.createAccount("5678", 4826.67, "password", true);
        testBank.createAccount("8765", 263.82, "password", false);
        testBank.transfer("5678", "8765", 2500);
        assertEquals(2326.67, testBank.checkBalance("5678"));
        assertEquals(2763.82,testBank.checkBalance("8765"));


        //negative amount/three decimals
        assertThrows(IllegalArgumentException.class, () -> testBank.transfer("1234", "4321",-100));
        assertThrows(IllegalArgumentException.class, () -> testBank.transfer("1234", "4321", -1000));
        assertThrows(IllegalArgumentException.class, () -> testBank.transfer("1234", "4321", -10));
        assertThrows(IllegalArgumentException.class, () -> testBank.transfer("1234", "4321", -1.502));

        //throws exception if sending account does not exist
        assertThrows(IllegalArgumentException.class, () -> testBank.transfer("9081", "1234", 89.18));

        //throws exception if receiving account does not exist
        assertThrows(IllegalArgumentException.class, () -> testBank.transfer("4321", "1190", 1.70));

    }

    @Test
    void transactionHistoryTest() throws InsufficientFundsException{
        CentralBank centBank2 =  new CentralBank("GoodBank");
        centBank2.createAccount("01",500, "password", true);

        assertThrows(IllegalArgumentException.class, () -> centBank2.transactionHistory("01"));

        centBank2.deposit("01",350.0);
        assertEquals("Deposit: 350.0\n",centBank2.transactionHistory("01"));

        centBank2.withdraw("01", 250.0);
        assertEquals("Deposit: 350.0\nWithdraw: 250.0\n", centBank2.transactionHistory("01"));
        assertEquals(2, centBank2.getTranCount("01"));

        centBank2.closeAccount("01");
        assertThrows(NullPointerException.class, () -> centBank2.transactionHistory("01"));
        assertThrows(NullPointerException.class, () -> centBank2.getTranCount("01"));
    }

    @Test
    void confirmCredentialsTest() {
        CentralBank centBank1 =  new CentralBank("GoodBank");
        centBank1.createAccount("001", 200, "246abc", false);
        centBank1.createAccount("002", 250.5, "123999", false);

        //check that password is right or wrong
        assertTrue(centBank1.confirmCredentials("001", "246abc"));
        assertFalse(centBank1.confirmCredentials("001", "1246"));
        assertTrue(centBank1.confirmCredentials("002", "123999"));
        assertFalse(centBank1.confirmCredentials("002", "abcdefg"));

        //check that exception is thrown if accountID doesn't exist
        assertThrows(IllegalArgumentException.class, () -> centBank1.confirmCredentials("003", "abc"));
    }

    @Test
    //Integration tests on code that Cobi wrote
    void cobiIntegrationTest() {
        CentralBank centralBank1 = new CentralBank("Bank1");
        centralBank1.createAccount("123", 300, "password", true);

        centralBank1.createAccount("345", 900, "password", false);
        centralBank1.createAccount("999", 360.18, "password", true);


        assertEquals(centralBank1.checkBalance("123"), 300); //checking integration between createAccount and checkBalance
        assertEquals(centralBank1.calcTotalAssets(), 1560.18); //checking integration between createAccount and calcTotalAssets

        centralBank1.freezeAccount("999");
        assertFalse(centralBank1.checkAccountExists("999")); //integration test between createAccount and freezeAccount
        assertTrue(centralBank1.checkFrozenAccountExists("999"));

        centralBank1.unfreezeAcct("999");
        assertTrue(centralBank1.checkAccountExists("999")); //integration test between createAccount, freezeAccount, unfreezeAccount
        assertFalse(centralBank1.checkFrozenAccountExists("999"));



    }

    @Test
    //System tests on code that Cobi wrote
    void cobiSystemTests() {
        CentralBank centralBank1 = new CentralBank("Test Bank");
        centralBank1.createAccount("001", 18.79, "password", true);
        centralBank1.createAccount("002", 400.2, "password", false);
        centralBank1.createAccount("003", 60, "password", true);

        assertEquals(centralBank1.calcTotalAssets(), 478.99); //test calcTotalAssets after creating accounts

        centralBank1.freezeAccount("002");
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.checkBalance("002")); //Make sure you cant checkBalance of frozen account

        centralBank1.unfreezeAcct("002");
        assertEquals(centralBank1.checkBalance("002"), 400.2); //test that you can checkBalance after frozen and unfrozen
        assertEquals(centralBank1.calcTotalAssets(), 478.99); //test that you can calcTotalAssets with an account that was frozen and unfrozen

        centralBank1.freezeAccount("001");
        assertEquals(centralBank1.calcTotalAssets(), 460.2); //test that calcTotalAssets does not include a frozen account

        assertThrows(IllegalArgumentException.class, ()-> centralBank1.freezeAccount("001")); //test that you can't freeze a frozen account
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("001", 2, "password", true)); //check that you can't create an account with same ID as a frozen account

        centralBank1.createAccount("004", 50.60, "password", false);
        assertEquals(centralBank1.calcTotalAssets(), 510.80); //check that you can create another account later and still test calcTotalAssets
        assertTrue(centralBank1.confirmCredentials("002", "password")); //check confirmCredentials
    }
}