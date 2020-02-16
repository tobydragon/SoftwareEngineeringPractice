package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdminTest {

    @Test
    void getAccountTest() {
        Admin admin = new Admin();

        // get account when there is nothing
        assertThrows(IllegalArgumentException.class, () -> admin.getAccount("1234567890"));

        // get checking account
        admin.createCheckingForTeller("1234567890", "Toby Dragon", "password", 0.0);
        assertNotEquals(null, admin.getAccount("1234567890"));

        // get savings account
        admin.createSavingsForTeller("0987654321", "Ali Erkan", "password", 100.0, 1.0, 100);
        assertNotEquals(null, "0987654321");
    }

    @Test
    void createCheckingForTellerTest () throws IllegalArgumentException {
        Admin admin = new Admin();
        //create account with no balance
        admin.createCheckingForTeller("1234567890", "Toby Dragon", "password", 0.0);
        assertEquals("1234567890", admin.getAccount("1234567890").getAcctId());

        //create account with balance
        admin.createCheckingForTeller("0987654321", "Ali Erkan", "password", 100.0);
        assertEquals("0987654321", admin.getAccount("0987654321").getAcctId());

        //create account with invalid acctID
        assertThrows(IllegalArgumentException.class, () -> admin.createCheckingForTeller("1234567890", "Sharon Stansfield", "password", 100.0));

    }

    @Test
    void createSavingsForTellerTest () {
        Admin admin = new Admin();
        //create account with no balance
        admin.createSavingsForTeller("1234567890", "Toby Dragon", "password", 0.0, 1.0, 100);
        assertEquals("1234567890", admin.getAccount("1234567890").getAcctId());

        //create account with balance
        admin.createSavingsForTeller("0987654321", "Ali Erkan", "password", 100.0, 1.0, 100);
        assertEquals("0987654321", admin.getAccount("0987654321").getAcctId());

        //create account with invalid acctID
        assertThrows(IllegalArgumentException.class, () -> admin.createSavingsForTeller("1234567890", "Sharon Stansfield", "password", 100.0, 1.0, 100));
    }

    @Test
    void freezeTest(){
        Admin admin = new Admin();
        //freeze account with zero
        admin.createCheckingForTeller("1452569405", "Toby Dragon", "password", 0);
        admin.freezeAccount("1452569405");
        Account checkingTester = admin.getAccount("1452569405");
        assertTrue(checkingTester.getFrozenStatus());

        admin.createSavingsForTeller("1029305967", "Doug Turnbull", "localify", 0, 5, 100);
        admin.freezeAccount("1029305967");
        Account savingsTester = admin.getAccount("1029305967");
        assertTrue(savingsTester.getFrozenStatus());

        //freeze account with balance
        admin.createCheckingForTeller("1452048605", "Ali Erkan", "networks", 100);
        admin.freezeAccount("1452048605");
        checkingTester = admin.getAccount("1452048605");
        assertTrue(checkingTester.getFrozenStatus());

        admin.createSavingsForTeller("1021059277", "Sharon Stansfield", "virtual", 200, 5, 100);
        admin.freezeAccount("1021059277");
        savingsTester = admin.getAccount("1021059277");
        assertTrue(savingsTester.getFrozenStatus());
    }

    @Test
    void unfreezeAccountTest(){
        Admin admin = new Admin();
        //unfreeze account with zero
        admin.createCheckingForTeller("1452569405", "Toby Dragon", "password", 0);
        admin.freezeAccount("1452569405");
        admin.unfreezeAcct("1452569405");
        Account checkingTester = admin.getAccount("1452569405");
        assertFalse(checkingTester.getFrozenStatus());

        //unfreeze unfrozen account with zero
        admin.createSavingsForTeller("1029305967", "Doug Turnbull", "localify", 0, 5, 100);
        admin.unfreezeAcct("1029305967");
        Account savingsTester = admin.getAccount("1029305967");
        assertFalse(savingsTester.getFrozenStatus());

        //unfreeze account with balance
        admin.createCheckingForTeller("1452048605", "Ali Erkan", "networks", 100);
        admin.freezeAccount("1452569405");
        admin.unfreezeAcct("1452569405");
        checkingTester = admin.getAccount("1452048605");
        assertFalse(checkingTester.getFrozenStatus());

        //unfreeze unfrozen account with balance
        admin.createSavingsForTeller("1021059277", "Sharon Stansfield", "virtual", 200, 5, 100);
        admin.unfreezeAcct("1021059277");
        savingsTester = admin.getAccount("1021059277");
        assertFalse(savingsTester.getFrozenStatus());
    }

    @Test
    void calcTotalAssetsTest () throws AcctFrozenException, IllegalArgumentException {

        // create zero accounts
        Admin admin = new Admin();
        assertEquals(0, admin.calcTotalAssets());

        // create all checking accounts
        Admin checkingAdmin = new Admin();
        checkingAdmin.createCheckingForTeller("1027584930", "ABC DEF", "abc", 5.0);
        checkingAdmin.createCheckingForTeller("1602839490", "DEF GHI", "abc", 0.0);
        checkingAdmin.createCheckingForTeller("1079602315", "GHI JKL", "abc", 20.0);
        assertEquals(25, checkingAdmin.calcTotalAssets());

        // create all savings accounts
        Admin savingsAdmin = new Admin();
        savingsAdmin.createSavingsForTeller("1869944029", "JKL MNO", "abc", 300.0, 10.4, 20);
        assertEquals(300, savingsAdmin.calcTotalAssets());

        // create mix of accounts
        Admin mixedAdmin = new Admin();
        mixedAdmin.createCheckingForTeller("1027584930", "ABC DEF", "abc", 5.0);
        mixedAdmin.createCheckingForTeller("1602839490", "DEF GHI", "abc", 0.0);
        mixedAdmin.createCheckingForTeller("1079602315", "GHI JKL", "abc", 20.0);
        mixedAdmin.createSavingsForTeller("1869944029", "JKL MNO", "abc", 300.0, 10.4, 20);
        assertEquals(325, mixedAdmin.calcTotalAssets());
    }

    @Test
    void closeAccountTest() throws AcctFrozenException, IllegalArgumentException {
        Admin admin = new Admin();

        // close an account that doesnt exist
        assertThrows(IllegalArgumentException.class, () -> admin.closeAccount("0123456789"));

        // close a frozen account
        admin.createCheckingForTeller("1920597820", "first last", "namename", 20.0);
        admin.freezeAccount("1920597820");
        assertThrows(AcctFrozenException.class, () -> admin.closeAccount("1920597820"));

        // close a checking account
        admin.createCheckingForTeller("1699552120", "first fist", "namename", 0.0);
        admin.closeAccount("1699552120");
        assertThrows(IllegalArgumentException.class, () -> admin.getAccount("1699552120"));

        // close a savings account
        admin.createSavingsForTeller("1133557799", "list last", "namename", 20.0, 5, 1);
        admin.closeAccount("1133557799");
        assertThrows(IllegalArgumentException.class, () -> admin.getAccount("1133557799"));
    }

}
