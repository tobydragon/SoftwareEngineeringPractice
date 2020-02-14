package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountEventTest {

    @Test
    void constructorGetterToStringTest() {
        AccountEvent ac = new AccountEvent("Deposit", 100.0, 1000.0);  //Testing normal case of creating

        assertEquals(100, ac.getEventAmount());  //Testing normal case of getting set values
        assertEquals("Deposit", ac.getEventType());  //Testing normal case of getting set values
        assertEquals("Deposit 100.0", ac.toString());  //Testing normal case of converting to string

        ac = new AccountEvent("This string is garbage and meaningless", 0.0, 0.0);  //Testing normal case of creating

        assertEquals(0, ac.getEventAmount());  //Testing normal case of getting set values
        assertEquals("This string is garbage and meaningless", ac.getEventType());  //Testing normal case of getting set values
        assertEquals("This string is garbage and meaningless 0.0", ac.toString());  //Testing normal case of converting to string
    }

    @Test
    void suspiciousTest() {
        AccountEvent ac = new AccountEvent("Deposit", 100.0, 500.0);  //Testing normal case with less or equal to half eventAmount and not withdraw or transfer out
        assertFalse(ac.getSuspicious());

        ac = new AccountEvent("Deposit", 100.0, 200.0);  //Testing edge case with less  or equal to half eventAmount and not withdraw or transfer out
        assertFalse(ac.getSuspicious());

        ac = new AccountEvent("Deposit", 100.0, 100.0);  //Testing normal case with more than half eventAmount but not withdraw or transfer out
        assertFalse(ac.getSuspicious());

        ac = new AccountEvent("Deposit", 100.01, 200.0);  //Testing edge case with more than half eventAmount but not withdraw or transfer out
        assertFalse(ac.getSuspicious());

        ac = new AccountEvent("Withdraw", 100.0, 500.0);  //Testing normal case with less or equal to half eventAmount but withdrawing
        assertFalse(ac.getSuspicious());

        ac = new AccountEvent("Withdraw", 100.0, 200.0);  //Testing edge case with less or equal to half eventAmount but withdrawing
        assertFalse(ac.getSuspicious());

        ac = new AccountEvent("Withdraw", 100.0, 100.0);  //Testing normal case with more than half eventAmount and withdraw
        assertTrue(ac.getSuspicious());

        ac = new AccountEvent("Withdraw", 100.01, 200.0);  //Testing edge case with more than half eventAmount and withdraw
        assertTrue(ac.getSuspicious());

        ac = new AccountEvent("Transfer out", 100.0, 500.0);  //Testing normal case with less or equal to half eventAmount but transferring out
        assertFalse(ac.getSuspicious());

        ac = new AccountEvent("Transfer out", 100.0, 200.0);  //Testing edge case with less or equal to half eventAmount but transferring out
        assertFalse(ac.getSuspicious());

        ac = new AccountEvent("Transfer out", 100.0, 100.0);  //Testing normal case with more than half eventAmount and transferring out
        assertTrue(ac.getSuspicious());

        ac = new AccountEvent("Transfer out", 100.01, 200.0);  //Testing edge case with more than half eventAmount and transferring out
        assertTrue(ac.getSuspicious());
    }
}