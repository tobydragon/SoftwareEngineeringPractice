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
        //freeze account with zero
        Checking checkingTester = new Checking("1452569405", "Toby Dragon", "password", 0);
        checkingTester.setFrozen(true);
        assertTrue(checkingTester.getFrozenStatus());

        Savings savingsTester = new Savings("1029305967", "Doug Turnbull", "localify", 0, 5, 100);
        savingsTester.setFrozen(true);
        assertTrue(savingsTester.getFrozenStatus());

        //freeze account with balance
        checkingTester = new Checking("1452048605", "Ali Erkan", "networks", 100);
        checkingTester.setFrozen(true);
        assertTrue(checkingTester.getFrozenStatus());

        savingsTester = new Savings("1021059277", "Sharon Stansfield", "virtual", 200, 5, 100);
        savingsTester.setFrozen(true);
        assertTrue(savingsTester.getFrozenStatus());
    }

}
