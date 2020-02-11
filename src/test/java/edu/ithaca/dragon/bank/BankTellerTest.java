package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankTellerTest {
    @Test
    void testCreateAccount() throws Exception{
        BankTeller b1 = new BankTeller();
        assertEquals(0, b1.getNumCustomers());
        b1.createAccount("bob","password", 100);
        assertEquals(1, b1.getNumCustomers());
        b1.createAccount("bb","password", 100);
        assertEquals(2, b1.getNumCustomers());

        assertThrows(IllegalArgumentException.class, ()-> b1.createAccount("bb", "password", 100));
        assertThrows(IllegalArgumentException.class, ()-> b1.createAccount("b", "password", -100));
        assertThrows(IllegalArgumentException.class, ()-> b1.createAccount("b", "password", 100.001));
    }

    @Test
    void testCloseAccount() throws Exception{
        BankTeller b1 = new BankTeller();
        b1.createAccount("bob","password", 100);
        b1.createAccount("bb","password", 100);
        assertEquals(2, b1.getNumCustomers());
        b1.closeAccount("bob");
        assertEquals(1, b1.getNumCustomers());

        assertThrows(IllegalArgumentException.class, ()-> b1.closeAccount("bob"));

    }
}
