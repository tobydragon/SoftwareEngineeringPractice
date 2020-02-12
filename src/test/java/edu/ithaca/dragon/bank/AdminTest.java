package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdminTest {

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

}
