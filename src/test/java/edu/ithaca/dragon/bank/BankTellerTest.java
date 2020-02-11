package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankTellerTest {
    @Test
    void depositTest() throws Exception {
        //Equivalence class: valid amount-positive int
        BankTeller bt1 = new BankTeller();
        bt1.createAccount("Mari", "love12", 100);
        bt1.deposit("Mari", 100);
        assertEquals(200, bt1.checkBalance("Mari"));

        bt1.deposit("Mari", 1);
        assertEquals(201, bt1.checkBalance("Mari"));

        bt1.deposit("Mari", 1.01);
        assertEquals(101, bt1.checkBalance("Mari"));

        bt1.deposit("Mari", 5000);
        assertEquals(5101, bt1.checkBalance("Mari"));

        //Equivalence Class-IllegalArgumentException negative amount
        assertThrows(IllegalArgumentException.class, () -> bt1.deposit("Mari", -1));

        //Equivalence Class-IllegalArgumentException negative amount
        assertThrows(IllegalArgumentException.class, () -> bt1.deposit("Mari", -10));

        //Equivalence Class-IllegalArgumentException  more than 2 decimal places
        assertThrows(IllegalArgumentException.class, () -> bt1.deposit("Mari", 1.908));

    }

    @Test
    void checkBalanceTest() throws Exception {
        //Equivalence class: valid amount-positive int
        BankTeller bt1 = new BankTeller();
        bt1.createAccount("Mari", "love12", 100);

        assertEquals(100, bt1.checkBalance("Mari"));

        //Equivalence Class- 0 starting balance
        bt1.createAccount("Ami", "yes101", 0);

        assertEquals(0, bt1.checkBalance("Ami"));

        //Equivalence Class- balance >1000
        bt1.createAccount("Houry", "101NP", 5000);

        assertEquals(5000, bt1.checkBalance("Houry"));

    }


    void testCreateAccount() throws Exception {
        BankTeller b1 = new BankTeller();
        assertEquals(0, b1.getNumCustomers());
        b1.createAccount("bob", "password", 100);
        assertEquals(1, b1.getNumCustomers());
        b1.createAccount("bb", "password", 100);
        assertEquals(2, b1.getNumCustomers());

        assertThrows(IllegalArgumentException.class, () -> b1.createAccount("bb", "password", 100));
        assertThrows(IllegalArgumentException.class, () -> b1.createAccount("b", "password", -100));
        assertThrows(IllegalArgumentException.class, () -> b1.createAccount("b", "password", 100.001));
    }

    @Test
    void testCloseAccount() throws Exception {
        BankTeller b1 = new BankTeller();
        b1.createAccount("bob", "password", 100);
        b1.createAccount("bb", "password", 100);
        assertEquals(2, b1.getNumCustomers());
        b1.closeAccount("bob");
        assertEquals(1, b1.getNumCustomers());

        assertThrows(IllegalArgumentException.class, () -> b1.closeAccount("bob"));

    }
}

